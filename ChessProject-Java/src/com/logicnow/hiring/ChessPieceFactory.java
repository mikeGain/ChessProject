package com.logicnow.hiring;

public class ChessPieceFactory {

	public ChessPiece createChessPiece(PieceType type, PieceColor colour){
		ChessPiece newPiece;
		switch (type) {
        case PAWN: 
        	newPiece = new Pawn(colour);
            break;
        default: 
        	newPiece = null;
            break;
		}
		return newPiece;
		
}
}
