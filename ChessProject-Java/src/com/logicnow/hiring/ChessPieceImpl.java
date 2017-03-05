package com.logicnow.hiring;

public abstract class ChessPieceImpl implements ChessPiece {
	protected ChessBoard chessBoard;
	protected int previousXCoordinate;
	protected int previousYCoordinate;
	protected int xCoordinate;
	protected int yCoordinate;
	protected PieceColor pieceColor;
	protected PieceType pieceType;

	public PieceType getPieceType() {
		return pieceType;
	}

	public ChessPieceImpl(PieceColor pieceColor) {
		this.pieceColor = pieceColor;
		this.previousXCoordinate = -1;
		this.previousYCoordinate = -1;
		this.xCoordinate = -1;
		this.yCoordinate = -1;

	}

	public void attachChessBoard(ChessBoard chessBoard) {
		this.chessBoard = chessBoard;

	}

	public int getPreviousXCoordinate() {
		return this.previousXCoordinate;
	}

	public int getXCoordinate() {
		return xCoordinate;
	}

	public void setXCoordinate(int value) {
		this.xCoordinate = value;
	}

	public int getPreviousYCoordinate() {
		return this.previousYCoordinate;
	}

	public int getYCoordinate() {
		return yCoordinate;
	}

	public void setYCoordinate(int value) {
		this.yCoordinate = value;
	}

	public PieceColor getPieceColor() {
		return this.pieceColor;
	}

	public void Move(MovementType movementType, int newX, int newY) {
		// Template for executing Move
		// Implementations may be overridden by concrete classes
		// As validation and move logic is different for each piece
		if (isValidMove(movementType, newX, newY)) {

			// Execute the move, changing piece state
			executeMove(movementType, newX, newY);

			// Notify all interested objects of change
			notifyObservers();

		}
	}

	protected boolean isValidMove(MovementType movementType, int newX, int newY) {
		boolean rtn = false;
		// Check with Board if valid position
		if (this.chessBoard.IsLegalBoardPosition(newX, newY)) {

			// Perform Global checks
			// If destination is same as current position not valid
			if (this.xCoordinate == newX && this.yCoordinate == newY) {
				rtn = false;
			} else {
				rtn = true;
			}

		}
		return rtn;

	}

	protected void executeMove(MovementType movementType, int newX, int newY) {
		// Only MOVE implemented
		//TODO:Implement CAPTURE
		if (movementType.equals(MovementType.MOVE)) {
			this.previousXCoordinate = this.xCoordinate;
			this.previousYCoordinate = this.yCoordinate;
			this.xCoordinate = newX;
			this.yCoordinate = newY;
		}
	}

	protected void notifyObservers() {
		this.chessBoard.update(this);
	}

	@Override
	public String toString() {
		return CurrentPositionAsString();
	}

	protected String CurrentPositionAsString() {
		String eol = System.lineSeparator();
		return String.format("Current X: {1}{0}Current Y: {2}{0}Piece Color: {3}", eol, xCoordinate, yCoordinate,
				pieceColor);
	}

}
