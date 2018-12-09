package edu.colostate.cs.cs414.betterbytes.p4.user;

/**
 * GameRecord class. Records game time, attacking and defending players, and the winner of the game.
 * @version 1.0
 */
public class GameRecord {

	// GLOBALS
	private String startTime;
	private String endTime;
	private Player winner;
	private Player defender;
	private Player attacker;

	// CONSTRUCTORS
	public GameRecord() {
	}

	public GameRecord(Player winner, Player defender, Player attacker) {
		this.winner = winner;
		this.defender = defender;
		this.attacker = attacker;
	}

	public GameRecord(String start, String end, Player winner, Player defender, Player attacker) {
		this.startTime = start;
		this.endTime = end;
		this.winner = winner;
		this.defender = defender;
		this.attacker = attacker;
	}

	// ACCESSORS
	public String getStartTime() {
		return this.startTime;
	}

	public String getEndTime() {
		return this.endTime;
	}

	public Player getWinner() {
		return this.winner;
	}

	public Player getDefender() {
		return this.defender;
	}

	public Player getAttacker() {
		return this.attacker;
	}

}
