package com.logicnow.hiring;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PawnTest {

    private ChessBoard chessBoard;
    private ChessPiece testSubject;
    
    private String pawnToString(ChessPiece pawn){
    	String eol = System.lineSeparator();
		return String.format("Current X: {1}{0}Current Y: {2}{0}Piece Color: {3}", eol, pawn.getPreviousXCoordinate(), pawn.getYCoordinate(),
				pawn.getPieceColor());
    }
    
    @Before
    public void setUp() {
        this.chessBoard = new ChessBoardImpl();
        this.testSubject = new Pawn(PieceColor.BLACK);
    }

    @Test
    public void testChessBoard_Add_Sets_XCoordinate() {
        this.chessBoard.Add(testSubject, 6, 3);
        assertEquals(6, testSubject.getXCoordinate());
    }

    @Test
    public void testChessBoard_Add_Sets_YCoordinate() {
        this.chessBoard.Add(testSubject, 6, 3);
        assertEquals(3, testSubject.getYCoordinate());
    }


    @Test
    public void testPawn_Move_IllegalCoordinates_Right_DoesNotMove() {
        chessBoard.Add(testSubject, 6, 3);
        testSubject.Move(MovementType.MOVE, 7, 3);
        assertEquals(6, testSubject.getXCoordinate());
        assertEquals(3, testSubject.getYCoordinate());
    }

    @Test
    public void testPawn_Move_IllegalCoordinates_Left_DoesNotMove() {
        chessBoard.Add(testSubject, 6, 3);
        testSubject.Move(MovementType.MOVE, 4, 3);
        assertEquals(6, testSubject.getXCoordinate());
        assertEquals(3, testSubject.getYCoordinate());
    }
    
    @Test
    public void testPawn_Move_White_IllegalCoordinates_Back_DoesNotMove() {
    	this.testSubject = new Pawn(PieceColor.WHITE);
    	chessBoard.Add(testSubject, 6, 3);
        testSubject.Move(MovementType.MOVE, 6, 2);
        assertEquals(6, testSubject.getXCoordinate());
        assertEquals(3, testSubject.getYCoordinate());
    }
    
    @Test
    public void testPawn_Move_White_2nd_Move_2Steps_DoesNotMove() {
    	this.testSubject = new Pawn(PieceColor.WHITE);
    	chessBoard.Add(testSubject, 6, 2);
        testSubject.Move(MovementType.MOVE, 6, 4);
        testSubject.Move(MovementType.MOVE, 6, 6);
        assertEquals(6, testSubject.getXCoordinate());
        assertEquals(4, testSubject.getYCoordinate());
    }
    
    @Test
    public void testPawn_Move_Black_IllegalCoordinates_Back_DoesNotMove() {
    	this.testSubject = new Pawn(PieceColor.BLACK);
    	chessBoard.Add(testSubject, 6, 3);
        testSubject.Move(MovementType.MOVE, 6, 4);
        assertEquals(6, testSubject.getXCoordinate());
        assertEquals(3, testSubject.getYCoordinate());
    }
    
    @Test
    public void testPawn_Move_Black_2nd_Move_2Steps_DoesNotMove() {
    	this.testSubject = new Pawn(PieceColor.BLACK);
    	chessBoard.Add(testSubject, 6, 6);
        testSubject.Move(MovementType.MOVE, 6, 4);
        testSubject.Move(MovementType.MOVE, 6, 2);
        assertEquals(6, testSubject.getXCoordinate());
        assertEquals(4, testSubject.getYCoordinate());
    }

    @Test
    public void testPawn_Move_White_LegalCoordinates_Forward_UpdatesCoordinates() {
    	this.testSubject = new Pawn(PieceColor.WHITE);
    	chessBoard.Add(testSubject, 6, 2);
        testSubject.Move(MovementType.MOVE, 6, 3);
        assertEquals(6, testSubject.getXCoordinate());
        assertEquals(3, testSubject.getYCoordinate());
    }
    
    @Test
    public void testPawn_Move_White_FirstMove_Forward_2_UpdatesCoordinates() {
    	this.testSubject = new Pawn(PieceColor.WHITE);
    	chessBoard.Add(testSubject, 6, 2);
        testSubject.Move(MovementType.MOVE, 6, 4);
        assertEquals(6, testSubject.getXCoordinate());
        assertEquals(4, testSubject.getYCoordinate());
    }
    
    @Test
    public void testPawn_Move_Black_FirstMove_Forward_2_UpdatesCoordinates() {
    	this.testSubject = new Pawn(PieceColor.BLACK);
    	chessBoard.Add(testSubject, 6, 6);
        testSubject.Move(MovementType.MOVE, 6, 4);
        assertEquals(6, testSubject.getXCoordinate());
        assertEquals(4, testSubject.getYCoordinate());
    }
    
    @Test
    public void testPawn_Move_Black_LegalCoordinates_Forward_UpdatesCoordinates() {
    	this.testSubject = new Pawn(PieceColor.BLACK);
    	chessBoard.Add(testSubject, 6, 6);
        testSubject.Move(MovementType.MOVE, 6, 5);
        assertEquals(6, testSubject.getXCoordinate());
        assertEquals(5, testSubject.getYCoordinate());
    }
    
    @Test
    public void testToStringNotOnBoard(){
    	assertEquals(testSubject.toString(),pawnToString(testSubject));
    }
    
    @Test
    public void testToStringOnBoard(){
    	chessBoard.Add(testSubject, 6, 2);
    	assertEquals(testSubject.toString(),pawnToString(testSubject));
    }
    
    @Test
    public void testGetPreviousXCoordinate_Not_On_Board(){
    	assertEquals(testSubject.getPreviousXCoordinate(),-1);
    }
    
    @Test
    public void testGetPreviousXCoordinate_On_Board(){
    	chessBoard.Add(testSubject, 6, 2);
    	assertEquals(testSubject.getPreviousXCoordinate(),-1);
    }
    
    @Test
    public void testGetPreviousXCoordinate_After_1_Move(){
    	chessBoard.Add(testSubject, 6, 6);
    	testSubject.Move(MovementType.MOVE, 6, 5);
    	assertEquals(testSubject.getPreviousXCoordinate(),6);
    }
    
    @Test
    public void testGetPreviousXCoordinate_After_Multiple_Moves(){
    	chessBoard.Add(testSubject, 6, 6);
    	testSubject.Move(MovementType.MOVE, 6, 5);
    	testSubject.Move(MovementType.MOVE, 6, 4);
    	assertEquals(testSubject.getPreviousXCoordinate(),6);
    }
    
    @Test
    public void testGetPreviousYCoordinate_Not_On_Board(){
    	assertEquals(testSubject.getPreviousYCoordinate(),-1);
    }
    
    @Test
    public void testGetPreviousYCoordinate_On_Board(){
    	chessBoard.Add(testSubject, 6, 2);
    	assertEquals(testSubject.getPreviousYCoordinate(),-1);
    }
    
    @Test
    public void testGetPreviousYCoordinate_After_1_Move(){
    	chessBoard.Add(testSubject, 6, 6);
    	testSubject.Move(MovementType.MOVE, 6, 5);
    	assertEquals(testSubject.getPreviousYCoordinate(),6);
    }
    
    @Test
    public void testGetPreviousYCoordinate_After_Multiple_Moves(){
    	chessBoard.Add(testSubject, 6, 6);
    	testSubject.Move(MovementType.MOVE, 6, 5);
    	testSubject.Move(MovementType.MOVE, 6, 4);
    	assertEquals(testSubject.getPreviousYCoordinate(),5);
    }
}