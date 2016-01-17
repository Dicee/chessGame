package com.dici.chess.pieces;

import static com.dici.collection.CollectionUtils.unionList;

import java.util.List;

import com.dici.chess.model.Piece;
import com.dici.chess.moves.DiagonalMove;
import com.dici.chess.moves.MoveWithLength;
import com.dici.chess.moves.VerticalMove;

public class Rook implements Piece {
    @Override 
    public List<MoveWithLength> getMaximalMoves() { return unionList(DiagonalMove.allMaximalMoves(), VerticalMove.allMaximalMoves()); }
}
