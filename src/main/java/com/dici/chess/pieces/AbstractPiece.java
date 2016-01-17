package miscellaneous.chess.pieces;

import com.dici.check.Check;

import miscellaneous.chess.model.Piece;
import miscellaneous.chess.model.PieceType;

abstract class AbstractPiece implements Piece {
    private final PieceType pieceType;

    public AbstractPiece(PieceType pieceType) { this.pieceType = Check.notNull(pieceType); }
    @Override public PieceType getPieceType() { return pieceType; }
}
