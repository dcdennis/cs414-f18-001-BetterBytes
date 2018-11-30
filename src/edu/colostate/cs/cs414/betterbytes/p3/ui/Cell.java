package edu.colostate.cs.cs414.betterbytes.p3.ui;

import java.awt.Image;
import java.awt.Rectangle;

import edu.colostate.cs.cs414.betterbytes.p3.utilities.Tools;

/**
 * This class represents a cell within the game
 * 
 * @author Daniel McClure - 830437441
 *
 */
public class Cell {

	private String absPath = "";
	private int x, y;
	private int size = 75; 
	private Piece holding = null;
	private Grid grid = null;
	private boolean selected = false;
	private Image icon = Tools.getLocalImg(absPath+"edu/colostate/cs/cs414/betterbytes/p3/data/corner.png");

	/**
	 * Constructor for Cell
	 * 
	 * @param x    coordinate in relation to the chess board
	 * @param y    coordinate in relation to the chess board
	 * @param size of the cell
	 * @param grid reference to grid class
	 */
	public Cell(int x, int y, Grid grid) {
		this.setX(x);
		this.setY(y);
		this.grid = grid;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getSize() {
		return this.size;
	}

	/**
	 * Sets this cell to either selected or not
	 * 
	 * @param s
	 */
	public void setSelected(boolean s) {
		// Tools.log("Selected: " + this);
		this.selected = s;
	}

	public boolean isSelected() {
		return this.selected;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Piece getPiece() {
		return this.holding;
	}

	/**
	 * This will put piece p on this cell
	 * 
	 * @param p designated piece to be placed
	 */
	public void setPiece(Piece p) {
		this.holding = p;
	}

	public boolean hasPiece() {
		return this.getPiece() != null && this.getPiece().getIcon() != null;
	}

	public boolean isWhiteCell() {
		return (this.getX() + this.getY()) % 2 == 0;
	}

	/**
	 * @return the y coordinate in which the cell is being painted
	 */
	public int getRealY() {
		return grid.getBaseY() + (this.getY() * this.getSize());
	}

	/**
	 * @return the x coordinate in which the cell is being painted
	 */
	public int getRealX() {
		return grid.getBaseX() + (this.getX() * this.getSize());
	}

	public Rectangle getBounds() {
		return new Rectangle(this.getRealX(), this.getRealY(), this.getSize(), this.getSize());
	}

	/**
	 * A cell will be equal if the coordinates of the cell are the same
	 * 
	 * @param c Cell being compared
	 * @return whether they are equal
	 */
	public boolean equals(Cell c) {
		return this.getX() == c.getX() && this.getY() == c.getY();
	}

	public String toString() {
		return "Grid Cell:" + this.getX() + ":" + this.getY();
	}

	/**
	 * @return Decoration icons only returned for certain cells
	 */
	public Image getIcon() {
		if (this.getX() == 1 && this.getY() == 1 || this.getX() == 11 && this.getY() == 11
				|| this.getX() == 1 && this.getY() == 11 || this.getX() == 11 && this.getY() == 1) {
			return icon;
		}
		return null;
	}

}
