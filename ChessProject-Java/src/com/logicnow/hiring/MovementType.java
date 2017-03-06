package com.logicnow.hiring;

/**
 * Move commands that can be issued to a ChessPiece
 * <li>{@link #MOVE}</li>
 * <li>{@link #CAPTURE}</li>
 *
 */
public enum MovementType {

	/**
	 * Perform a move from one set of coordinates to another
	 */
	MOVE,

	/**
	 * Perform a cpature manouvere moving from one set of coordinates to another
	 * in orde rto complete capture.
	 */
	CAPTURE;

}
