package com.dici.chess.pieces;

import com.dici.chess.model.Move;
import com.dici.chess.model.PieceType;
import com.dici.chess.model.Player;
import com.dici.chess.model.ReadableBoard;
import com.dici.chess.moves.DiagonalMove;
import com.dici.chess.moves.VerticalMove;
import com.dici.collection.richIterator.RichIterators;
import com.dici.math.geometry.geometry2D.ImmutablePoint;

import java.util.Set;

import static com.dici.collection.CollectionUtils.union;

public class Pawn extends AbstractPiece {
    public Pawn() { super(PieceType.PAWN); }
    
    @Override
    public Set<Move> specialRuleAllowedMoves(ImmutablePoint origin, Player currentPlayer, ReadableBoard board, boolean isFirstTurn) {
        int signum = currentPlayer == Player.BLACK ? 1 : -1;
        return union(getRegularMoves(origin, currentPlayer, board, isFirstTurn, signum),
                     getAttackMoves(origin, currentPlayer, board, signum));
    }

    private Set<Move> getRegularMoves(ImmutablePoint origin, Player currentPlayer, ReadableBoard board, boolean isFirstTurn, int signum) {
        return new VerticalMove(signum * (isFirstTurn ? 2 : 1)).getAllowedSubMoves(origin, currentPlayer, board);
    }

    private Set<DiagonalMove> getAttackMoves(ImmutablePoint origin, Player currentPlayer, ReadableBoard board, int signum) {
        return RichIterators.of(new DiagonalMove(1, signum), new DiagonalMove(-1, signum))
                            .filter(attackMove -> attackMove.isAttack(origin, currentPlayer, board))
                            .toSet();
    }
}