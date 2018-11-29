package edu.colostate.cs.cs414.betterbytes.p3.game;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents a cell on a board
 * @version 1.0
 */
public class Cell implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public final String CORNER = "C"; // 4 corner cells
	public final String THRONE = "T"; // middle throne cell
	public final String SQUARE = "S"; // normal cells

	// GLOBALS
	private int x, y;
	private String type;
	private Piece piece;
	private boolean isOccupied;

	// CTOR
	public Cell() {
	}

	/**
	 * Construct a cell with a given type
	 * @param type Type of the cell
	 */
	public Cell(String type) {
		this.type = type;
	}

	/**
	 * Construct a cell with a given type and a piece on it
	 * @param type Type of the cell
	 * @param piece The piece on the cell
	 */
	public Cell(String type, Piece piece) {
		this.piece = piece;
		this.type = type;
	}

	/**
	 * Construct a cell with position (x,y), a given type, and a piece on it
	 * @param x x position
	 * @param y y position
	 * @param type Type of the cell
	 * @param piece Piece on the cell
	 */
	public Cell(int x, int y, String type, Piece piece)
	{
		this.x = x;
		this.y = y;
		this.type = type;
		this.piece = piece;
	}
	// ACCESSORS

	/**
	 * Return if the cell has the CORNER type
	 * @return if type is a corner
	 */
	public boolean isCorner() {
		return this.type.equals(CORNER);
	}

	/**
	 * Return if the cell has the THRONE type
	 * @return if type is a throne
	 */
	public boolean isThrone() {
		return this.type.equals(THRONE);
	}

	/**
	 * Return if the cell has the SQUARE type
	 * @return if type is a square
	 */
	public boolean isSquare() {
		return this.type.equals(SQUARE);
	}

	/**
	 * Return if the cell is occupied by a piece
	 * @return if the cell is occupied
	 */
	public boolean isOccupied() {
		return this.isOccupied;
	}

	/**
	 * Get the piece on the cell. Can return a non-existent piece
	 * @return the piece on the cell
	 */
	public Piece getPiece() {
		return this.piece;
	}

	// MUTATORS

	/**
	 * Set the piece on this cell to the new piece. If the cell was not occupied, it now is
	 * @param piece Piece to go on the cell
	 */
	public void setPiece(Piece piece) {
		this.piece = piece;
		if (isOccupied == false) {
			this.isOccupied = true;
		}
	}

	/**
	 * Set the piece on the cell to null and set isOccupied to false.
	 */
	public void removePiece() {
		this.piece = null;
		this.isOccupied = false;
	}

	/**
	 * Get the x position of the piece.
	 * @return the x position
	 */
	public int getX() {
		return x;
	}

	/**
	 * Set the x position to the x parameter
	 * @param x the new position
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Get the y position of the piece.
	 * @return the y position
	 */
	public int getY() {
		return y;
	}
	/**
	 * Set the y position to the y parameter
	 * @param y the new position
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Return true if this cell has a piece, false if the piece is currently null.
	 * @return if the cell has a piece
	 */
	public boolean hasPiece() {
		return this.getPiece() != null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((CORNER == null) ? 0 : CORNER.hashCode());
		result = prime * result + ((SQUARE == null) ? 0 : SQUARE.hashCode());
		result = prime * result + ((THRONE == null) ? 0 : THRONE.hashCode());
		result = prime * result + (isOccupied ? 1231 : 1237);
		result = prime * result + ((piece == null) ? 0 : piece.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cell other = (Cell) obj;
		if (CORNER == null) {
			if (other.CORNER != null)
				return false;
		} else if (!CORNER.equals(other.CORNER))
			return false;
		if (SQUARE == null) {
			if (other.SQUARE != null)
				return false;
		} else if (!SQUARE.equals(other.SQUARE))
			return false;
		if (THRONE == null) {
			if (other.THRONE != null)
				return false;
		} else if (!THRONE.equals(other.THRONE))
			return false;
		if (isOccupied != other.isOccupied)
			return false;
		if (piece == null) {
			if (other.piece != null)
				return false;
		} else if (!piece.equals(other.piece))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

}