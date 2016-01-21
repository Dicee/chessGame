package com.dici.chess.pieces;

import com.dici.chess.model.*;
import com.dici.chess.moves.DiagonalMove;
import com.dici.chess.moves.VerticalMove;
import com.dici.collection.richIterator.RichIterators;
import com.dici.math.geometry.geometry2D.ImmutablePoint;

import java.util.Set;

import static com.dici.collection.CollectionUtils.union;

public class Pawn extends AbstractPiece {
    public Pawn() { super(PieceType.PAWN); }
    
    @Override
    public Set<Move> specialRuleAllowedMoves(ImmutablePoint origin, Player currentPlayer, ReadableBoard board) {
        int     signum      = currentPlayer == Player.BLACK ? 1 : -1;
        boolean isFirstTurn = origin.x == (currentPlayer == Player.BLACK ? 1 : ChessBoard.BOARD_SIZE - 2);
        return union(getRegularMoves(origin, board, isFirstTurn, signum),
                     getAttackMoves (origin, currentPlayer, board, signum));
    }

    private Set<Move> getRegularMoves(ImmutablePoint origin, ReadableBoard board, boolean isFirstTurn, int signum) {
        return new VerticalMove(signum * (isFirstTurn ? 2 : 1)).getObstacleFreeSubMoves(origin, board);
    }

    private Set<DiagonalMove> getAttackMoves(ImmutablePoint origin, Player currentPlayer, ReadableBoard board, int signum) {
        return RichIterators.of(new DiagonalMove(signum, 1), new DiagonalMove(signum, -1))
                            .filter(attackMove -> attackMove.isAttack(origin, currentPlayer, board))
                            .toSet();
    }
}