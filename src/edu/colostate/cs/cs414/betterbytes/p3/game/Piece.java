package edu.colostate.cs.cs414.betterbytes.p3.game;

import java.io.Serializable;

public class Piece implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8063544977871411012L;
	private boolean isRook;
	private boolean isKing;
	private String type;
	private String color;

	public Piece() {
	}

	public Piece(boolean isRook, String color) {
		this.isRook = isRook;
		this.isKing = !isRook;
		this.color = color;
		this.type = initPieceType();
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
