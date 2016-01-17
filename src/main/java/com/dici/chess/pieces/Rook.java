package miscellaneous.chess.pieces;

import static com.dici.collection.CollectionUtils.unionList;

import java.util.List;

import miscellaneous.chess.model.PieceType;
import miscellaneous.chess.moves.HorizontalMove;
import miscellaneous.chess.moves.MoveWithLength;
import miscellaneous.chess.moves.VerticalMove;

public class Rook extends AbstractPiece {
    public Rook() { super(PieceType.ROOK); }
    
    @Override 
    public List<MoveWithLength> getMaximalMoves() { return unionList(HorizontalMove.allMaximalMoves(), VerticalMove.allMaximalMoves()); }
}
