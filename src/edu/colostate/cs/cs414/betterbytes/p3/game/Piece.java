package edu.colostate.cs.cs414.betterbytes.p3.game;

import java.io.Serializable;

/**
 * Represents a piece on a cell
 * @version 1.0
 */
public class Piece implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean isRook;
	private boolean isKing;
	private String type;
	private String color;

	public Piece() { 
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + (isKing ? 1231 : 1237);
		result = prime * result + (isRook ? 1231 : 1237);
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Piece other = (Piece) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (isKing != other.isKing)
			return false;
		if (isRook != other.isRook)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	/**
	 * Constructs a new piece based on its type and color.
	 * @param isRook if the piece is a rook or not
	 * @param color color of the piece as a string
	 */
	public Piece(boolean isRook, String color) {
		this.isRook = isRook;
		this.isKing = !isRook;
		this.color = color;
		this.type = initPieceType();
	}

	/**
	 * Determines the type of the piece based on the isRook field
	 * @return The String "rook" if isRook is true, otherwise "king"
	 */
	public String initPieceType() {
		if (isRook == true) {
			return "rook";
		} else {
			return "king";
		}
	}

	/**
	 * Returns if the piece is a rook
	 * @return true if the piece is a rook, false otherwise
	 */
	public boolean isRook() {
		return this.isRook;
	}

	/**
	 * Returns if the piece is a king
	 * @return true if the piece is a king, false otherwise
	 */
	public boolean isKing() {
		return this.isKing;
	}

	/**
	 * Gets the type of the piece
	 * @return the piece's type
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * Gets the color of the piece
	 * @return the piece's color
	 */
	public String getColor() {
		return this.color;
	}

	/**
	 * Determines if the piece is white or not
	 * @return true if the piece is white, false otherwise
	 */
	public boolean isWhite() {
		return this.getType().equals("white");
	}

}
