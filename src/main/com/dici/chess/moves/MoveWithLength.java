package com.dici.chess.moves;

import static com.dici.chess.model.ChessBoard.BOARD_SIZE;

import java.util.LinkedList;
import java.util.List;

import com.dici.chess.model.Move;
import com.dici.chess.model.Player;
import com.dici.chess.model.ReadableBoard;
import com.dici.chess.utils.Delta;
import com.dici.chess.utils.ImmutablePoint;

import com.dici.check.Check;

public abstract class MoveWithLength implements Move {
    protected final int length;

    public MoveWithLength(int length) { 
        // Check.isBetween is right-exclusive 
        Check.areDifferent(length, 0, "Move cannot be of length 0");
        Check.isBetween(- BOARD_SIZE, length, BOARD_SIZE + 1, "Move larger than board size : " + length + " (maximum " + BOARD_SIZE + ")");
        this.length = length;
    }
    
    @Override
    public final List<Move> getAllowedSubMoves(ImmutablePoint origin, Player currentPlayer, ReadableBoard board) {
        List<Move> moves = new LinkedList<>();
        
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
}