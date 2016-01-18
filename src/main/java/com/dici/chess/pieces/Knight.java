package com.dici.chess.pieces;

import com.dici.chess.model.PieceType;
import com.dici.chess.moves.KnightMove;

import java.util.Set;

public class Knight extends AbstractPiece {
    public Knight() { super(PieceType.KNIGHT); }
    
    @Override public Set<KnightMove> getMaximalMoves() { return KnightMove.allPossibleMoves(); }
}
