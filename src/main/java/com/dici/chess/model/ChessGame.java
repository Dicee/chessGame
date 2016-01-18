package com.dici.chess.model;

import com.dici.check.Check;
import com.dici.math.geometry.geometry2D.ImmutablePoint;

import java.util.List;

public class ChessGame implements ReadableBoard {
    private final ChessBoard board;

    private int turn = 0;
    
    public ChessGame() { this(null); }
    public ChessGame(ChessBoardViewer boardViewer) { this.board = new ChessBoard(boardViewer); }

    private void nextTurn() { turn++; }
    
    public void registerBoardViewer(ChessBoardViewer boardViewer) {
        board.registerBoardViewer(boardViewer);
    }

    @Override public Player getOccupier(int x, int y) { return board.getOccupier(x, y); }

    public List<Move> getAllowedMoves(ImmutablePoint origin) {
        Check.isTrue(board.isOccupied(origin), "No player on cell " + origin);
        return getPiece(origin).getAllowedMoves(origin, getCurrentPlayer(), board, turn < 2);
    }
    
    private Piece getPiece(ImmutablePoint pos) { return board.getPiece(pos); }
    
    public Player getCurrentPlayer() {
        return turn % 2 == 0 ? Player.WHITE : Player.BLACK;
    }
}
