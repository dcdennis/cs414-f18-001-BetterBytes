package edu.colostate.cs.cs414.p1.betterbytes.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import edu.colostate.cs.cs414.p1.betterbytes.utilities.Tools;

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

	public void paint(Graphics g) {
		this.paintCells(g);
		this.paintPieces(g);
	}

	/**
	 * This method paints the cells, it is long because of the effects and
	 * offsetting I use to shadow and emphasize certain cells. Will definitely
	 * generate Code Smells because of similar repeated code
	 * 
	 * @param g
	 *            Graphics object
	 */
	public void paintCells(Graphics g) {
		for (Cell c : cells) {

			g.setColor(Color.GRAY);
			if (c.isWhiteCell()) {
				g.setColor(new Color(81, 100, 114));
			}

			// CELL ICON
			g.fillRect(baseX + (c.getX() * c.getSize()), baseY + (c.getY() * c.getSize()), c.getSize(), c.getSize());
			if (c.getIcon() != null) {
				g.drawImage(c.getIcon(), c.getRealX() + 1, c.getRealY() + 1, null);
			}

			// OUTLINE
			g.setColor(new Color(0, 0, 0, 200));
			g.drawRect(baseX + (c.getX() * c.getSize()), baseY + (c.getY() * c.getSize()), c.getSize(), c.getSize());
			g.drawRect(baseX + (c.getX() * c.getSize()) + 1, baseY + (c.getY() * c.getSize()) + 1, c.getSize(),
					c.getSize());

			// SELECTION
			if (c.isSelected()) {
				g.setColor(new Color(83, 183, 25, 85));
				g.fillRect(baseX + (c.getX() * c.getSize()) + 1, baseY + (c.getY() * c.getSize()) + 1, c.getSize() - 1,
						c.getSize() - 1);
				g.setColor(new Color(255, 255, 255, 200));
				g.drawRect(baseX + (c.getX() * c.getSize()) + 1, baseY + (c.getY() * c.getSize()) + 1, c.getSize() - 2,
						c.getSize() - 2);
			}

			if (this.isCellSelected()) {
				Cell selected = this.getSelectedCell();
				if (game.canMove(selected, c.getX(), c.getY())) {
					g.setColor(new Color(0, 255, 55, 55));
					g.fillRect(baseX + (c.getX() * c.getSize()), baseY + (c.getY() * c.getSize()), c.getSize(),
							c.getSize());
					g.setColor(new Color(0, 0, 0, 200));
					g.drawRect(baseX + (c.getX() * c.getSize()), baseY + (c.getY() * c.getSize()), c.getSize(),
							c.getSize());
					g.drawRect(baseX + (c.getX() * c.getSize()) + 1, baseY + (c.getY() * c.getSize()) + 1, c.getSize(),
							c.getSize());
				}
			}
		}
	}

	/**
	 * Method for painting the Pieces of the board
	 * 
	 * @param g
	 *            Graphics
	 */
	public void paintPieces(Graphics g) {
		for (Cell c : getCells()) {
			if (c.hasPiece() && c.getPiece().getIcon() != null) {
				// g.setColor(Color.BLUE);
				// g.fillRect(c.getRealX() + 10, c.getRealY() + 10, 20, 20);
				g.drawImage(c.getPiece().getIcon(), c.getRealX() - 1, c.getRealY() - 2, 83, 83, null);
			}
		}
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
