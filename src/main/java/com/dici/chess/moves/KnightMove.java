package com.dici.chess.moves;

import com.dici.chess.model.Move;
import com.dici.chess.model.Player;
import com.dici.chess.model.ReadableBoard;
import com.dici.math.geometry.geometry2D.Delta;
import com.dici.math.geometry.geometry2D.ImmutablePoint;

import java.util.Set;
import java.util.stream.Stream;

import static com.dici.check.Check.notNull;
import static java.util.Collections.emptySet;
import static java.util.Collections.singleton;
import static java.util.stream.Collectors.toSet;

public class KnightMove implements Move {
    public static Set<KnightMove> allPossibleMoves() { return Stream.of(Orientation.values()).map(KnightMove::new).collect(toSet()); }
    
    private final Orientation orientation;

    public KnightMove(Orientation orientation) { this.orientation = notNull(orientation); }

    @Override
    public Delta delta() { return new Delta(orientation.dx, orientation.dy); }

    @Override
    public Set<Move> getAllowedSubMoves(ImmutablePoint origin, Player currentPlayer, ReadableBoard board) {
        return isLegal(origin, currentPlayer, board) ? singleton(this) : emptySet();
    }

    public static enum Orientation {
        STAND_RIGHT(-1, 2),           // . 
                                      // .
                                      // . .
                                     
        STAND_LEFT(1, 2),             //   . 
                                      //   .
                                      // . .
                         
        LIE_RIGHT(2, -1),              // .     
                                       // . . .
                                
        LIE_LEFT(-2, -1),              //     . 
                                       // . . .
                                
        STAND_RIGHT_REV(-1, -2),       // . . 
                                       // .
                                       // .
                                
        STAND_LEFT_REV(1, -2),         // . . 
                                       //   .
                                       //   .
                                
        LIE_RIGHT_REV(2, 1),           // . . .
                                       // . 
                                
        LIE_LEFT_REV (-2, 1);           // . . .
                                       //     .
        public final int dx, dy;
        
        private Orientation(int dx, int dy) {
            this.dx = dx;
            this.dy = dy;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KnightMove that = (KnightMove) o;
        return orientation == that.orientation;
    }

    @Override
    public int hashCode() { return orientation.hashCode(); }
}