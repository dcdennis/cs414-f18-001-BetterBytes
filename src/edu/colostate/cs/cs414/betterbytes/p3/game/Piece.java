package edu.colostate.cs.cs414.betterbytes.p3.game;

import java.io.Serializable;

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
	
	public String dump()
	{
		return "" + this.color.charAt(0) + this.type.toUpperCase().charAt(0);
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

	public Piece(boolean isRook, String color) {
		this.isRook = isRook;
		this.isKing = !isRook;
		this.color = color;
		this.type = initPieceType();
	}
	
	

	public Piece(String stringPiece) {
		System.out.println(stringPiece);
		this.isRook = stringPiece.charAt(1) == 'R';
		if(stringPiece.charAt(0) == 'w')
			this.color = "white";
		else
			this.color = "black";
		this.isKing = !this.isRook;
		this.type = initPieceType();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Piece [type=" + type + ", color=" + color + "]";
	}

	public String initPieceType() {
		if (isRook == true) {
			return "rook";
		} else {
			return "king";
		}
	}

	public boolean isRook() {
		return this.isRook;
	}

	public boolean isKing() {
		return this.isKing;
	}

	public String getType() {
		return this.type;
	}

	public String getColor() {
		return this.color;
	}

	public boolean isWhite() {
		return this.getType().equals("white");
	}

}
