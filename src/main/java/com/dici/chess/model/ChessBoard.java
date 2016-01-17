package miscellaneous.chess.model;

import static com.dici.check.Check.notNull;
import static com.dici.math.MathUtils.isBetween;
import miscellaneous.chess.pieces.Bishop;
import miscellaneous.chess.pieces.King;
import miscellaneous.chess.pieces.Knight;
import miscellaneous.chess.pieces.Pawn;
import miscellaneous.chess.pieces.Queen;
import miscellaneous.chess.pieces.Rook;

import com.dici.check.Check;
import com.dici.math.geometry.geometry2D.ImmutablePoint;

public class ChessBoard implements ReadableBoard {
    public static final int BOARD_SIZE = 8;
    
    public static boolean isLegal(ImmutablePoint pos) { return isLegal(pos.x, pos.y); }
    public static boolean isLegal(int x, int y) { return isBetween(0, x, BOARD_SIZE) && isBetween(0, y, BOARD_SIZE); }
    
    private final Cell[][] cells = new Cell[BOARD_SIZE][BOARD_SIZE];
    private ChessBoardViewer boardViewer;

    public ChessBoard() { this(null); }
    
    public ChessBoard(ChessBoardViewer boardViewer) {
        if (boardViewer != null) registerBoardViewer(boardViewer);
        
        for (Player player : Player.values()) {
            int baseRow  = player == Player.BLACK ? 0 : BOARD_SIZE - 1;
            int pawnsRow = baseRow + (player == Player.BLACK ? 1 : -1);
            
            setRooks       (player, baseRow );
            setKnights     (player, baseRow );
            setBishops     (player, baseRow );
            setKingAndQueen(player, baseRow );
            setPawns       (player, pawnsRow);
        }
    }
    
    private void setRooks(Player player, int row) {
        init(row, 0             , player, new Rook());
        init(row, BOARD_SIZE - 1, player, new Rook());
    }
    
    private void setKnights(Player player, int row) {
        init(row, 1             , player, new Knight());
        init(row, BOARD_SIZE - 2, player, new Knight());
    }
    
    private void setBishops(Player player, int row) {
        init(row, 2             , player, new Bishop());
        init(row, BOARD_SIZE - 3, player, new Bishop());
    }

    private void setKingAndQueen(Player player, int row) {
        init(row, 3             , player, new Queen());
        init(row, BOARD_SIZE - 4, player, new King ());
    }
    
    private void setPawns(Player player, int row) {
        for (int j = 0; j < BOARD_SIZE; j++) init(row, j, player, new Pawn());
    }
    
    private void init(int i, int j, Player player, Piece piece) {
        cells[i][j] = new Cell(player, piece);
        if (boardViewer != null) {
            boardViewer.handleInitialization(new ImmutablePoint(i, j), player, piece.getPieceType());
        }
    }

    @Override
    public Player getOccupier(int x, int y) {
        Check.isTrue(isLegal(x, y), "Illegal position : (" + x + ", " + y + ")");
        return cells[x][y] == null ? null : cells[x][y].player;
    }
    
    public Piece getPiece(ImmutablePoint pos) {
        return cells[pos.x][pos.y] == null ? null : cells[pos.x][pos.y].piece;
    }
    
    void registerBoardViewer(ChessBoardViewer boardViewer) { this.boardViewer = notNull(boardViewer); }
}