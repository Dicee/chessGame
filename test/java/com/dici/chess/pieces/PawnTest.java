package com.dici.chess.pieces;

import com.dici.chess.model.Move;
import com.dici.chess.model.Player;
import com.dici.chess.model.ReadableBoard;
import com.dici.chess.moves.DiagonalMove;
import com.dici.chess.moves.VerticalMove;
import com.dici.chess.moves.VerticalMoveTest;
import com.dici.math.geometry.geometry2D.ImmutablePoint;
import org.junit.Before;
import org.junit.Test;
import utils.TestReadableBoard;

import java.util.Set;

import static com.dici.chess.moves.DiagonalMove.Orientation.*;
import static com.dici.collection.CollectionUtils.setOf;
import static java.util.Collections.emptySet;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class PawnTest {
    private Pawn pawn;

    @Before
    public void setUp() { pawn = new Pawn(); }

    @Test
    public void testMaximalMoves() {
        assertThat(pawn.getMaximalMoves(), is(empty()));
    }

    @Test
    public void testSpecialRules_firstAndRegularTurn() {
        checkSpecialRules(new ImmutablePoint(1, 1), Player.BLACK, new TestReadableBoard(), VerticalMoveTest.allMovesUpToLength(2));
        checkSpecialRules(new ImmutablePoint(2, 1), Player.BLACK, new TestReadableBoard(), VerticalMoveTest.allMovesUpToLength(1));
    }

    @Test
    public void testSpecialRules_verticalMove_doesNotAttack_firstTurn() {
        TestReadableBoard board         = new TestReadableBoard().withOccupied(Player.WHITE, new ImmutablePoint(2, 2), new ImmutablePoint(3, 2));
        Set<Move>         expectedMoves = emptySet();
        checkSpecialRules(new ImmutablePoint(1, 2), Player.BLACK, board, expectedMoves);
    }

    @Test
    public void testSpecialRules_verticalMove_doesNotAttack_regularTurn() {
        TestReadableBoard board         = new TestReadableBoard().withOccupied(Player.WHITE, new ImmutablePoint(3, 2));
        Set<Move>         expectedMoves = emptySet();
        checkSpecialRules(new ImmutablePoint(2, 2), Player.BLACK, board, expectedMoves);
    }

    @Test
    public void testSpecialRules_verticalMove_getsBlockedBySamePlayer_firstTurn_twoAligned() {
        TestReadableBoard board         = new TestReadableBoard().withOccupied(Player.BLACK, new ImmutablePoint(2, 2), new ImmutablePoint(3, 2));
        Set<Move>         expectedMoves = emptySet();
        checkSpecialRules(new ImmutablePoint(1, 2), Player.BLACK, board, expectedMoves);
    }

    @Test
    public void testSpecialRules_verticalMove_getsBlockedBySamePlayer_firstTurn_onePieceTwoStepsAway() {
        TestReadableBoard board         = new TestReadableBoard().withOccupied(Player.BLACK, new ImmutablePoint(3, 2));
        Set<Move>         expectedMoves = setOf(new VerticalMove(1));
        checkSpecialRules(new ImmutablePoint(1, 2), Player.BLACK, board, expectedMoves);
    }

    @Test
    public void testSpecialRules_verticalMove_getsBlockedBySamePlayer_regularTurn() {
        TestReadableBoard board         = new TestReadableBoard().withOccupied(Player.BLACK, new ImmutablePoint(3, 2));
        Set<Move>         expectedMoves = emptySet();
        checkSpecialRules(new ImmutablePoint(2, 2), Player.BLACK, board, expectedMoves);
    }

    @Test
    public void testSpecialRules_attack_oneAttack_black() {
        TestReadableBoard board         = new TestReadableBoard().withOccupied(Player.WHITE, new ImmutablePoint(3, 1), new ImmutablePoint(3, 2));
        Set<Move>         expectedMoves = setOf(new DiagonalMove(BOTTOM_RIGHT, 1));
        checkSpecialRules(new ImmutablePoint(2, 1), Player.BLACK, board, expectedMoves);
    }

    @Test
    public void testSpecialRules_attack_twoAttacks_black() {
        TestReadableBoard board         = new TestReadableBoard().withOccupied(Player.WHITE, new ImmutablePoint(3, 0), new ImmutablePoint(3, 2));
        Set<Move>         expectedMoves = setOf(new VerticalMove(1), new DiagonalMove(BOTTOM_LEFT, 1), new DiagonalMove(BOTTOM_RIGHT, 1));
        checkSpecialRules(new ImmutablePoint(2, 1), Player.BLACK, board, expectedMoves);
    }

    @Test
    public void testSpecialRules_attack_oneAttack_white() {
        TestReadableBoard board         = new TestReadableBoard().withOccupied(Player.BLACK, new ImmutablePoint(0, 2), new ImmutablePoint(3, 3));
        Set<Move>         expectedMoves = setOf(new VerticalMove(-1), new DiagonalMove(TOP_LEFT, 1));
        checkSpecialRules(new ImmutablePoint(4, 4), Player.WHITE, board, expectedMoves);
    }

    @Test
    public void testSpecialRules_attack_doesNotAttackSamePlayer() {
        TestReadableBoard board         = new TestReadableBoard().withOccupied(Player.BLACK, new ImmutablePoint(3, 1), new ImmutablePoint(3, 3));
        Set<Move>         expectedMoves = setOf(new VerticalMove(1));
        checkSpecialRules(new ImmutablePoint(2, 2), Player.BLACK, board, expectedMoves);
    }

    @Test
    public void testSpecialRules_mixed_oneAttackAndRegular() {
        TestReadableBoard board         = new TestReadableBoard().withOccupied(Player.WHITE, new ImmutablePoint(2, 2));
        Set<Move>         expectedMoves = setOf(new VerticalMove(1), new DiagonalMove(BOTTOM_RIGHT, 1), new VerticalMove(1), new VerticalMove(2));
        checkSpecialRules(new ImmutablePoint(1, 1), Player.BLACK, board, expectedMoves);
    }

    private void checkSpecialRules(ImmutablePoint origin, Player player, ReadableBoard board, Set<? extends Move> expectedMoves) {
        assertThat(pawn.specialRuleAllowedMoves(origin, player, board), equalTo(expectedMoves));
    }
}