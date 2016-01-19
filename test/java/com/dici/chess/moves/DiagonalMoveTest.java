package com.dici.chess.moves;

import com.dici.chess.model.Move;
import com.dici.chess.model.Player;
import com.dici.chess.moves.DiagonalMove.Orientation;
import com.dici.collection.richIterator.RichIntIterator;
import com.dici.math.geometry.geometry2D.Delta;
import com.dici.math.geometry.geometry2D.ImmutablePoint;
import org.junit.Test;
import utils.TestReadableBoard;

import java.util.Set;

import static com.dici.chess.model.ChessBoard.BOARD_SIZE;
import static com.dici.chess.moves.DiagonalMove.Orientation.TOP_LEFT;
import static com.dici.collection.CollectionUtils.listOf;
import static com.dici.collection.CollectionUtils.setOf;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class DiagonalMoveTest {
    @Test
    public void testMaximalMoves() {
        Set<DiagonalMove> allMoves = setOf(maximalDiagonalMove( 1,  1), maximalDiagonalMove(1, -1),
                                           maximalDiagonalMove(-1, -1), maximalDiagonalMove(-1, 1));
        assertThat(DiagonalMove.allMaximalMoves(), equalTo(allMoves));
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorFailsOnNonDiagonalDelta() { new DiagonalMove(-1, 2); }

    @Test
    public void testMove() {
        assertThat(new DiagonalMove(  4, 4).execute(new ImmutablePoint(0, 1)), equalTo(new ImmutablePoint( 4, 5)));
        assertThat(new DiagonalMove(- 2, 2).execute(new ImmutablePoint(0, 1)), equalTo(new ImmutablePoint(-2, 3)));
    }

    @Test
    public void testSubAllowedMoves_freeOfObtsacles() {
        ImmutablePoint origin          = new ImmutablePoint(3, 3);
        Set<Move>      allowedSubMoves = new DiagonalMove(TOP_LEFT, 3).getAllowedSubMoves(origin, Player.BLACK, new TestReadableBoard());
        assertThat(allowedSubMoves, equalTo(allMovesUpToLength(TOP_LEFT, 3)));
    }

    @Test
    public void testSubAllowedMoves_outOfBoard() {
        ImmutablePoint origin          = new ImmutablePoint(2, 2);
        Set<Move>      allowedSubMoves = new DiagonalMove(TOP_LEFT, 3).getAllowedSubMoves(origin, Player.BLACK, new TestReadableBoard());
        assertThat(allowedSubMoves, equalTo(allMovesUpToLength(TOP_LEFT, 2)));
    }

    @Test
    public void testSubAllowedMoves_blockedByOtherPiece() {
        ImmutablePoint    origin          = new ImmutablePoint(2, 2);
        TestReadableBoard board           = new TestReadableBoard().withOccupied(Player.BLACK, new ImmutablePoint(0, 0));
        Set<Move>         allowedSubMoves = new DiagonalMove(TOP_LEFT, 3).getAllowedSubMoves(origin, Player.BLACK, board);
        assertThat(allowedSubMoves, equalTo(allMovesUpToLength(TOP_LEFT, 1)));
    }

    @Test
    public void testSubAllowedMoves_killOtherPlayerPiece() {
        ImmutablePoint    origin          = new ImmutablePoint(2, 2);
        TestReadableBoard board           = new TestReadableBoard().withOccupied(Player.WHITE, new ImmutablePoint(0, 0));
        Set<Move>         allowedSubMoves = new DiagonalMove(TOP_LEFT, 3).getAllowedSubMoves(origin, Player.BLACK, board);
        assertThat(allowedSubMoves, equalTo(allMovesUpToLength(TOP_LEFT, 2)));
    }

    @Test
    public void testNormalizedDelta() {
        for (int dx : listOf(3, -3))
            for (int dy : listOf(3, -3))
                assertThat(new DiagonalMove(dx, dy).normalizedDelta(), equalTo(new Delta(dx / Math.abs(dx), dy / Math.abs(dy))));
    }

    private static DiagonalMove maximalDiagonalMove(int dx, int dy) { return new DiagonalMove(dx * BOARD_SIZE, dy * BOARD_SIZE); }
    private static Set<DiagonalMove> allMovesUpToLength(Orientation orientation, int length) {
        return RichIntIterator.closedRange(1, length).map(dx -> new DiagonalMove(orientation, dx)).toSet();
    }
}