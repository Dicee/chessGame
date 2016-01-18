package com.dici.chess.pieces;

import com.dici.chess.model.PieceType;
import com.dici.chess.model.Player;
import com.dici.chess.model.ReadableBoard;
import com.dici.chess.moves.DiagonalMove;
import com.dici.chess.moves.MoveWithLength;
import com.dici.chess.moves.VerticalMove;
import com.dici.math.geometry.geometry2D.ImmutablePoint;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.dici.collection.CollectionUtils.listOf;

public class Pawn extends AbstractPiece {
    public Pawn() { super(PieceType.PAWN); }
    
    @Override
    public Set<MoveWithLength> specialRuleAllowedMoves(ImmutablePoint origin, Player currentPlayer, ReadableBoard board, boolean isFirstTurn) {
        Set<MoveWithLength> moves = new HashSet<>();
        
        int signum = currentPlayer == Player.BLACK ? 1 : -1;
        moves.add(new VerticalMove(signum));
        
        if (isFirstTurn) moves.add(new VerticalMove(2 * signum));
        
        List<MoveWithLength> attackMoves = listOf(new DiagonalMove(1, signum, 1), new DiagonalMove(-1, signum, 1));
        for (MoveWithLength attackMove : attackMoves) {
            ImmutablePoint pos = attackMove.execute(origin);
            if (board.isLegal(pos, currentPlayer) && board.isOccupied(pos)) moves.add(attackMove);
        }
        return moves;
    }
}