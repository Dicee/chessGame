package com.dici.chess.moves;

import com.dici.chess.model.Move;
import com.dici.chess.model.Player;
import com.dici.collection.richIterator.RichIterators;
import com.dici.math.geometry.geometry2D.Delta;
import com.dici.math.geometry.geometry2D.ImmutablePoint;
import org.junit.Test;
import utils.TestReadableBoard;

import java.util.Set;

import static com.dici.chess.moves.KnightMove.Orientation.*;
import static com.dici.collection.CollectionUtils.setOf;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class KnightMoveTest {
    @Test
    public void testMaximalMoves() {
        Set<Delta> deltas         = RichIterators.fromCollection(KnightMove.allPossibleMoves()).map(Move::delta).toSet();
        Set<Delta> expectedDeltas = setOf(new Delta(-1, 2), new Delta(-1, -2), new Delta(1, 2), new Delta(1, -2),
                                          new Delta(-2, 1), new Delta(-2, -1), new Delta(2, 1), new Delta(2, -1));
        assertThat(deltas, equalTo(expectedDeltas));
    }

    @Test
    public void testMove() {
        assertThat(new KnightMove(LIE_LEFT   ).execute(new ImmutablePoint(0, 1)), equalTo(new ImmutablePoint(-2, 0)));
        assertThat(new KnightMove(STAND_RIGHT).execute(new ImmutablePoint(0, 1)), equalTo(new ImmutablePoint(-1, 3)));
    }

    @Test
    public void testSubAllowedMoves_outOfBoard() {
        ImmutablePoint origin          = new ImmutablePoint(1, 0);
        Set<Move>      allowedSubMoves = new KnightMove(STAND_LEFT_REV).getAllowedSubMoves(origin, Player.BLACK, new TestReadableBoard());
        assertThat(allowedSubMoves, is(empty()));
    }

    @Test
    public void testSubAllowedMoves_insideBoard() {
        ImmutablePoint origin          = new ImmutablePoint(1, 2);
        KnightMove     knightMove      = new KnightMove(STAND_LEFT_REV);
        Set<Move>      allowedSubMoves = knightMove.getAllowedSubMoves(origin, Player.BLACK, new TestReadableBoard());
        assertThat(allowedSubMoves, is(setOf(knightMove)));
    }

    @Test
    public void testSubAllowedMoves_blockedByOtherPiece() {
        ImmutablePoint    origin          = new ImmutablePoint(1, 2);
        TestReadableBoard board           = new TestReadableBoard().withOccupied(Player.BLACK, new ImmutablePoint(2, 0));
        KnightMove        knightMove      = new KnightMove(STAND_LEFT_REV);
        Set<Move>         allowedSubMoves = knightMove.getAllowedSubMoves(origin, Player.BLACK, board);
        assertThat(allowedSubMoves, is(empty()));
    }

    @Test
    public void testSubAllowedMoves_killOtherPlayerPiece() {
        ImmutablePoint    origin          = new ImmutablePoint(1, 2);
        TestReadableBoard board           = new TestReadableBoard().withOccupied(Player.WHITE, new ImmutablePoint(2, 0));
        KnightMove        knightMove      = new KnightMove(STAND_LEFT_REV);
        Set<Move>         allowedSubMoves = knightMove.getAllowedSubMoves(origin, Player.BLACK, board);
        assertThat(allowedSubMoves, equalTo(setOf(knightMove)));
    }
}
