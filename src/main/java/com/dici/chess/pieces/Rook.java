package com.dici.chess.pieces;

import com.dici.chess.model.PieceType;
import com.dici.chess.moves.HorizontalMove;
import com.dici.chess.moves.MoveWithLength;
import com.dici.chess.moves.VerticalMove;

import java.util.Set;

import static com.dici.collection.CollectionUtils.union;

public class Rook extends AbstractPiece {
    public Rook() { super(PieceType.ROOK); }
    
    @Override 
    public Set<MoveWithLength> getMaximalMoves() { return union(HorizontalMove.allMaximalMoves(), VerticalMove.allMaximalMoves()); }
}
