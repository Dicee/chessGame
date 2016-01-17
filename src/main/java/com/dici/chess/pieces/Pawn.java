package com.dici.chess.pieces;

import static com.dici.collection.CollectionUtils.listOf;

import java.util.LinkedList;
import java.util.List;

import com.dici.chess.model.PieceType;
import com.dici.chess.model.Player;
import com.dici.chess.model.ReadableBoard;
import com.dici.chess.moves.DiagonalMove;
import com.dici.chess.moves.MoveWithLength;
import com.dici.chess.moves.VerticalMove;

import com.dici.math.geometry.geometry2D.ImmutablePoint;

public class Pawn extends AbstractPiece {
    public Pawn() { super(PieceType.PAWN); }
    
    @Override
    public List<MoveWithLength> specialRuleAllowedMoves(ImmutablePoint origin, Player currentPlayer, ReadableBoard board, boolean isFirstTurn) {
        List<MoveWithLength> moves = new LinkedList<>();
        
        int signum = currentPlayer == Player.BLACK ? 1 : -1;
        moves.add(new VerticalMove(signum));
        
        if (isFirstTurn) moves.add(new VerticalMove(2 * signum));
        
        List<MoveWithLength> attackMoves = listOf(new DiagonalMove(1, signum, 1), new DiagonalMove(-1, signum, 1));
        for (MoveWithLength attackMove : attackMoves) {
            ImmutablePoint pos = attackMove.move(origin);
            if (board.isLegal(pos, currentPlayer) && board.isOccupied(pos)) moves.add(attackMove);
        }
        return moves;
    }
}