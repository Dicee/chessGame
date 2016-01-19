package com.dici.chess.pieces;

import com.dici.chess.model.Move;
import com.dici.chess.model.Player;
import com.dici.chess.model.ReadableBoard;
import com.dici.chess.moves.DiagonalMove;
import com.dici.chess.moves.MoveWithLength;
import com.dici.chess.moves.VerticalMove;
import com.dici.chess.moves.VerticalMoveTest;
import com.dici.math.geometry.geometry2D.ImmutablePoint;
import org.junit.Before;
import org.junit.Test;
import utils.TestReadableBoard;

import java.util.Set;

import static com.dici.chess.moves.DiagonalMove.Orientation.BOTTOM_LEFT;
import static com.dici.chess.moves.DiagonalMove.Orientation.BOTTOM_RIGHT;
import static com.dici.collection.CollectionUtils.setOf;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class PawnTest {
    private Pawn pawn;

    @Before
    public void setUp() {
        pawn = new Pawn();
    }

    @Test
    public void testMaximalMoves() {
        assertThat(pawn.getMaximalMoves(), is(empty()));
    }

    @Test
    public void testSpecialRules_firstTurn() {
        checkSpecialRules(new ImmutablePoint(1, 0), Player.BLACK, new TestReadableBoard(), true , VerticalMoveTest.allMovesUpToLength(2));
        checkSpecialRules(new ImmutablePoint(1, 0), Player.BLACK, new TestReadableBoard(), false, VerticalMoveTest.allMovesUpToLength(1));
    }

    @Test
    public void testSpecialRules_attack_twoAttacks() {
        TestReadableBoard board = new TestReadableBoard().withOccupied(Player.WHITE, new ImmutablePoint(0, 2), new ImmutablePoint(2, 2));
        Set<MoveWithLength> expectedMoves = setOf(new VerticalMove(1), new DiagonalMove(BOTTOM_LEFT, 1), new DiagonalMove(BOTTOM_RIGHT, 1));
        checkSpecialRules(new ImmutablePoint(1, 1), Player.BLACK, board, false, expectedMoves);
    }

    @Test
    public void testSpecialRules_attack_oneAttack() {
        TestReadableBoard board = new TestReadableBoard().withOccupied(Player.WHITE, new ImmutablePoint(0, 2), new ImmutablePoint(3, 3));
        Set<MoveWithLength> expectedMoves = setOf(new VerticalMove(1), new DiagonalMove(BOTTOM_LEFT, 1));
        checkSpecialRules(new ImmutablePoint(1, 1), Player.BLACK, board, false, expectedMoves);
    }

    @Test
    public void testSpecialRules_attack_doesNotAttackSamePlayer() {
        TestReadableBoard board = new TestReadableBoard().withOccupied(Player.BLACK, new ImmutablePoint(0, 2), new ImmutablePoint(3, 3));
        Set<MoveWithLength> expectedMoves = setOf(new VerticalMove(1));
        checkSpecialRules(new ImmutablePoint(1, 1), Player.BLACK, board, false, expectedMoves);
    }

    private void checkSpecialRules(ImmutablePoint origin, Player player, ReadableBoard board, boolean isFirstTurn, Set<? extends Move> expectedMoves) {
        assertThat(pawn.specialRuleAllowedMoves(origin, player, board, isFirstTurn), equalTo(expectedMoves));
    }
}
