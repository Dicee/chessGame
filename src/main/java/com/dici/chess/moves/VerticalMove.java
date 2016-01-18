package com.dici.chess.moves;

import com.dici.chess.model.ChessBoard;
import com.dici.math.geometry.geometry2D.Delta;

import java.util.Set;

import static com.dici.collection.CollectionUtils.setOf;

public final class VerticalMove extends MoveWithLength {
    public static Set<VerticalMove> allMaximalMoves() { return allMovesFromLength(ChessBoard.BOARD_SIZE); }
    public static Set<VerticalMove> allUnitMoves   () { return allMovesFromLength(1); }
    
    public static Set<VerticalMove> allMovesFromLength(int length) {
        return setOf(new VerticalMove(length), new VerticalMove(-length));
    }
    
    public VerticalMove(int length) { super(length); }

    @Override protected MoveWithLength buildFromLength(int length) { return new VerticalMove(length); }
    @Override protected Delta normalizedDelta() { return new Delta(1, 0); }
}