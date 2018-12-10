package edu.colostate.cs.cs414.betterbytes.p4.hnefatafl.game.testing;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.colostate.cs.cs414.betterbytes.p4.hnefatafl.game.Board;
import edu.colostate.cs.cs414.betterbytes.p4.hnefatafl.game.Cell;
import edu.colostate.cs.cs414.betterbytes.p4.hnefatafl.game.Piece;

public class BoardTest {

	
	@Test
	public void testBoard() {
		Board board = new Board();
		assertEquals(board.getSquaresOnBoard().length, 121);
		//board.getSquaresOnBoard();
		//board.getCell(0,0);
		//assertEquals(board.getCell(0,0), new Cell("S"));
	}
	
	@Test
	public void testCellAccessor() {
		Board board = new Board();
		assertEquals(board.getCell(0,0), new Cell("S", new Piece()));
	}
}
