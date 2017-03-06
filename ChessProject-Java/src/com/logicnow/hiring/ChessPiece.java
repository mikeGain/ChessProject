package com.logicnow.hiring;

public interface ChessPiece {

	/**
	 * Returns the current {@link PieceType} for the instance
	 *
	 * @return {@link PieceType}
	 */
	public PieceType getPieceType();

	/**
	 * Returns the current {@link PieceColor} for the instance
	 *
	 * @return {@link PieceColor}
	 */
	public PieceColor getPieceColor();

	/**
	 * <pre>
	 * Attach a {@link ChessBoard} observer to the ChessPiece. The ChessBoard
	 * state will be used to determine validity of each move request.
	 *
	 * The ChessBoard status will then need updating for each state change on
	 * this instance
	 *
	 *
	 * @param chessBoard
	 *            ChessBoard
	 */
	public void attachChessBoard(ChessBoard chessBoard);

	/**
	 * Returns the currently set previousXCoordinate value
	 *
	 * @return int
	 */
	public int getPreviousXCoordinate();

	/**
	 * Returns the currently set xCoordinate value
	 *
	 * @return int
	 */
	public int getXCoordinate();

	/**
	 * Sets a new xCoordinate value
	 *
	 * @param value
	 *            new xCoordinate value
	 */
	public void setXCoordinate(int value);

	/**
	 * Returns the currently set previousYCoordinate value
	 *
	 * @return int
	 */
	public int getPreviousYCoordinate();

	/**
	 * Returns the currently set yCoordinate value
	 *
	 * @return int
	 */
	public int getYCoordinate();

	/**
	 * Sets a new yCoordinate value
	 *
	 * @param value
	 *            new yCoordinate value
	 */
	public void setYCoordinate(int value);

	/**
	 * <pre>
	 * Validates and if valid executes the requested move
	 * </pre>
	 *
	 * @param movementType
	 *            the requested {@link MovementType}
	 * @param newX
	 *            proposed new X Coordinate
	 * @param newY
	 *            proposed new Y Coordinate
	 */
	public void Move(MovementType movementType, int newX, int newY);

}
