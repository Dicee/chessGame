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

    private final int orientation;

    public HorizontalMove(int length) {
        super(Math.abs(length));
        this.orientation = length / Math.abs(length);
    }

    @Override protected MoveWithLength buildFromLength(int length) { return new HorizontalMove(orientation * length); }
    @Override protected Delta normalizedDelta() { return new Delta(0, orientation); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        HorizontalMove that = (HorizontalMove) o;
        return orientation == that.orientation;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + orientation;
        return result;
    }
}