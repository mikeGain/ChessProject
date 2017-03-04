package com.logicnow.hiring;

public interface ChessPiece {
	public PieceType getPieceType();
	
	public void setChessBoard(ChessBoard chessBoard);
	
	public int getXCoordinate();
	
	public void setXCoordinate(int value);

	public int getYCoordinate();
	
	public void setYCoordinate(int value);

	public PieceColor getPieceColor();
	
	public void Move(MovementType movementType, int newX, int newY);
	
	public boolean hasMoved(int fromX, int fromY);
	
	
}
