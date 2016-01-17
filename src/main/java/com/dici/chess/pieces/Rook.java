package com.dici.chess.pieces;

import static com.dici.collection.CollectionUtils.unionList;

import java.util.List;

import com.dici.chess.model.PieceType;
import com.dici.chess.moves.HorizontalMove;
import com.dici.chess.moves.MoveWithLength;
import com.dici.chess.moves.VerticalMove;

public class Rook extends AbstractPiece {
    public Rook() { super(PieceType.ROOK); }
    
    @Override 
    public List<MoveWithLength> getMaximalMoves() { return unionList(HorizontalMove.allMaximalMoves(), VerticalMove.allMaximalMoves()); }
}
