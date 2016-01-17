package miscellaneous.chess.model;

import com.dici.math.geometry.geometry2D.ImmutablePoint;

public interface ChessBoardViewer {
    void handleInitialization(ImmutablePoint pos, Player player, PieceType pieceType);
    void handleMove(ImmutablePoint from, ImmutablePoint to);
}
