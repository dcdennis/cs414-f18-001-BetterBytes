package edu.colostate.cs.cs414.betterbytes.p3.user;

public class Stats {

	// GLOBALS FIELDS
	private int wins;
	private int losses;
	
	// CONSTRUCTOR
	public Stats() {}
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
