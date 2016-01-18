package com.dici.chess.moves;

import com.dici.chess.model.ChessBoard;
import com.dici.math.geometry.geometry2D.Delta;

import java.util.Set;

import static com.dici.collection.CollectionUtils.setOf;

public final class HorizontalMove extends MoveWithLength {
    public static Set<HorizontalMove> allMaximalMoves() { return allMovesFromLength(ChessBoard.BOARD_SIZE); }
    public static Set<HorizontalMove> allUnitMoves   () { return allMovesFromLength(1); }
    
    public static Set<HorizontalMove> allMovesFromLength(int length) {
        return setOf(new HorizontalMove(length), new HorizontalMove(-length));
    }
    
    public HorizontalMove(int length) { super(length); }

    @Override protected MoveWithLength buildFromLength(int length) { return new HorizontalMove(length); }
    @Override protected Delta normalizedDelta() { return new Delta(0, length / Math.abs(length)); }
}