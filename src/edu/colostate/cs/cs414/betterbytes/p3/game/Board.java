package edu.colostate.cs.cs414.betterbytes.p3.game;

import java.io.Serializable;

/**
 * Represents the game's board
 * @version 1.0
 */
public class Board implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3252334098475305497L;
	
	private Cell cells[];

	/**
	 * Default Board Constructor
	 */
	public Board() {
		this.cells = initializeBoard();
	}

	/**
	 * Initialize cells of the board based off the board string.
	 * Not finished, don't call it.
	 * @param boardData The board state as a String
	 */
	public Board(String boardData) {
		// TODO initialize the cells from a string
		// EX: dan_devin.txt (PKG: edu.colostate.cs.cs414.betterbytes.p3.data.games)
	}

	/**
	 * Get the cells of the board
	 * @return cells of the board
	 */
	public Cell[] getSquaresOnBoard() {
		return this.cells;
	}

	/**
	 * Initialize the board setting all squares to standard squares.
	 * Needs to be updated to handle corners and the throne cell.
	 * @return the 1d array of all 121 cells
	 */
	public Cell[] initializeBoard() {

		Cell cells[] = new Cell[121];
		// TODO: add Corner and Throne Cells
		// Create new Cell("C") and new Cell("T") based off index of board
		for (int i = 0; i < 121; i++) {
			cells[i] = new Cell("S");
		}
		return cells;

	}

	/**
	 * Return the cell at position (x,y) for 0 <= x,y <= 10.
	 * No bounds check so (x + 11*y) < 12 or else.
	 * @param x Should be [0, 10]
	 * @param y Should be [0, 10]
	 * @return cell at (x,y)
	 */
	public Cell getCell(int x, int y) {
		// CHECK INDEX LOGIC
		return cells[11 * y + x];
	}
}
