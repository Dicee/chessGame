package com.dici.chess.moves;

import com.dici.chess.model.ChessBoard;
import com.dici.math.geometry.geometry2D.Delta;

import java.util.Set;

import static com.dici.collection.CollectionUtils.setOf;

public final class VerticalMove extends MoveWithLength {
    public static Set<VerticalMove> allMaximalMoves   (          ) { return allMovesFromLength(ChessBoard.BOARD_SIZE)                 ; }
    public static Set<VerticalMove> allUnitMoves      (          ) { return allMovesFromLength(1)                                     ; }
    public static Set<VerticalMove> allMovesFromLength(int length) { return setOf(new VerticalMove(length), new VerticalMove(-length)); }

    private final int orientation;

    public VerticalMove(int length) {
        super(Math.abs(length));
        this.orientation = length / Math.abs(length);
    }

    @Override protected MoveWithLength buildFromLength(int length) { return new VerticalMove(orientation * length); }
    @Override protected Delta normalizedDelta() { return new Delta(orientation, 0); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        VerticalMove that = (VerticalMove) o;
        return orientation == that.orientation;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + orientation;
        return result;
    }
}