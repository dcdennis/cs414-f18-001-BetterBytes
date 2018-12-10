package edu.colostate.cs.cs414.betterbytes.p4.hnefatafl.game.testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.colostate.cs.cs414.betterbytes.p4.hnefatafl.game.Cell;
import edu.colostate.cs.cs414.betterbytes.p4.hnefatafl.game.Piece;

public class CellTest {

	
	@Test
	public void testCellType() {
		Cell cell = new Cell("C", new Piece());
		assertTrue(cell.isCorner());
	}
	
	@Test
	public void testCellGetPiece() {
		Cell cell = new Cell("C", new Piece());
		assertEquals(cell.getPiece(), new Piece());
	}
	
	@Test
	public void testCellSetPiece() {
		Cell cell = new Cell();
		cell.setPiece(new Piece(true, "white"));
		assertEquals(cell.getPiece(), new Piece(true, "white"));
	}
	
	@Test
	public void testCellremovePiece() {
		Cell cell = new Cell();
		cell.setPiece(new Piece(true, "white"));
		cell.removePiece();
		assertNull(cell.getPiece());
	}
	
	@Test
	public void testCellEquals() {
		Cell cell = new Cell(5,5,"T", new Piece(false, "white"));
		Cell cpyCell = new Cell(5,5,"T", new Piece(false, "white"));
		assertTrue(cell.equals(cpyCell));
	}
	
	@Test
	public void testCellNotEquals() {
		Cell cell = new Cell(5,5,"T", new Piece(false, "white"));
		Cell cpyCell = new Cell(5,5,"T", new Piece(false, "black"));
		assertFalse(cell.equals(cpyCell));
	}

}
