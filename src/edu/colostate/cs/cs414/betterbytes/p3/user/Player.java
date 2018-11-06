package edu.colostate.cs.cs414.betterbytes.p3.user;

public class Player {

	// GLOBAL FIELDS
	public Account account;
	public String color;
	
	// CONSTRUCTORS
	public Player() {}
	public Player(Account account) {
		this.account = account;
	}
	public Player(Account account, String color) {
		this.account = account;
		this.color = color;
	}
	
	// SETTERS
	public void setAccount(Account account) {
		this.account = account;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	// GETTERS
	public Account getAccount() {
		return this.account;
	}
	public String getColor() {
		return this.color;
	}
	
}
