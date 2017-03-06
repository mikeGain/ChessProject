package com.logicnow.hiring;

public interface ChessBoard extends ChessPieceObserver {
	public static int MAX_BOARD_WIDTH = 8;
	public static int MAX_BOARD_HEIGHT = 8;

	/**
	 * <pre>
	 * Adds a Chess Piece to the Chessboard if coordinates are valid or space isn't already occupied.
	 *
	 * Coordinates are invalid if they are out of range (negative or greater than max) or if space is already
	 * occupied by another piece
	 * </pre>
	 *
	 * @param piece
	 *            {@link ChessPiece} Instance in "non added" state
	 * @param xCoordinate
	 *            X position on board
	 * @param yCoordinate
	 *            Y position on board
	 */
	public void Add(ChessPiece piece, int xCoordinate, int yCoordinate);

	/**
	 * <pre>
	 * Determines if the position on the board represented by the X and Y coordinates provided is
	 * occupied by another {@link ChessPiece} or not.
	 * </pre>
	 *
	 * @param xCoordinate
	 *            X position on board
	 * @param yCoordinate
	 *            Y position on board
	 * @return
	 */
	public boolean isSquareOccupied(int xCoordinate, int yCoordinate);

	/**
	 * <pre>
	 * Determines if the supplied coordinates represent a legal board position or not.
	 * Legal coordinates must be within the range of the board i.e. not negative and not greater
	 * than the number of squares on the X or Y axis
	 * </pre>
	 *
	 * @param xCoordinate
	 *            X position on board
	 * @param yCoordinate
	 *            Y position on board
	 * @return
	 */

	public boolean IsLegalBoardPosition(int xCoordinate, int yCoordinate);

	/**
	 * Moves a {@link ChessPiece} identified by the "from" coordinates to the
	 * "to" coordinates Move will not be carried out if the piece determines
	 * that the move request is not valid for itself e.g. Pawns cannot move
	 * sideways.
	 *
	 * @param moveType
	 *            A value of {@link MovementType}
	 * @param fromX
	 *            X position of piece to be moved
	 * @param fromY
	 *            Y position of peice to be moved
	 * @param toX
	 *            Intended destination X position
	 * @param toY
	 *            Intended destination Y position
	 */
	public void MovePiece(MovementType moveType, int fromX, int fromY, int toX, int toY);

}