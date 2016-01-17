package com.dici.chess.pieces;

import static com.dici.collection.CollectionUtils.unionList;

import java.util.List;

import com.dici.chess.model.Move;
import com.dici.chess.model.Piece;
import com.dici.chess.moves.DiagonalMove;
import com.dici.chess.moves.HorizontalMove;
import com.dici.chess.moves.VerticalMove;

public class Queen implements Piece {
    @Override
    public List<Move> getMaximalMoves() {
        return unionList(DiagonalMove.allMaximalMoves(), HorizontalMove.allMaximalMoves(), VerticalMove.allMaximalMoves());
    }
}