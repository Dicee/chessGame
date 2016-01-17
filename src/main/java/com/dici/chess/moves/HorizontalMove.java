package com.dici.chess.moves;

import static com.dici.collection.CollectionUtils.listOf;

import java.util.List;

import com.dici.chess.model.ChessBoard;
import com.dici.chess.utils.Delta;

public final class HorizontalMove extends MoveWithLength {
    public static List<HorizontalMove> allMaximalMoves() { return allMovesFromLength(ChessBoard.BOARD_SIZE); }
    public static List<HorizontalMove> allUnitMoves   () { return allMovesFromLength(1); }
    
    public static List<HorizontalMove> allMovesFromLength(int length) {
        return listOf(new HorizontalMove(length), new HorizontalMove(-length));
    }
    
    public HorizontalMove(int length) { super(length); }

    @Override protected MoveWithLength buildFromLength(int length) { return new HorizontalMove(length); }
    @Override protected Delta normalizedDelta() { return new Delta(0, 1); }
}