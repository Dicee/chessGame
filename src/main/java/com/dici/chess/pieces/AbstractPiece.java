package com.dici.chess.pieces;

import com.dici.check.Check;
import com.dici.chess.model.Piece;
import com.dici.chess.model.PieceType;

abstract class AbstractPiece implements Piece {
    private final PieceType pieceType;

    public AbstractPiece(PieceType pieceType) { this.pieceType = Check.notNull(pieceType); }
    @Override public PieceType getPieceType() { return pieceType                         ; }
    @Override public String    toString    () { return getPieceType().name()             ; }
}
