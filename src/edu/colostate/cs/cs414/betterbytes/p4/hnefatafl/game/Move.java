package edu.colostate.cs.cs414.betterbytes.p4.hnefatafl.game;

import java.io.Serializable;

/**
 * Move class. Represents positions of a movement for a game.
 */
public class Move implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int startX;
	private int endX;
	private int startY;
	private int endY;

	/**
	 * Move constructor. Sets the start (x,y) positions and the end (x,y) positions
	 * @param startX start X position
	 * @param endX end X position
	 * @param startY start Y position
	 * @param endY end Y position
	 */
	public Move(int startX, int endX, int startY, int endY) {
		this.startX = startX;
		this.endX = endX;
		this.startY = startY; 
		this.endY = endY;
	}

	public int getStartX() {
		return startX;
	}

	public int getStartY() {
		return startY;
	}

	public int getEndX() {
		return endX;
	}

	public int getEndY() {
		return endY;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endX;
		result = prime * result + endY;
		result = prime * result + startX;
		result = prime * result + startY;
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
		Move other = (Move) obj;
		if (endX != other.endX)
			return false;
		if (endY != other.endY)
			return false;
		if (startX != other.startX)
			return false;
		if (startY != other.startY)
			return false;
		return true;
	}
}
