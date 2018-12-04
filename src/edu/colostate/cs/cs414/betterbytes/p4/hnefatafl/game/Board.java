package edu.colostate.cs.cs414.betterbytes.p4.hnefatafl.game;

import java.io.Serializable;

public class Board implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3252334098475305497L;
	
	private Cell cells[];

	public Board() {
		this.cells = initializeBoard();
	}

	public Board(String boardData) {
		// TODO initialize the cells from a string
		// EX: dan_devin.txt (PKG: edu.colostate.cs.cs414.betterbytes.p3.data.games)
	}

	public Cell[] getSquaresOnBoard() {
		return this.cells;
	}

	public Cell[] initializeBoard() {

		Cell cells[] = new Cell[121];
		// TODO: add Corner and Throne Cells
		// Create new Cell("C") and new Cell("T") based off index of board
		for (int i = 0; i < 121; i++) {
			cells[i] = new Cell("S");
		}
		return cells;

	}

	public Cell getCell(int x, int y) {
		// CHECK INDEX LOGIC
		return cells[11 * y + x];
	}
}
