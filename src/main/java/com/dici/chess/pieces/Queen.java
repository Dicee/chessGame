package com.dici.chess.pieces;

import static com.dici.collection.CollectionUtils.unionList;

import java.util.List;

import com.dici.chess.model.Move;
import com.dici.chess.model.PieceType;
import com.dici.chess.moves.DiagonalMove;
import com.dici.chess.moves.HorizontalMove;
import com.dici.chess.moves.VerticalMove;

public class Queen extends AbstractPiece {
    public Queen() { super(PieceType.QUEEN); }
    
    @Override
    public List<Move> getMaximalMoves() {
        return unionList(DiagonalMove.allMaximalMoves(), HorizontalMove.allMaximalMoves(), VerticalMove.allMaximalMoves());
    }
}