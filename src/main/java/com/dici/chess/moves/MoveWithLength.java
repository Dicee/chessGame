package com.dici.chess.moves;

import com.dici.check.Check;
import com.dici.chess.model.Move;
import com.dici.chess.model.Player;
import com.dici.chess.model.ReadableBoard;
import com.dici.collection.richIterator.RichIntIterator;
import com.dici.math.geometry.geometry2D.Delta;
import com.dici.math.geometry.geometry2D.ImmutablePoint;

import java.util.Set;

import static com.dici.chess.model.ChessBoard.BOARD_SIZE;

public abstract class MoveWithLength implements Move {
    protected final int length;

    public MoveWithLength(int length) { 
        // Check.isBetween is right-exclusive
        Check.isBetween(1, length, BOARD_SIZE + 1, "Move larger than board size : " + length + " (maximum " + BOARD_SIZE + ")");
        this.length = length;
    }
    
    @Override
    public final Set<Move> getAllowedSubMoves(ImmutablePoint origin, Player currentPlayer, ReadableBoard board) {
        Delta delta = normalizedDelta();
        return RichIntIterator.range(1, length)
                              .takeWhile(steps -> board.isLegal(origin.move(delta.times(steps)), currentPlayer))
                              .map(this::buildFromLength)
                              .toSet();
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