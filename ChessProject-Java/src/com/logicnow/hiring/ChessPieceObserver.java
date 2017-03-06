package com.logicnow.hiring;

public interface ChessPieceObserver {
	/**
	 * Will update the Observer's state in reation to the state change of the
	 * ChessPiece passed in
	 * 
	 * @param updatedPiece
	 *            updated ChessPiece instance
	 */
	public void update(ChessPiece updatedPiece);

}
