package miscellaneous.chess.pieces;

import static com.dici.collection.CollectionUtils.unionList;

import java.util.List;

import miscellaneous.chess.model.Move;
import miscellaneous.chess.model.PieceType;
import miscellaneous.chess.moves.DiagonalMove;
import miscellaneous.chess.moves.HorizontalMove;
import miscellaneous.chess.moves.VerticalMove;

public class Queen extends AbstractPiece {
    public Queen() { super(PieceType.QUEEN); }
    
    @Override
    public List<Move> getMaximalMoves() {
        return unionList(DiagonalMove.allMaximalMoves(), HorizontalMove.allMaximalMoves(), VerticalMove.allMaximalMoves());
    }
}