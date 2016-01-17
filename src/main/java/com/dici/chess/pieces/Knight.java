package miscellaneous.chess.pieces;

import java.util.List;

import miscellaneous.chess.model.PieceType;
import miscellaneous.chess.moves.KnightMove;

public class Knight extends AbstractPiece {
    public Knight() { super(PieceType.KNIGHT); }
    
    @Override public List<KnightMove> getMaximalMoves() { return KnightMove.allPossibleMoves(); }
}
