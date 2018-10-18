package edu.colostate.cs.cs414.p1.betterbytes.ui;

import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;

import edu.colostate.cs.cs414.p1.betterbytes.utilities.Tools;

/**
 * This class represents a physical chess piece
 * 
 * @author Daniel McClure - 830437441
 *
 */
public class Piece {

	private PieceType type = null;
	private boolean white = false;
	public Image king = Tools.getLocalImg("data/pieces/white_king.png");
	public Image[] rooks = { Tools.getLocalImg("data/pieces/black_rook.png"),
			Tools.getLocalImg("data/pieces/white_rook.png") };

	/**
	 * Constructor for piece
	 * 
	 * @param type
	 *            of piece
	 * @param isWhite
	 *            is white piece
	 */
	public Piece(PieceType type, boolean isWhite) {
		this.setType(type);
		this.white = isWhite;
	}

	public PieceType getType() {
		return type;
	}

	public void setType(PieceType type) {
		this.type = type;
	}

	public void setTypeFromString(String type) {
		Tools.log(type);
		switch (type) {
		case "KING":
			this.setType(PieceType.KING);
			break;
		case "ROOK":
			this.setType(PieceType.ROOK);
			break;
		}
	}

	public void setIsWhite(boolean isWhite) {
		this.white = isWhite;
	}

	/**
	 * @return The correct image icon based off of piece type and color
	 */
	public Image getIcon() {
		if (this.getType() == null)
			return null;
		switch (this.getType()) {
		case KING:
			return king;
		case ROOK:
			if (isWhite())
				return rooks[1];
			return rooks[0];
		default:
			break;
		}
		return null;
	}

	public boolean isWhite() {
		return this.white;
	}

}
