package com.logicnow.hiring;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ChessBoardTest extends TestCase {

    private ChessBoard testSubject;
    private ChessPieceFactory factory = new ChessPieceFactory();

    @Before
    public void setUp() throws Exception {
        testSubject = new ChessBoardImpl();
    }

    @Test
    public void testHas_MaxBoardWidth_of_8() {
        assertEquals(8, ChessBoard.MAX_BOARD_HEIGHT);
    }

    @Test
    public void testHas_MaxBoardHeight_of_8() {
        assertEquals(8, ChessBoard.MAX_BOARD_HEIGHT);
    }

    @Test
    public void testIsLegalBoardPosition_True_X_equals_0_Y_equals_0() {
        boolean isValidPosition = testSubject.IsLegalBoardPosition(0, 0);
        assertTrue(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_True_X_equals_5_Y_equals_5() {
        boolean isValidPosition = testSubject.IsLegalBoardPosition(5, 5);
        Assert.assertTrue(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_False_X_equals_11_Y_equals_5() {
        boolean isValidPosition = testSubject.IsLegalBoardPosition(11, 5);
        assertFalse(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_False_X_equals_0_Y_equals_9() {
        boolean isValidPosition = testSubject.IsLegalBoardPosition(0, 9);
        assertFalse(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_False_X_equals_11_Y_equals_0() {
        boolean isValidPosition = testSubject.IsLegalBoardPosition(11, 0);
        assertFalse(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_False_For_Negative_Y_Values() {
        boolean isValidPosition = testSubject.IsLegalBoardPosition(5, -1);
        Assert.assertFalse(isValidPosition);
    }
    
    @Test
    public void testIsLegalBoardPosition_False_For_Negative_X_Values() {
        boolean isValidPosition = testSubject.IsLegalBoardPosition(-5, 1);
        Assert.assertFalse(isValidPosition);
    }

    @Test
    public void testAdd_Avoids_Duplicate_Positioning() {
        ChessPiece firstPawn = factory.createChessPiece(PieceType.PAWN, PieceColor.BLACK);
        ChessPiece secondPawn = factory.createChessPiece(PieceType.PAWN, PieceColor.BLACK);
        testSubject.Add(firstPawn, 6, 3);
        testSubject.Add(secondPawn, 6, 3);
        assertEquals(6, firstPawn.getXCoordinate());
        assertEquals(3, firstPawn.getYCoordinate());
        assertEquals(-1, secondPawn.getXCoordinate());
        assertEquals(-1, secondPawn.getYCoordinate());
    }
    
    @Test
    public void testAdd_Both_Colours() {
    	ChessPiece firstPawn = factory.createChessPiece(PieceType.PAWN, PieceColor.BLACK);
        ChessPiece secondPawn = factory.createChessPiece(PieceType.PAWN, PieceColor.BLACK);
        testSubject.Add(firstPawn, 6, 3);
        testSubject.Add(secondPawn, 6, 7);
        assertEquals(6, firstPawn.getXCoordinate());
        assertEquals(3, firstPawn.getYCoordinate());
        assertEquals(6, secondPawn.getXCoordinate());
        assertEquals(7, secondPawn.getYCoordinate());
    }
    
    @Test
    public void testLimits_The_Number_Of_Pawns()
    {
        for (int i = 0; i < 10; i++)
        {
        	ChessPiece pawn = factory.createChessPiece(PieceType.PAWN, PieceColor.BLACK);
            
            int row = i / ChessBoard.MAX_BOARD_WIDTH;
            testSubject.Add(pawn, 6 + row, i % ChessBoard.MAX_BOARD_WIDTH);
            if (row < 1)
            {
                assertEquals(6 + row, pawn.getXCoordinate());
                assertEquals(i % ChessBoard.MAX_BOARD_WIDTH, pawn.getYCoordinate());
            }
            else
            {
                assertEquals(-1, pawn.getXCoordinate());
                Assert.assertEquals(-1, pawn.getYCoordinate());
            }
        }
    }
    
    @Test
    public void testisSquareOccupied_False_Empty_Board(){
    	Assert.assertFalse(testSubject.isSquareOccupied(2, 6));
    }
    
    @Test
    public void testisSquareOccupied_False_Occupied_Board(){
    	ChessPiece firstPawn = factory.createChessPiece(PieceType.PAWN, PieceColor.BLACK);
        ChessPiece secondPawn = factory.createChessPiece(PieceType.PAWN, PieceColor.BLACK);
       testSubject.Add(firstPawn, 6, 3);
        testSubject.Add(secondPawn, 6, 7);
    	Assert.assertFalse(testSubject.isSquareOccupied(2, 6));
    	
    }
    
    @Test
    public void testisSquareOccupied_TRUE(){
    	ChessPiece firstPawn = factory.createChessPiece(PieceType.PAWN, PieceColor.BLACK);
        ChessPiece secondPawn = factory.createChessPiece(PieceType.PAWN, PieceColor.BLACK);
        testSubject.Add(firstPawn, 6, 3);
        testSubject.Add(secondPawn, 6, 7);
    	Assert.assertTrue(testSubject.isSquareOccupied(6, 3));
    }
    
    @Test
    public void testMovePieceValidMove_Sucessful(){
    	ChessPiece piece = factory.createChessPiece(PieceType.PAWN, PieceColor.BLACK);
        testSubject.Add(piece, 6, 4);
        testSubject.MovePiece(MovementType.MOVE, 6, 4, 6, 2);
        Assert.assertFalse(testSubject.isSquareOccupied(6, 4));
        Assert.assertTrue(testSubject.isSquareOccupied(6, 2));
    }
    
    @Test
    public void testMovePieceMultipleValidMoves_Sucessful(){
    	ChessPiece piece = factory.createChessPiece(PieceType.PAWN, PieceColor.WHITE);
        testSubject.Add(piece, 6, 2);
        testSubject.MovePiece(MovementType.MOVE, 6, 2, 6, 4);
        Assert.assertFalse(testSubject.isSquareOccupied(6, 2));
        Assert.assertTrue(testSubject.isSquareOccupied(6, 4));
        testSubject.MovePiece(MovementType.MOVE, 6, 4, 6, 5);
        Assert.assertFalse(testSubject.isSquareOccupied(6, 4));
        Assert.assertTrue(testSubject.isSquareOccupied(6, 5));
    }
    
    @Test
    public void testMovePiecemMultiplePieceValidMoves_UnSucessful(){
    	ChessPiece whitePiece = factory.createChessPiece(PieceType.PAWN, PieceColor.WHITE);
    	ChessPiece blackPiece = factory.createChessPiece(PieceType.PAWN, PieceColor.BLACK);
        
    	testSubject.Add(whitePiece, 6, 2);
    	testSubject.Add(blackPiece, 6, 6);
    	
        testSubject.MovePiece(MovementType.MOVE, 6, 2, 6, 4);
        testSubject.MovePiece(MovementType.MOVE, 6, 6, 6, 4);
        Assert.assertTrue(testSubject.isSquareOccupied(6, 4));
        
        assertEquals(6, whitePiece.getXCoordinate());
        assertEquals(4, whitePiece.getYCoordinate());
        assertEquals(6, blackPiece.getXCoordinate());
        assertEquals(6, blackPiece.getYCoordinate());
        
    }
    
}