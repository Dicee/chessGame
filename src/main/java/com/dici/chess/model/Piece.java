package com.dici.chess.model;

import com.dici.math.geometry.geometry2D.ImmutablePoint;

import java.util.Set;

import static com.dici.collection.CollectionUtils.union;
import static java.util.Collections.emptySet;
import static java.util.stream.Collectors.toSet;

public interface Piece {
    PieceType getPieceType();
    
    default Set<? extends Move> getMaximalMoves() { return emptySet(); }
    
    default Set<? extends Move> specialRuleAllowedMoves(ImmutablePoint origin, Player currentPlayer, ReadableBoard board, boolean isFirstTurn) {
        return emptySet();
    }
    
    default Set<Move> getAllowedMoves(ImmutablePoint origin, Player currentPlayer, ReadableBoard board, boolean isFirstTurn) {
        Set<Move> actualAllowedMoves =
                getMaximalMoves().stream()
                                 .flatMap(move -> move.getAllowedSubMoves(origin, currentPlayer, board).stream())
                                 .collect(toSet());
        return union(actualAllowedMoves, specialRuleAllowedMoves(origin, currentPlayer, board, isFirstTurn));
    }
}