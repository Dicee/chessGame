package com.dici.chess.model;

import com.dici.collection.richIterator.RichIterators;
import com.dici.math.geometry.geometry2D.ImmutablePoint;

import java.util.Set;

import static com.dici.collection.CollectionUtils.union;
import static java.util.Collections.emptySet;

public interface Piece {
    PieceType getPieceType();
    
    default Set<? extends Move> getMaximalMoves() { return emptySet(); }
    
    default Set<? extends Move> specialRuleAllowedMoves(ImmutablePoint origin, Player currentPlayer, ReadableBoard board) {
        return emptySet();
    }
    
    default Set<Move> getAllowedMoves(ImmutablePoint origin, Player currentPlayer, ReadableBoard board) {
        Set<Move> actualAllowedMoves = RichIterators.fromCollection(getMaximalMoves())
                                                    .flatMap(move -> move.getAllowedSubMoves(origin, currentPlayer, board))
                                                    .toSet();
        return union(actualAllowedMoves, specialRuleAllowedMoves(origin, currentPlayer, board));
    }
}