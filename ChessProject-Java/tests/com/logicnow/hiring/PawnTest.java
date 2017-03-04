package com.logicnow.hiring;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PawnTest {

    private ChessBoard chessBoard;
    private ChessPiece testSubject;
    private ChessPieceFactory factory;

    @Before
    public void setUp() {
        this.factory = new ChessPieceFactory();
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
    public void testPawn_Move_White_2Move_2Steps_DoesNotMove() {
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
    public void testPawn_Move_Black_2Move_2Steps_DoesNotMove() {
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
    public void testHasMoved_TRUE() {
    	this.testSubject = new Pawn(PieceColor.BLACK);
    	chessBoard.Add(testSubject, 6, 6);
        testSubject.Move(MovementType.MOVE, 6, 5);
        assertTrue(testSubject.hasMoved(6, 6));
    }
    
    @Test
    public void testHasMoved_FALSE() {
    	this.testSubject = new Pawn(PieceColor.BLACK);
    	chessBoard.Add(testSubject, 6, 6);
        testSubject.Move(MovementType.MOVE, 6, 7);
        assertFalse(testSubject.hasMoved(6, 6));
    }

}