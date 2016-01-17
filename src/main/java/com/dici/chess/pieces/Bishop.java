package com.dici.chess.pieces;

import java.util.List;

import com.dici.chess.model.PieceType;
import com.dici.chess.moves.DiagonalMove;
import com.dici.chess.moves.MoveWithLength;

public class Bishop extends AbstractPiece {
    public Bishop() { super(PieceType.BISHOP); }

    @Override public List<? extends MoveWithLength> getMaximalMoves() { return DiagonalMove.allMaximalMoves(); }
}
