package com.logicnow.hiring;

public class Pawn extends ChessPieceImpl {

	private int maxMoveStep;
	
	
	public Pawn(PieceColor pieceColor) {
		super(pieceColor);
		
		this.maxMoveStep = 2;	
		this.pieceType = PieceType.PAWN;
		
	}

	@Override
	protected void executeMove(MovementType movementType, int newX, int newY) {
		super.executeMove(movementType, newX, newY);
		this.maxMoveStep = 1;

	}
	
	private boolean isValidMoveForward(int newX, int newY){
		if (this.xCoordinate != newX){
			return false;
		}
		
		int yDifference = newY - this.yCoordinate;
		
		if  (Math.abs(yDifference) > this.maxMoveStep){
			return false;
		}
		
		if (this.pieceColor == PieceColor.BLACK && yDifference >= 0 ){
			return false;
		}
		else if (this.pieceColor == PieceColor.WHITE &&  yDifference <= 0){
			return false;
			
		}
		
		return true;
	}
	
	protected boolean isValidMove(MovementType movementType, int newX, int newY) {
		boolean rtn = false;

		if (super.isValidMove(movementType, newX, newY)) {
			// Pawns Can't Move onto occupied square
			// unless capturing where they can move onto occupied or unoccupied
			// (if en passant)
			// Can move 2 on first move and only 1 thereafter

			if (movementType.equals(MovementType.MOVE) && isValidMoveForward( newX,  newY)
					&& !this.chessBoard.isSquareOccupied(newX, newY)) {
				rtn = true;
			}
		} else if (movementType.equals(MovementType.CAPTURE)) {
			throw new UnsupportedOperationException("Capture Not Implemented");
		}

		return rtn;
	}

	

}
