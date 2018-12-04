package edu.colostate.cs.cs414.betterbytes.p4.user;

import java.io.Serializable;

public class Stats implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 533963618842963462L;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + losses;
		result = prime * result + wins;
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
		Stats other = (Stats) obj;
		if (losses != other.losses)
			return false;
		if (wins != other.wins)
			return false;
		return true;
	}

	// GLOBALS FIELDS
	private int wins;
	private int losses;

	// CONSTRUCTOR
	public Stats() {
	}

	public Stats(int wins, int losses) {
		this.wins = wins;
		this.losses = losses;
	}

	// SETTERS
	public void addWin() {
		this.wins++;
	}

	public void addLoss() {
		this.losses++;
	}

	// GETTERS
	public int getWins() {
		return this.wins;
	}

	public int getLosses() {
		return this.losses;
	}
}
