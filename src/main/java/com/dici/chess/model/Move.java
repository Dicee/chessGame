package com.dici.chess.model;

import com.dici.math.geometry.geometry2D.Delta;
import com.dici.math.geometry.geometry2D.ImmutablePoint;

import java.util.Set;

public interface Move {
    Delta delta();
    Set<Move> getAllowedSubMoves(ImmutablePoint origin, Player currentPlayer, ReadableBoard board);
    
    default ImmutablePoint execute(ImmutablePoint origin) { return origin.move(delta()); }
    default boolean isLegal(ImmutablePoint origin, Player currentPlayer, ReadableBoard board) { return board.isLegal(execute(origin), currentPlayer); }
    default boolean isAttack(ImmutablePoint origin, Player currentPlayer, ReadableBoard board) { return board.isLegalAttack(execute(origin), currentPlayer); }
    default boolean landsOnFreeCell(ImmutablePoint origin, ReadableBoard board) {
        ImmutablePoint pos = execute(origin);
        return ChessBoard.isInBoard(pos) && !board.isOccupied(pos);
    }
}
