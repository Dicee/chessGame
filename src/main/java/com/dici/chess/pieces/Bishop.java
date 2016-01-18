package com.dici.chess.pieces;

import com.dici.chess.model.PieceType;
import com.dici.chess.moves.DiagonalMove;
import com.dici.chess.moves.MoveWithLength;

import java.util.Set;

public class Bishop extends AbstractPiece {
    public Bishop() { super(PieceType.BISHOP); }

    @Override public Set<? extends MoveWithLength> getMaximalMoves() { return DiagonalMove.allMaximalMoves(); }
}
