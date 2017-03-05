package com.logicnow.hiring;

public interface ChessPiece {
	
	public PieceType getPieceType();
	
	public void attachChessBoard(ChessBoard chessBoard);
	
	public int getPreviousXCoordinate();
	
	public int getXCoordinate();
	
	public void setXCoordinate(int value);
	
	public int getPreviousYCoordinate();
	
	public int getYCoordinate();
	
	public void setYCoordinate(int value);

	public PieceColor getPieceColor();
	
	public void Move(MovementType movementType, int newX, int newY);
	
	
	
	
}
