package com.dici.chess.moves;

import com.dici.check.Check;
import com.dici.chess.model.Move;
import com.dici.chess.model.Player;
import com.dici.chess.model.ReadableBoard;
import com.dici.math.geometry.geometry2D.Delta;
import com.dici.math.geometry.geometry2D.ImmutablePoint;

import java.util.HashSet;
import java.util.Set;

public abstract class MoveWithLength implements Move {
    protected final int length;

    public MoveWithLength(int length) { 
        Check.isGreaterThan(length, 0);
        this.length = length;
    }
    
    @Override
    public final Set<Move> getAllowedSubMoves(ImmutablePoint origin, Player currentPlayer, ReadableBoard board) {
        Set<Move> moves = new HashSet<>();
        Move      move  = buildFromLength(1);
        for (int i = 1; i <= length && move.landsOnFreeCell(origin, board); move = buildFromLength(++i)) moves.add(move);
        if (move.isAttack(origin, currentPlayer, board)) moves.add(move);
        return moves;
    }

    @Override
    public final Delta delta() { return normalizedDelta().times(length); }

    protected abstract Move buildFromLength(int length);
    protected abstract Delta normalizedDelta();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MoveWithLength that = (MoveWithLength) o;
        return length == that.length;
    }

    @Override
    public int hashCode() { return length; }
}