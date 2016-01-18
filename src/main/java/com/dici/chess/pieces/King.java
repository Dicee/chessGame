package com.dici.chess.pieces;

import com.dici.chess.model.PieceType;
import com.dici.chess.moves.DiagonalMove;
import com.dici.chess.moves.HorizontalMove;
import com.dici.chess.moves.MoveWithLength;
import com.dici.chess.moves.VerticalMove;

import java.util.Set;

import static com.dici.collection.CollectionUtils.union;

public class King extends AbstractPiece {
    public King() { super(PieceType.KING); }
    
    @Override
    public Set<MoveWithLength> getMaximalMoves() {
        return union(DiagonalMove.allUnitMoves(), HorizontalMove.allUnitMoves(), VerticalMove.allUnitMoves());
    }
}
