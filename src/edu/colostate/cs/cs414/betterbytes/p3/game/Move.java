package edu.colostate.cs.cs414.betterbytes.p3.game;

import java.io.Serializable;

/**
 * Represents a move on the board
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
	 * Construct a movement from position (startX, startY) to (endX, endY)
	 * @param startX x position of the start of the move
	 * @param endX x position of the end of the move
	 * @param startY y position of the start of the move
	 * @param endY y position of the end of the move
	 */
	public Move(int startX, int endX, int startY, int endY) {
		this.startX = startX;
		this.endX = endX;
		this.startY = startY;
		this.endY = endY;
	}

	/**
	 * Gets the x position of the start of the move
	 * @return startX
	 */
	public int getStartX() {
		return startX;
	}

	/**
	 * Gets the y position of the start of the move
	 * @return startY
	 */
	public int getStartY() {
		return startY;
	}

	/**
	 * Gets the x position of the end of the move
	 * @return endX
	 */
	public int getEndX() {
		return endX;
	}

	/**
	 * Gets the y position of the end of the move
	 * @return endY
	 */
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
