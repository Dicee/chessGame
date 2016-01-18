package com.dici.chess.model;

import com.dici.math.geometry.geometry2D.Delta;
import com.dici.math.geometry.geometry2D.ImmutablePoint;

import java.util.Set;

public interface Move {
    Delta delta();
    Set<Move> getAllowedSubMoves(ImmutablePoint origin, Player currentPlayer, ReadableBoard board);
    
    default ImmutablePoint execute(ImmutablePoint pos) { return pos.move(delta()); }
    default boolean isLegal(ImmutablePoint pos, Player currentPlayer, ReadableBoard board) { 
        return board.isLegal(execute(pos), currentPlayer);
    }
}
