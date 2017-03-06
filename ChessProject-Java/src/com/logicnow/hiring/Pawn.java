package com.logicnow.hiring;

public class Pawn extends ChessPieceImpl {

	private int maxMoveStep;

	/**
	 * Constructor Initialises maxMoveStep and pieceType
	 *
	 * @param pieceColor
	 *            the {@link PieceColor} for this Pawn instance
	 */
	public Pawn(PieceColor pieceColor) {
		super(pieceColor);

		this.maxMoveStep = 2;
		this.pieceType = PieceType.PAWN;

	}

	/**
	 * <pre>
	 * Determines if the move requested is a valid Move forward for a Pawn chess piece.
	 * Checks that the maximum number of steps isn't exeeded and that the Y is changing by at least 1.
	 * If Y doesn't change this indicates a non moments or a movement on the X axis both of which
	 * are not valid. Logic only appies to {@linkplain MovementType#MOVE} type.
	 * </pre>
	 *
	 * @param newX
	 *            the proposed new X coordinate
	 * @param newY
	 *            the proposed new Y coordinate
	 * @return boolean
	 */
	private boolean isValidMoveForward(int newX, int newY) {

		int yDifference = newY - this.yCoordinate;

		// Check the number of steps isn't being exceeded
		if (Math.abs(yDifference) > this.maxMoveStep) {
			return false;
		}

		// Check piece is moving at least once square in the correct direction
		if (this.pieceColor == PieceColor.BLACK && yDifference >= 0) {
			return false;
		} else if (this.pieceColor == PieceColor.WHITE && yDifference <= 0) {
			return false;

		}

		return true;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logicnow.hiring.ChessPieceImpl#executeMove(com.logicnow.hiring.
	 * MovementType, int, int)
	 */
	@Override
	protected void executeMove(MovementType movementType, int newX, int newY) {
		super.executeMove(movementType, newX, newY);
		// Pawn has moved, can only move one space from now on
		this.maxMoveStep = 1;

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logicnow.hiring.ChessPieceImpl#isValidMove(com.logicnow.hiring.
	 * MovementType, int, int)
	 */
	/**
	 * <pre>
	 * Checks if the move requested is valid to the Pawn chess piece.
	 * <li>Performs abstract class checks</li>
	 * <li>If {@linkplain MovementType#MOVE} request, checks isValidMoveForward
	 * logic</li>
	 * <li>If {@linkplain MovementType#MOVE} request, checks the proposed new
	 * coordinates are not already occupued</li>
	 */
	@Override
	protected boolean isValidMove(MovementType movementType, int newX, int newY) {
		boolean rtn = false;

		if (super.isValidMove(movementType, newX, newY)) {
			// Pawns Can't Move onto occupied square
			// unless capturing where they can move onto occupied or unoccupied
			// (if en passant)
			// Can move 2 on first move and only 1 thereafter

			if (movementType.equals(MovementType.MOVE) && isValidMoveForward(newX, newY)
					&& !this.chessBoard.isSquareOccupied(newX, newY)) {
				rtn = true;
			}
		} else if (movementType.equals(MovementType.CAPTURE)) {
			throw new UnsupportedOperationException("Capture Not Implemented");
		}

		return rtn;
	}

}
