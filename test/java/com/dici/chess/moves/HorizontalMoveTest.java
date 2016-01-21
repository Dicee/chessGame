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

public class HorizontalMoveTest {
    @Test
    public void testMaximalMoves() {
        assertThat(HorizontalMove.allMaximalMoves(), equalTo(setOf(maximalHorizontalMove(1), maximalHorizontalMove(-1))));
    }

    @Test
    public void testNormalizedDelta() {
        assertThat(new HorizontalMove( 3).normalizedDelta(), equalTo(new Delta(0,  1)));
        assertThat(new HorizontalMove(-5).normalizedDelta(), equalTo(new Delta(0, -1)));
    }

    @Test
    public void testMove() {
        assertThat(new HorizontalMove(  4).execute(new ImmutablePoint(0, 1)), equalTo(new ImmutablePoint(0, 5)));
        assertThat(new HorizontalMove(- 2).execute(new ImmutablePoint(0, 1)), equalTo(new ImmutablePoint(0, -1)));
    }

    @Test
    public void testSubAllowedMoves_freeOfObtsacles() {
        ImmutablePoint origin          = new ImmutablePoint(1, 4);
        Set<Move>      allowedSubMoves = new HorizontalMove(3).getAllowedSubMoves(origin, Player.BLACK, new TestReadableBoard());
        assertThat(allowedSubMoves, equalTo(allMovesUpToLength(3)));
    }

    @Test
    public void testSubAllowedMoves_outOfBoard() {
        ImmutablePoint origin          = new ImmutablePoint(1, 5);
        Set<Move>      allowedSubMoves = new HorizontalMove(3).getAllowedSubMoves(origin, Player.BLACK, new TestReadableBoard());
        assertThat(allowedSubMoves, equalTo(allMovesUpToLength(2)));
    }

    @Test
    public void testSubAllowedMoves_blockedByOtherPiece() {
        ImmutablePoint    origin          = new ImmutablePoint(1, 5);
        TestReadableBoard board           = new TestReadableBoard().withOccupied(Player.BLACK, new ImmutablePoint(1, 7));
        Set<Move>         allowedSubMoves = new HorizontalMove(3).getAllowedSubMoves(origin, Player.BLACK, board);
        assertThat(allowedSubMoves, equalTo(allMovesUpToLength(1)));
    }

    @Test
    public void testSubAllowedMoves_killOtherPlayerPiece() {
        ImmutablePoint    origin          = new ImmutablePoint(1, 5);
        TestReadableBoard board           = new TestReadableBoard().withOccupied(Player.WHITE, new ImmutablePoint(1, 7));
        Set<Move>         allowedSubMoves = new HorizontalMove(3).getAllowedSubMoves(origin, Player.BLACK, board);
        assertThat(allowedSubMoves, equalTo(allMovesUpToLength(2)));
    }

    @Test
    public void testSubAllowedMoves_alignedEnemiesKillsOnlyFirst() {
        ImmutablePoint    origin          = new ImmutablePoint(1, 5);
        TestReadableBoard board           = new TestReadableBoard().withOccupied(Player.WHITE, new ImmutablePoint(1, 7), new ImmutablePoint(1, 6));
        Set<Move>         allowedSubMoves = new HorizontalMove(3).getAllowedSubMoves(origin, Player.BLACK, board);
        assertThat(allowedSubMoves, equalTo(allMovesUpToLength(1)));
    }

    private static HorizontalMove maximalHorizontalMove(int dx) { return new HorizontalMove(dx * BOARD_SIZE);}
    private static Set<HorizontalMove> allMovesUpToLength(int length) { return RichIntIterator.closedRange(1, length).map(HorizontalMove::new).toSet();}
}
