package edu.colostate.cs.cs414.betterbytes.p3.user;

import java.io.Serializable;

public class Player implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4601225344910731288L;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((account == null) ? 0 : 3);
		result = prime * result + ((color == null) ? 0 : color.hashCode());
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
		Player other = (Player) obj;
		if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
			return false;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		return true;
	}

	// GLOBAL FIELDS
	public Account account;
	public String color;

	// CONSTRUCTORS
	public Player() {
	}

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
