package com.dici.chess.pieces;

import static com.dici.collection.CollectionUtils.listOf;

import java.util.LinkedList;
import java.util.List;

import com.dici.chess.model.Piece;
import com.dici.chess.model.Player;
import com.dici.chess.model.ReadableBoard;
import com.dici.chess.moves.DiagonalMove;
import com.dici.chess.moves.MoveWithLength;
import com.dici.chess.moves.VerticalMove;
import com.dici.chess.utils.ImmutablePoint;

public class Pawn implements Piece {
    @Override
    public List<MoveWithLength> specialRuleAllowedMoves(ImmutablePoint origin, Player currentPlayer, ReadableBoard board, boolean isFirstTurn) {
        List<MoveWithLength> moves = new LinkedList<>();
        
        int signum = currentPlayer == Player.BLACK ? 1 : -1;
        moves.add(new VerticalMove(signum));
        
        if (isFirstTurn) moves.add(new VerticalMove(2 * signum));
        
        List<MoveWithLength> attackMoves = listOf(new DiagonalMove(1, signum, 1), new DiagonalMove(-1, signum, 1));
        for (MoveWithLength attackMove : attackMoves) {
            if (attackMove.isLegal(origin, currentPlayer, board)) moves.add(attackMove);
        }
        return moves;
    }
}