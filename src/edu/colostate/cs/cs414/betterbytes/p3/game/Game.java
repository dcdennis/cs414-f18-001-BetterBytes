package edu.colostate.cs.cs414.betterbytes.p3.game;

import edu.colostate.cs.cs414.betterbytes.p3.user.Player;

public class Game {

	// GLOBALS
	private String startTime;
	private String endTime;
	private Player turn; // Player that needs to make a move
	private Player attacker;
	private Player defender;
	
	// CONSTRUCTOR
	public Game() {}
	public Game(String startTime, Player attacker, Player defender) {
		this.turn = attacker;
		this.attacker = attacker;
		this.defender = defender;
		this.startTime = startTime;
	}
	
	// SETTERS
	public void changeTurns() {
		if (turn == attacker) {
			this.turn = defender;
		}
		else {
			this.turn = attacker;
		}
	}
	public void setEndTime(String end) {
		this.endTime = end;
	}
	
	// GETTERS
	public String getStartTime() {
		return this.startTime;
	}
	public String getEndTime() {
		return this.endTime;
	}
	public Player getTurn() {
		return this.turn;
	}
	public Player getAttacker() {
		return this.attacker;
	}
	public Player getDefender() {
		return this.defender;
	}
	
}


