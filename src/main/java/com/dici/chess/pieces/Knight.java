package com.dici.chess.pieces;

import java.util.List;

import com.dici.chess.model.PieceType;
import com.dici.chess.moves.KnightMove;

public class Knight extends AbstractPiece {
    public Knight() { super(PieceType.KNIGHT); }
    
    @Override public List<KnightMove> getMaximalMoves() { return KnightMove.allPossibleMoves(); }
}
