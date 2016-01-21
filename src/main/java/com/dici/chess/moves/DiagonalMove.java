package com.dici.chess.moves;

import com.dici.check.Check;
import com.dici.chess.model.ChessBoard;
import com.dici.collection.richIterator.RichIterators;
import com.dici.math.geometry.geometry2D.Delta;

import java.util.Set;

public final class DiagonalMove extends MoveWithLength {
    public static Set<DiagonalMove> allMaximalMoves() { return allMovesFromLength(ChessBoard.BOARD_SIZE); }
    public static Set<DiagonalMove> allUnitMoves   () { return allMovesFromLength(1); }
    
    public static Set<DiagonalMove> allMovesFromLength(int length) {
        return RichIterators.of(Orientation.values()).map(orientation -> new DiagonalMove(orientation, length)).toSet();
    }
    
    private final Orientation orientation;

    public DiagonalMove(Orientation orientation, int length) { this(orientation.dx * length, orientation.dy * length); }

    public DiagonalMove(int dx, int dy) {
        super(Math.abs(dx));
        Check.areEqual(Math.abs(dx), Math.abs(dy), "dx and dy should have the same absolute value");
        int abs = Math.abs(dx);
        this.orientation = Orientation.get(dx / abs, dy / abs);
    }

    @Override protected MoveWithLength buildFromLength(int length) { return new DiagonalMove(orientation, length); }
    @Override protected Delta normalizedDelta() { return new Delta(orientation.dx, orientation.dy); }

    public static enum Orientation {
        TOP_LEFT     (-1, -1),
        BOTTOM_LEFT  ( 1, -1),
        TOP_RIGHT    (-1,  1),
        BOTTOM_RIGHT ( 1,  1);

        private final int dx, dy;

        private Orientation(int dx, int dy) {
            this.dx = dx;
            this.dy = dy;
        }
        
        public static Orientation get(int dx, int dy) {
            return RichIterators.of(values())
                                .findFirst(orientation -> orientation.dx == dx && orientation.dy == dy)
                                .orElseThrow(() -> new IllegalArgumentException("No Orientation with delta " + new Delta(dx, dy)));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DiagonalMove that = (DiagonalMove) o;
        return orientation == that.orientation;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + orientation.hashCode();
        return result;
    }
}