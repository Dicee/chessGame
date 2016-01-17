package miscellaneous.chess.pieces;

import java.util.List;

import miscellaneous.chess.model.PieceType;
import miscellaneous.chess.moves.DiagonalMove;
import miscellaneous.chess.moves.MoveWithLength;

public class Bishop extends AbstractPiece {
    public Bishop() { super(PieceType.BISHOP); }

    @Override public List<? extends MoveWithLength> getMaximalMoves() { return DiagonalMove.allMaximalMoves(); }
}
