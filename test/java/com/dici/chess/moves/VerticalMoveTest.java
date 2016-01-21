package com.dici.chess.moves;

import com.dici.chess.model.Move;
import com.dici.chess.model.Player;
import com.dici.collection.richIterator.RichIntIterator;
import com.dici.math.geometry.geometry2D.Delta;
import com.dici.math.geometry.geometry2D.ImmutablePoint;
import org.junit.Test;
import utils.TestReadableBoard;

import java.util.Set;

import static com.dici.chess.model.ChessBoard.BOARD_SIZE;
import static com.dici.collection.CollectionUtils.setOf;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class VerticalMoveTest {
    @Test
    public void testMaximalMoves() {
        assertThat(VerticalMove.allMaximalMoves(), equalTo(setOf(maximalVerticalMove(1), maximalVerticalMove(-1))));
    }

    @Test
    public void testNormalizedDelta() {
        assertThat(new VerticalMove( 3).normalizedDelta(), equalTo(new Delta( 1, 0)));
        assertThat(new VerticalMove(-5).normalizedDelta(), equalTo(new Delta(-1, 0)));
    }

    @Test
    public void testMove() {
        assertThat(new VerticalMove(  4).execute(new ImmutablePoint(0, 1)), equalTo(new ImmutablePoint( 4, 1)));
        assertThat(new VerticalMove(- 2).execute(new ImmutablePoint(0, 1)), equalTo(new ImmutablePoint(-2, 1)));
    }

    @Test
    public void testSubAllowedMoves_freeOfObtsacles() {
        ImmutablePoint origin          = new ImmutablePoint(4, 1);
        Set<Move>      allowedSubMoves = new VerticalMove(3).getAllowedSubMoves(origin, Player.BLACK, new TestReadableBoard());
        assertThat(allowedSubMoves, equalTo(allMovesUpToLength(3)));
    }

    @Test
    public void testSubAllowedMoves_outOfBoard() {
        ImmutablePoint origin          = new ImmutablePoint(5, 1);
        Set<Move>      allowedSubMoves = new VerticalMove(3).getAllowedSubMoves(origin, Player.BLACK, new TestReadableBoard());
        assertThat(allowedSubMoves, equalTo(allMovesUpToLength(2)));
    }

    @Test
    public void testSubAllowedMoves_blockedByOtherPiece() {
        ImmutablePoint    origin          = new ImmutablePoint(5, 1);
        TestReadableBoard board           = new TestReadableBoard().withOccupied(Player.BLACK, new ImmutablePoint(7, 1));
        Set<Move>         allowedSubMoves = new VerticalMove(3).getAllowedSubMoves(origin, Player.BLACK, board);
        assertThat(allowedSubMoves, equalTo(allMovesUpToLength(1)));
    }

    @Test
    public void testSubAllowedMoves_killOtherPlayerPiece() {
        ImmutablePoint    origin          = new ImmutablePoint(5, 1);
        TestReadableBoard board           = new TestReadableBoard().withOccupied(Player.WHITE, new ImmutablePoint(7, 1));
        Set<Move>         allowedSubMoves = new VerticalMove(3).getAllowedSubMoves(origin, Player.BLACK, board);
        assertThat(allowedSubMoves, equalTo(allMovesUpToLength(2)));
    }

    @Test
    public void testSubAllowedMoves_alignedEnemiesKillsOnlyFirst() {
        ImmutablePoint    origin          = new ImmutablePoint(5, 1);
        TestReadableBoard board           = new TestReadableBoard().withOccupied(Player.WHITE, new ImmutablePoint(7, 1), new ImmutablePoint(6, 1));
        Set<Move>         allowedSubMoves = new VerticalMove(3).getAllowedSubMoves(origin, Player.BLACK, board);
        assertThat(allowedSubMoves, equalTo(allMovesUpToLength(1)));
    }

    private static VerticalMove maximalVerticalMove(int dx) { return new VerticalMove(dx * BOARD_SIZE); }
    public  static Set<VerticalMove> allMovesUpToLength(int length) { return RichIntIterator.closedRange(1, length).map(VerticalMove::new).toSet();}
}
