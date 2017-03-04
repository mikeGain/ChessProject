package com.logicnow.hiring;

public abstract class ChessPieceImpl implements ChessPiece{
	protected ChessBoard chessBoard;
	protected int xCoordinate;
	protected int yCoordinate;
	protected PieceColor pieceColor;
	protected PieceType pieceType;

	public PieceType getPieceType() {
		return pieceType;
	}

	public ChessPieceImpl(PieceColor pieceColor) {
		this.pieceColor = pieceColor;
		this.xCoordinate = -1;
		this.yCoordinate = -1;

	}

	
	public void setChessBoard(ChessBoard chessBoard) {
		this.chessBoard = chessBoard;
	}

	public int getXCoordinate() {
		return xCoordinate;
	}

	public void setXCoordinate(int value) {
		this.xCoordinate = value;
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
	
	public boolean hasMoved(int fromX, int fromY){
		return !(this.xCoordinate == fromX && this.yCoordinate == fromY);
	}
	
	public void Move(MovementType movementType, int newX, int newY) {
		// Template for executing Move
		// Implementations may be overridden by concrete classes
		if (isValidMove(movementType, newX, newY)) {
			executeMove(movementType, newX, newY);
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
			}
			else{
				rtn = true;
			}

		}
		return rtn;

	}

	protected  void executeMove(MovementType movementType, int newX, int newY){
		this.xCoordinate = newX;
		this.yCoordinate = newY;
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
