package com.logicnow.hiring;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

import org.junit.Test;

public class ChessPieceFactoryTest extends TestCase {
	
	ChessPieceFactory testSubject;
	
	@Before
    public void setUp() throws Exception {
        testSubject = new ChessPieceFactory();
    }
	
	@Test
	public void testCreateWhitePawn_Success() {
		ChessPiece pawn = testSubject.createChessPiece(PieceType.PAWN, PieceColor.WHITE);
		assertEquals(pawn.getPieceType(), PieceType.PAWN);
		assertEquals(pawn.getPieceColor(),PieceColor.WHITE);
	}
	
	@Test
	public void testCreateBlackPawn_Success() {
		ChessPiece pawn = testSubject.createChessPiece(PieceType.PAWN, PieceColor.BLACK);
		assertEquals(pawn.getPieceType(), PieceType.PAWN);
		assertEquals(pawn.getPieceColor(),PieceColor.BLACK);
	}
	
	
}
