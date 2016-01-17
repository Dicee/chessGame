package miscellaneous.chess.model;

import java.util.List;

import com.dici.math.geometry.geometry2D.Delta;
import com.dici.math.geometry.geometry2D.ImmutablePoint;

public interface Move {
    Delta delta();
    List<Move> getAllowedSubMoves(ImmutablePoint origin, Player currentPlayer, ReadableBoard board);
    
    default ImmutablePoint move(ImmutablePoint pos) { return pos.move(delta()); }
    default boolean isLegal(ImmutablePoint pos, Player currentPlayer, ReadableBoard board) { 
        return board.isLegal(move(pos), currentPlayer); 
    }
}