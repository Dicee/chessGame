package com.dici.chess.guifx;

import static com.dici.collection.CollectionUtils.listOf;
import static com.dici.chess.model.PieceType.BISHOP;
import static com.dici.chess.model.PieceType.KING;
import static com.dici.chess.model.PieceType.KNIGHT;
import static com.dici.chess.model.PieceType.PAWN;
import static com.dici.chess.model.PieceType.QUEEN;
import static com.dici.chess.model.PieceType.ROOK;

import java.util.List;
import java.util.stream.Stream;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import com.dici.chess.model.PieceType;
import com.dici.chess.model.Player;

import com.dici.check.Check;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.ImmutableTable.Builder;
import com.google.common.collect.Table;

public class TilesLoader {
    private final Image tiles = new Image(TilesLoader.class.getResourceAsStream("tiles.png"));
    
    private static final int BLACK_HEIGHT = 170;
    private static final int WHITE_HEIGHT = 151;
    private static final int BLACK_Y      = 0  ;
    private static final int WHITE_Y      = 228;
    
    private static Table<Player, PieceType, ImagePartition> IMAGE_PARTITIONS = initImagePartitions();
    
    private static Table<Player, PieceType, ImagePartition> initImagePartitions() {
        Builder<Player, PieceType, ImagePartition> imagePartitionsBuilder = ImmutableTable.builder();
        List<Integer  > xPositions = listOf(0, 99, 203, 288, 376, 473, 559);
        List<PieceType> pieceTypes = listOf(PAWN, ROOK, BISHOP, QUEEN, KNIGHT, KING);
        for (int i = 0; i < xPositions.size() - 1; i++) {
            int       current   = xPositions.get(i), next = xPositions.get(i + 1);
            PieceType pieceType = pieceTypes.get(i);
            
            ImagePartition blackPartition = new ImagePartition(current, BLACK_Y, next - current, BLACK_HEIGHT);
            ImagePartition whitePartition = new ImagePartition(current, WHITE_Y, next - current, WHITE_HEIGHT);
            imagePartitionsBuilder = imagePartitionsBuilder.put(Player.BLACK, pieceType, blackPartition)
                                                           .put(Player.WHITE, pieceType, whitePartition);
        }
        return imagePartitionsBuilder.build();
    }
    
    public ImageView getPawn  (Player player, double desiredSize) { return getPiece(player, PAWN  , desiredSize); }
    public ImageView getKnight(Player player, double desiredSize) { return getPiece(player, KNIGHT, desiredSize); }
    public ImageView getRook  (Player player, double desiredSize) { return getPiece(player, BISHOP, desiredSize); }
    public ImageView getBishop(Player player, double desiredSize) { return getPiece(player, ROOK  , desiredSize); }
    public ImageView getQueen (Player player, double desiredSize) { return getPiece(player, QUEEN , desiredSize); }
    public ImageView getKing  (Player player, double desiredSize) { return getPiece(player, KING  , desiredSize); }
    
    public ImageView getPiece(Player player, PieceType pieceType, double desiredSize) {
        ImagePartition imagePartition = IMAGE_PARTITIONS.get(player, pieceType);
        ImageView imageView = new ImageView(new WritableImage(tiles.getPixelReader(), imagePartition.x, imagePartition.y, 
                                                              imagePartition.width, imagePartition.height));
        imageView.setFitWidth(desiredSize);
        imageView.setFitHeight(desiredSize);
        imageView.setPreserveRatio(true);

        double ratio = imagePartition.width / ((double) imagePartition.height);
        if (ratio < 1) {
            double realWidth = ratio * desiredSize;
            imageView.setTranslateX((desiredSize - realWidth) / 2);
        }
        return imageView;
    }
    
    private static class ImagePartition {
        public final int width, height;
        public final int x, y;
        
        public ImagePartition(int x, int y, int width, int height) {
            Stream.of(x    , y     ).forEach(Check::isPositive);
            Stream.of(width, height).forEach(i -> Check.isGreaterThan(i, 0));
            
            this.width  = width;
            this.height = height;
            this.x      = x;
            this.y      = y;
        }
    }
}
