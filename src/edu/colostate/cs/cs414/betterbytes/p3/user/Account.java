package edu.colostate.cs.cs414.betterbytes.p3.user;

import java.util.ArrayList;
import java.util.List;

public class Account {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((invites == null) ? 0 : invites.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((players == null) ? 0 : players.hashCode());
		result = prime * result + ((stats == null) ? 0 : stats.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (invites == null) {
			if (other.invites != null)
				return false;
		} else if (!invites.equals(other.invites))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (players == null) {
			if (other.players != null)
				return false;
		} else if (!players.equals(other.players))
			return false;
		if (stats == null) {
			if (other.stats != null)
				return false;
		} else if (!stats.equals(other.stats))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	// GLOBAL FIELDS
	private String username;
	private String email;
	private String password;
	private List<Invitation> invites;
	private ArrayList<Player> players;
	private Stats stats;

	public Account() {
	}

	// CONSTRUCTOR
	public Account(String email, String password, String username) {
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public Account(String username, String password) {
		this.username = username;
		this.password = password;
	}

	// MUTATORS
	public void setUsername(String username) {
		this.username = username;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
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

	public List<Invitation> getInvites() {
		return invites;
	}

	public void setInvites(List<Invitation> invites) {
		this.invites = invites;
	}

	public void addInvite(Invitation invitation) {
		this.invites.add(invitation);
	}
}
