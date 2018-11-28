package edu.colostate.cs.cs414.betterbytes.p3.game;

import java.io.Serializable;
import java.util.ArrayList;

public class Cell implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
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

	public Cell(String type) {
		this.type = type;
	}

	public Cell(String type, Piece piece) {
		this.piece = piece;
		this.type = type;
	}

	// ACCESSORS
	public boolean isCorner() {
		return this.type.equals(CORNER);
	}

	public boolean isThrone() {
		return this.type.equals(THRONE);
	}

	public boolean isSquare() {
		return this.type.equals(SQUARE);
	}

	public boolean isOccupied() {
		return this.isOccupied;
	}

	public Piece getPiece() {
		return this.piece;
	}

	// MUTATORS
	public void setPiece(Piece piece) {
		this.piece = piece;
		if (isOccupied == false) {
			this.isOccupied = true;
		}
	} 

	public void removePiece() {
		this.piece = null;
		this.isOccupied = false;
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

	public boolean hasPiece() {
		return this.getPiece() != null;
	}
}
