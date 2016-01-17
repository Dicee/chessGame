package miscellaneous.chess.pieces;

import static com.dici.collection.CollectionUtils.unionList;

import java.util.List;

import miscellaneous.chess.model.PieceType;
import miscellaneous.chess.moves.DiagonalMove;
import miscellaneous.chess.moves.HorizontalMove;
import miscellaneous.chess.moves.MoveWithLength;
import miscellaneous.chess.moves.VerticalMove;

public class King extends AbstractPiece {
    public King() { super(PieceType.KING); }
    
    @Override
    public List<MoveWithLength> getMaximalMoves() {
        return unionList(DiagonalMove.allUnitMoves(), HorizontalMove.allUnitMoves(), VerticalMove.allUnitMoves());
    }
}