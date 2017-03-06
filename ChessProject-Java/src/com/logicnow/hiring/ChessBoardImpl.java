package com.logicnow.hiring;

import java.util.HashMap;

public class ChessBoardImpl implements ChessBoard {

	public static int MAX_BOARD_WIDTH = 8;
	public static int MAX_BOARD_HEIGHT = 8;
	public static int START_COORDINATE = 0;

	private HashMap<PieceType, Integer> maxPieceCounts;
	private HashMap<PieceColor, HashMap<PieceType, Integer>> pieceCounts;
	private ChessPiece[][] pieces;

	/**
	 * Constructor
	 */
	public ChessBoardImpl() {
		this.pieces = new ChessPiece[MAX_BOARD_WIDTH][MAX_BOARD_HEIGHT];

		// Initialise Data Structures to keep track of numbers of pieces
		this.pieceCounts = new HashMap<>();
		this.pieceCounts.put(PieceColor.BLACK, new HashMap<PieceType, Integer>());
		this.pieceCounts.put(PieceColor.WHITE, new HashMap<PieceType, Integer>());

		// Set the maximum numbers for each of the chess piece types;
		setMaxPieceCounts();

	}

	/**
	 * Setup the expected maximum number of pieces per {@link PieceType}
	 */
	private void setMaxPieceCounts() {
		this.maxPieceCounts = new HashMap<>();
		this.maxPieceCounts.put(PieceType.PAWN, 8);
		// TODO: extend for other piece types
		// e.g. this.maxPieceCounts.put(PieceType.KING, 1);
	}

	/**
	 * Increment the piece type count for the colour and type of the ChessPiece
	 * supplied
	 *
	 * @param piece
	 *            {@link ChessPiece} that identifies the type and colour to be
	 *            incrementd
	 * @param currentCount
	 *            The current piece count for the supplied type and colour.
	 */
	private void incrementPieceCount(ChessPiece piece, Integer currentCount) {
		this.pieceCounts.get(piece.getPieceColor()).put(piece.getPieceType(), new Integer(currentCount.intValue() + 1));

	}

	/**
	 * Will return a {@link ChessPiece} instance at the supplied coordinates or
	 * NULL if coordinates are not occupied.
	 *
	 * @param xCoordinate
	 *            X position on board
	 * @param yCoordinate
	 *            Y position on board
	 * @return
	 */
	private ChessPiece getPieceAt(int xCoordinate, int yCoordinate) {
		return pieces[xCoordinate][yCoordinate];
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logicnow.hiring.ChessBoard#Add(com.logicnow.hiring.ChessPiece,
	 * int, int)
	 */
	@Override
	public void Add(ChessPiece piece, int xCoordinate, int yCoordinate) {

		Integer pieceCount = pieceCounts.get(piece.getPieceColor()).get(piece.getPieceType());

		// If no count has been initialised set to 0
		// This means logic below can be consistent for first add
		// and any subsequent adds
		if (pieceCount == null) {
			pieceCount = new Integer(0);
		}

		// If not reached the maximum number for this type
		if (pieceCount.intValue() < maxPieceCounts.get(piece.getPieceType()).intValue()) {
			// If space isn't occupied
			if (IsLegalBoardPosition(xCoordinate, yCoordinate) && !isSquareOccupied(xCoordinate, yCoordinate)) {

				piece.setXCoordinate(xCoordinate);
				piece.setYCoordinate(yCoordinate);

				// attach Chess Board to Chess Piece
				piece.attachChessBoard(this);
				incrementPieceCount(piece, pieceCount);
				pieces[xCoordinate][yCoordinate] = piece;

			}
		}

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logicnow.hiring.ChessBoard#isSquareOccupied(int, int)
	 */
	@Override
	public boolean isSquareOccupied(int xCoordinate, int yCoordinate) {
		return !(pieces[xCoordinate][yCoordinate] == null);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logicnow.hiring.ChessBoard#IsLegalBoardPosition(int, int)
	 */
	@Override
	public boolean IsLegalBoardPosition(int xCoordinate, int yCoordinate) {
		return !(xCoordinate < START_COORDINATE || xCoordinate > MAX_BOARD_WIDTH || xCoordinate < START_COORDINATE
				|| xCoordinate > MAX_BOARD_WIDTH || yCoordinate < START_COORDINATE || yCoordinate > MAX_BOARD_WIDTH
				|| yCoordinate < START_COORDINATE || yCoordinate > MAX_BOARD_WIDTH);

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logicnow.hiring.ChessBoard#MovePiece(com.logicnow.hiring.
	 * MovementType, int, int, int, int)
	 */
	@Override
	public void MovePiece(MovementType movementType, int fromX, int fromY, int toX, int toY) {
		ChessPiece piece = this.getPieceAt(fromX, fromY);
		piece.Move(movementType, toX, toY);

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.logicnow.hiring.ChessPieceObserver#update(com.logicnow.hiring.
	 * ChessPiece)
	 */
	@Override
	public void update(ChessPiece updatedPiece) {

		// remove from original square
		this.pieces[updatedPiece.getPreviousXCoordinate()][updatedPiece.getPreviousYCoordinate()] = null;
		// place in new square
		this.pieces[updatedPiece.getXCoordinate()][updatedPiece.getYCoordinate()] = updatedPiece;
	}

}
