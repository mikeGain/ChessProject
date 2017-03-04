package com.logicnow.hiring;

public interface ChessBoard {
	public static int MAX_BOARD_WIDTH = 8;
	public static int MAX_BOARD_HEIGHT = 8;
	
	public void Add(ChessPiece piece, int xCoordinate, int yCoordinate);

	public boolean isSquareOccupied(int xCoordinate, int yCoordinate);

	public boolean IsLegalBoardPosition(int xCoordinate, int yCoordinate);

	public void MovePiece(MovementType moveType, int fromX, int fromY, int toX, int toY);



}