package com.logicnow.hiring;

public class ChessPieceFactory {

	/**
	 * Creates and returns an instance of the ChessPiece indicated by the
	 * supplied {@link PieceType}.
	 * 
	 * @param type
	 *            {@link PieceType}
	 * @param colour
	 *            {@link PieceColor}
	 * @return {@link ChessPiece}
	 */
	public ChessPiece createChessPiece(PieceType type, PieceColor colour) {
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
