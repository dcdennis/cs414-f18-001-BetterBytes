package edu.colostate.cs.cs414.betterbytes.p3.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import edu.colostate.cs.cs414.betterbytes.p3.utilities.Tools;

/**
 * 
 * @author Daniel McClure - 830437441
 *
 */
public class Grid {

	private int baseX = 15, baseY = 15;

	private ArrayList<Cell> cells = new ArrayList<Cell>();
	private ArrayList<Cell> backup = new ArrayList<Cell>();

	private Game game = null;

	/**
	 * Constructor for Grid, the object that holds the Cells and Pieces of the
	 * Game.
	 * 
	 * @param baseX
	 *            coordinate in which the grid will be painted
	 * @param baseY
	 *            coordinate in which the grid will be painted
	 * @param game
	 *            reference to the Game object
	 */
	public Grid(int baseX, int baseY, Game game) {
		this.game = game;
		this.setBaseX(baseX);
		this.setBaseY(baseY);
		for (int x = 1; x <= 11; x++) {
			for (int y = 1; y <= 11; y++) {
				cells.add(new Cell(x, y, this));
			}
		}
	}

	/**
	 * This will set the board of the correct leangth of an ArrayList of Cells.
	 * 
	 * @param board
	 * @return Successful
	 */
	public boolean setBoard(ArrayList<Cell> board) {
		if (board.size() == cells.size()) {
			for (int i = 0; i < cells.size(); i++) {
				cells.get(i).setX(board.get(i).getX());
				cells.get(i).setY(board.get(i).getY());
				cells.get(i).setPiece(board.get(i).getPiece());
			}
			return true;
		} else {
			Tools.log("Board incorrect size! Cannot load!");
			return false;
		}
	}

	/**
	 * This undoes the last unsent move. Used after previewing the board after a
	 * move and not wanting to send a move
	 */
	public void revertLastMove() {
		for (int i = 0; i < cells.size(); i++) {
			cells.get(i).setX(backup.get(i).getX());
			cells.get(i).setY(backup.get(i).getY());
			cells.get(i).setPiece(backup.get(i).getPiece());
		}
	}

	public int getBaseX() {
		return baseX;
	}

	public void setBaseX(int baseX) {
		this.baseX = baseX;
	}

	public int getBaseY() {
		return baseY;
	}

	public void setBaseY(int baseY) {
		this.baseY = baseY;
	}

	public ArrayList<Cell> getCells() {
		return this.cells;
	}
	

	/**
	 * 
	 * @return The selected Cell on the board, null if none are selected
	 */
	public Cell getSelectedCell() {
		for (Cell c : this.getCells())
			if (c.isSelected())
				return c;
		return null;
	}

	public boolean isCellSelected() {
		for (Cell c : this.getCells())
			if (c.isSelected())
				return true;
		return false;
	}

	/**
	 * Clears selected Cell
	 */
	public void clearSelected() {
		for (Cell c : this.getCells())
			if (c.isSelected())
				c.setSelected(false);
	}

	/**
	 * Returns Cell object when given a valid x y, will return null if x y is
	 * not on board
	 * 
	 * @param x
	 * @param y
	 * @return Cell object
	 */
	public Cell getCell(int x, int y) {
		for (Cell c : this.cells)
			if (c.getX() == x && c.getY() == y)
				return c;
		return null;
	}

	/**
	 * Incomplete, this will send the move to the server
	 * 
	 * @return successful
	 */
	public boolean sendMoveToServer() {
		return false;
	}

	/**
	 * This method will move Piece p from Cell old to Cell nu
	 * 
	 * @param p
	 *            Piece to move
	 * @param old
	 *            Cell that contains Piece p
	 * @param nu
	 *            Destination Cell
	 */
	public void movePiece(Piece p, Cell old, Cell nu) {
		if (p != null && old != null && nu != null && game.isOurTurn()) {
			backup.clear();
			for (Cell c : cells) {
				Cell nc = new Cell(c.getX(), c.getY(), this);
				nc.setPiece(c.getPiece());
				backup.add(nc);
			}
			// if(nu.hasPiece()) {

			// } else {
			nu.setPiece(p);
			old.setPiece(null);
			// }
		}
	}

	/**
	 * This method reads a String and attempts to load the board from it. It
	 * will fail if string is wrongly formatted or incomplete
	 * 
	 * @param data
	 *            String to parse board from
	 * @return whether it was successful or not
	 */
	public boolean setBoardFromString(String data) {
		// ArrayList<Cell> newCells = new ArrayList<Cell>();
		game.clearBoard();
		for (String s : data.split("~")) {
			if (s != null) {
				String[] info = s.split(":");
				if (info != null && info.length == 4) {
					int x = Integer.parseInt(info[0]);
					int y = Integer.parseInt(info[1]);

					// Cell c = new Cell(Integer.parseInt(info[0]),
					// Integer.parseInt(info[1]),
					// this);
					Piece p = new Piece(null, false);
					p.setTypeFromString(info[2]);
					p.setIsWhite(Boolean.parseBoolean(info[3]));

					game.getCell(x, y).setPiece(p);

					// c.setPiece(p);
					// newCells.add(c);
				} else {
					Tools.log("Second split failed");
				}
			} else {
				Tools.log("First split failed");
			}
		}
		// if (newCells.size() == cells.size())
		// return this.setBoard(newCells);
		return true;
	}

	/**
	 * This method saves the current grid to a string that can later be loaded.
	 * Uses '~' and ':' to separate data.
	 * 
	 * @return Grid data converted to a single string
	 */
	public String saveToString() {
		String data = "";
		for (Cell c : cells) {
			if (c.getPiece() != null) {
				data += "~" + c.getX() + ":" + c.getY() + ":" + c.getPiece().getType() + ":" + c.getPiece().isWhite();
			} else {
				data += "~" + c.getX() + ":" + c.getY() + ":" + "null" + ":" + "false";
			}
		}
		return data;
	}

}
