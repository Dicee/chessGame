package com.dici.chess.pieces;

import java.util.List;

import com.dici.chess.model.Piece;
import com.dici.chess.moves.KnightMove;

public class Knight implements Piece {
    @Override public List<KnightMove> getMaximalMoves() { return KnightMove.allPossibleMoves(); }
}
