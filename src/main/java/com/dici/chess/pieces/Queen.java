package com.dici.chess.pieces;

import com.dici.chess.model.Move;
import com.dici.chess.model.PieceType;
import com.dici.chess.moves.DiagonalMove;
import com.dici.chess.moves.HorizontalMove;
import com.dici.chess.moves.VerticalMove;

import java.util.Set;

import static com.dici.collection.CollectionUtils.union;

public class Queen extends AbstractPiece {
    public Queen() { super(PieceType.QUEEN); }
    
    @Override
    public Set<Move> getMaximalMoves() {
        return union(DiagonalMove.allMaximalMoves(), HorizontalMove.allMaximalMoves(), VerticalMove.allMaximalMoves());
    }
}