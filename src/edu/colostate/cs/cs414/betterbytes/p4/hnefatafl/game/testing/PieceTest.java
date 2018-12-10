package edu.colostate.cs.cs414.betterbytes.p4.hnefatafl.game.testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import edu.colostate.cs.cs414.betterbytes.p4.hnefatafl.game.Cell;
import edu.colostate.cs.cs414.betterbytes.p4.hnefatafl.game.Piece;

public class PieceTest {

	
	@Test
	void testPieceType() {
		Piece rookPiece = new Piece(true, "black");
		assertTrue(rookPiece.isRook());
	}
	
	@Test
	void testPieceKing() {
		Piece kingPiece = new Piece(false, "black");
		assertTrue(kingPiece.isKing());
	}
	
	@Test
	void testPieceColor() {
		Piece rookPiece = new Piece(true, "white");
		assertTrue(rookPiece.isWhite());
	}
	
	@Test
	void testPiecetoString() {
		Piece rookPiece = new Piece(true, "black");
		assertEquals(rookPiece.toString(), "Piece [type=rook, color=black]");
	}
	
	@Test
	void testColor() {
		Piece blackPiece = new Piece(false, "black");
		assertEquals(blackPiece.getColor(), "black");
	}
}
