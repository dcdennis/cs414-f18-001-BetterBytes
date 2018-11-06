package edu.colostate.cs.cs414.betterbytes.p3.user;

import java.util.ArrayList;

public class Account {
	
	// GLOBAL FIELDS
	private String username;
	private String email;
	private String password;
	private Invitation invite;
	private ArrayList<Player> players; 
	private Stats stats;
	
	
	
	public Account() {}

	// CONSTRUCTOR 
	public Account(String email,String password,String username) 
	{
		this.username = username;
		this.email = email;
		this.password = password;
	}
	
	// MUTATORS
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	public void setPassword(String password) 
	{
		this.password = password;
	}
	
	public void addPlayer(Player player) {
		this.players.add(player);
	}
	
	// ACCESSORS
	public String getUsername() {
		return this.username;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public Stats getStats() {
		return this.stats;
	}
}
