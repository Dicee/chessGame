package com.dici.chess.model;

import com.dici.check.Check;
import com.dici.math.geometry.geometry2D.ImmutablePoint;

import java.util.Set;

public class ChessGame implements ReadableBoard {
    private final ChessBoard board;

    private int turn = 0;
    
    public ChessGame() { this(null); }
    public ChessGame(ChessBoardViewer boardViewer) { this.board = new ChessBoard(boardViewer); }

    public void registerBoardViewer(ChessBoardViewer boardViewer) {
        board.registerBoardViewer(boardViewer);
    }

    @Override public Player getOccupier(int x, int y) { return board.getOccupier(x, y); }

    public void play(ImmutablePoint origin, ImmutablePoint destination) {
        validatePlayer(origin);
        validateMove(origin, destination);
        board.play(origin, destination);
        turn++;
    }

    private void validateMove(ImmutablePoint origin, ImmutablePoint to) {
        Piece piece = getPiece(origin);
        boolean isAllowedMove = getAllowedMoves(origin).stream().map(move -> move.execute(origin)).anyMatch(pos -> pos.equals(to));
        if (!isAllowedMove) throw new IllegalStateException("Illegal for piece " + piece + " to move from " + origin + " to " + to);
    }

    private void validatePlayer(ImmutablePoint origin) {
        Player player = getOccupier(origin);
        if (player != getCurrentPlayer()) throw new IllegalStateException("Current player is " + getCurrentPlayer() + ", not " + player);
    }

    public Set<Move> getAllowedMoves(ImmutablePoint origin) {
        Check.isTrue(board.isOccupied(origin), "No player on cell " + origin);
        return getPiece(origin).getAllowedMoves(origin, getCurrentPlayer(), board);
    }
    
    private Piece getPiece(ImmutablePoint pos) { return board.getPiece(pos); }
    
    public Player getCurrentPlayer() {
        return turn % 2 == 0 ? Player.WHITE : Player.BLACK;
    }
}
