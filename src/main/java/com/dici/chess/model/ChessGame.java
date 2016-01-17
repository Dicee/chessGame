package com.dici.chess.model;

import java.util.List;

import com.dici.check.Check;
import com.dici.math.geometry.geometry2D.ImmutablePoint;

public class ChessGame implements ReadableBoard {
    private final ChessBoard board;
    private       Player     currentPlayer = Player.WHITE;
    
    private int turn = 0;
    
    public ChessGame() { this(null); }
    public ChessGame(ChessBoardViewer boardViewer) { this.board = new ChessBoard(boardViewer); }

    public void nextTurn() {
        currentPlayer = currentPlayer == Player.WHITE ? Player.BLACK : Player.WHITE;
    }
    
    public void registerBoardViewer(ChessBoardViewer boardViewer) {
        board.registerBoardViewer(boardViewer);
    }

    @Override public Player getOccupier(int x, int y) { return board.getOccupier(x, y); }

    public List<Move> getAllowedMoves(ImmutablePoint origin) {
        Check.isTrue(board.isOccupied(origin), "No player on cell " + origin);
        return getPiece(origin).getAllowedMoves(origin, currentPlayer, board, turn == 0);
    }
    
    private Piece getPiece(ImmutablePoint pos) { return board.getPiece(pos); }
    
    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}
