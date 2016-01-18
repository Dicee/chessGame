package com.dici.chess.moves;

import com.dici.check.Check;
import com.dici.chess.model.Move;
import com.dici.chess.model.Player;
import com.dici.chess.model.ReadableBoard;
import com.dici.math.geometry.geometry2D.Delta;
import com.dici.math.geometry.geometry2D.ImmutablePoint;

import java.util.HashSet;
import java.util.Set;

import static com.dici.chess.model.ChessBoard.BOARD_SIZE;

public abstract class MoveWithLength implements Move {
    protected final int length;

    public MoveWithLength(int length) { 
        Check.notEqual(length, 0, "Move cannot be of length 0");
        // Check.isBetween is right-exclusive 
        Check.isBetween(- BOARD_SIZE, length, BOARD_SIZE + 1, "Move larger than board size : " + length + " (maximum " + BOARD_SIZE + ")");
        this.length = length;
    }
    
    @Override
    public final Set<Move> getAllowedSubMoves(ImmutablePoint origin, Player currentPlayer, ReadableBoard board) {
        Set<Move> moves = new HashSet<>();
        
        Delta delta = normalizedDelta();
        ImmutablePoint pos = origin.move(delta);
        for (int steps = 1; board.isLegal(pos, currentPlayer) && steps <= length; steps++) 
            moves.add(buildFromLength(steps));
        return moves;
    }
    
    @Override
    public final Delta delta() { return normalizedDelta().times(length); }

    protected abstract MoveWithLength buildFromLength(int length);
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