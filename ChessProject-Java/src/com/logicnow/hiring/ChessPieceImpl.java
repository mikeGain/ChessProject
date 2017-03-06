package com.logicnow.hiring;

public abstract class ChessPieceImpl implements ChessPiece {
	protected ChessBoard chessBoard;
	protected int previousXCoordinate;
	protected int previousYCoordinate;
	protected int xCoordinate;
	protected int yCoordinate;
	protected PieceColor pieceColor;
	protected PieceType pieceType;

	/**
	 * <pre>
	 * Performs move validity checks common to all chess pieces.
	 *
	 * Note:Each concrete implementation should implement their own move validity.
	 * </pre>
	 *
	 * @param movementType
	 *            the requested {@link MovementType}
	 * @param newX
	 *            proposed new X Coordinate
	 * @param newY
	 *            proposed new Y Coordinate
	 * @return boolean
	 */
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

	/**
	 * Performs the steps required to execute the move for this piece
	 *
	 * @param movementType
	 *            the requested {@link MovementType}
	 * @param newX
	 *            proposed new X Coordinate
	 * @param newY
	 *            proposed new Y Coordinate
	 */
	protected void executeMove(MovementType movementType, int newX, int newY) {
		// Only MOVE implemented
		// TODO:Implement CAPTURE
		if (movementType.equals(MovementType.MOVE)) {
			this.previousXCoordinate = this.xCoordinate;
			this.previousYCoordinate = this.yCoordinate;
			this.xCoordinate = newX;
			this.yCoordinate = newY;
		}
	}

	/**
	 * Notify any observers of this instance of a state change
	 */
	protected void notifyObservers() {
		this.chessBoard.update(this);
	}

	/**
	 * Generates a string format of the ChessPiece position
	 *
	 * @return String
	 */
	protected String CurrentPositionAsString() {
		String eol = System.lineSeparator();
		return String.format("Current X: {1}{0}Current Y: {2}{0}Piece Color: {3}", eol, xCoordinate, yCoordinate,
				pieceColor);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logicnow.hiring.ChessPiece#getPieceType()
	 */
	@Override
	public PieceType getPieceType() {
		return pieceType;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logicnow.hiring.ChessPiece#getPieceColor()
	 */
	@Override
	public PieceColor getPieceColor() {
		return this.pieceColor;
	}

	public ChessPieceImpl(PieceColor pieceColor) {
		this.pieceColor = pieceColor;
		this.previousXCoordinate = -1;
		this.previousYCoordinate = -1;
		this.xCoordinate = -1;
		this.yCoordinate = -1;

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logicnow.hiring.ChessPiece#attachChessBoard(com.logicnow.hiring.
	 * ChessBoard)
	 */
	@Override
	public void attachChessBoard(ChessBoard chessBoard) {
		this.chessBoard = chessBoard;

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logicnow.hiring.ChessPiece#getPreviousXCoordinate()
	 */
	@Override
	public int getPreviousXCoordinate() {
		return this.previousXCoordinate;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logicnow.hiring.ChessPiece#getXCoordinate()
	 */
	@Override
	public int getXCoordinate() {
		return xCoordinate;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logicnow.hiring.ChessPiece#setXCoordinate(int)
	 */
	@Override
	public void setXCoordinate(int value) {
		this.xCoordinate = value;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logicnow.hiring.ChessPiece#getPreviousYCoordinate()
	 */
	@Override
	public int getPreviousYCoordinate() {
		return this.previousYCoordinate;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logicnow.hiring.ChessPiece#getYCoordinate()
	 */
	@Override
	public int getYCoordinate() {
		return yCoordinate;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logicnow.hiring.ChessPiece#setYCoordinate(int)
	 */
	@Override
	public void setYCoordinate(int value) {
		this.yCoordinate = value;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.logicnow.hiring.ChessPiece#Move(com.logicnow.hiring.MovementType,
	 * int, int)
	 */
	/**
	 *
	 */
	@Override
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

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	/**
	 * <pre>
	 * Returns a string in the following format
	 * Current X: [X POSITION VALUE]
	 * Current Y: [Y POSITION VALUE]
	 * Piece Color: [PIECE COLOUR VALUE]
	 * </pre>
	 */
	@Override
	public String toString() {
		return CurrentPositionAsString();
	}

}
