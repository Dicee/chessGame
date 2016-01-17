package com.dici.chess.guifx;

import static com.dici.math.geometry.GeometryUtils.closestDiscretePoint;
import static com.dici.chess.model.ChessBoard.BOARD_SIZE;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import com.dici.chess.model.ChessBoardViewer;
import com.dici.chess.model.ChessGame;
import com.dici.chess.model.PieceType;
import com.dici.chess.model.Player;

import com.dici.math.MathUtils;
import com.dici.math.geometry.GeometryUtils;
import com.dici.math.geometry.geometry2D.ImmutablePoint;

public class ChessGameFX extends Application implements ChessBoardViewer {
    private static final int TILE_SIZE = 100;

    private Stage            primaryStage;
    private Rectangle2D      screenBounds;

    private GridPane         gridPane;
    private TilesLoader      tilesLoader;

    private ChessGame        chessGame;
    private Tile[][]         tiles;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Screen screen = Screen.getPrimary();
        this.primaryStage = primaryStage;
        this.screenBounds = screen.getVisualBounds();
        this.gridPane     = new GridPane();

        setGlobalEventHandler(gridPane);
        initTiles();

        this.chessGame = new ChessGame(this);

        Scene scene = new Scene(gridPane, 0, 0);
        primaryStage.setScene(scene);

        double height = screenBounds.getHeight();
        double width  = screenBounds.getWidth();
        int paneSize  = 8 * TILE_SIZE;

        primaryStage.setX((width  - paneSize) / 2);
        primaryStage.setY((height - paneSize) / 2);
        primaryStage.setWidth(paneSize);
        primaryStage.setHeight(paneSize);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void initTiles() {
        this.tilesLoader  = new TilesLoader();
        this.tiles        = new Tile[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++)
            for (int j = 0; j < BOARD_SIZE; j++) {
                Tile tile = (i + j) % 2 == 0 ? Tile.whiteTile(TILE_SIZE) : Tile.blackTile(TILE_SIZE);
                tiles[i][j] = tile;
                gridPane.add(tile, j, i);
            }
    }

    @Override
    public void handleInitialization(ImmutablePoint pos, Player player, PieceType pieceType) {
        tiles[pos.x][pos.y].setGraphic(tilesLoader.getPiece(player, pieceType, TILE_SIZE));
    }

    @Override
    public void handleMove(ImmutablePoint from, ImmutablePoint to) {
    }

    private void setGlobalEventHandler(Node root) {
        root.addEventHandler(MouseEvent.MOUSE_CLICKED, ev -> {
            int x = (int) Math.floor(ev.getSceneX() / TILE_SIZE);
            int y = (int) Math.floor(ev.getSceneY() / TILE_SIZE);
            unselectAll();
            
            ImmutablePoint pos = gridToBoard(new ImmutablePoint(x, y));
            if (chessGame.getOccupier(pos) == chessGame.getCurrentPlayer()) {
                tiles[pos.x][pos.y].select();
                chessGame.getAllowedMoves(pos).stream().map(move -> move.move(pos))
                                                       .forEach(coord -> tiles[coord.x][coord.y].preselect());
            }
        });
    }
    
    private void unselectAll() {
        for (int i = 0; i < BOARD_SIZE; i++)
            for (int j = 0; j < BOARD_SIZE; j++) tiles[j][i].unselect();
    }

    private static ImmutablePoint boardToGrid(ImmutablePoint pos) { return new ImmutablePoint(pos.y, pos.x); }
    // does the same thing as above, but keeping it in case it changes later
    private static ImmutablePoint gridToBoard(ImmutablePoint pos) { return boardToGrid(pos)                ; }
    
    public static void main(String[] args) {
        launch(args);
    }
}