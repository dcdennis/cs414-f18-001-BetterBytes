package edu.colostate.cs.cs414.betterbytes.p4.server.wireforms;

import java.io.Serializable;

public class UserLogon implements Message, Protocol, Serializable {

	@Override
	public String toString() {
		return "UserLogon [username=" + username + ", passwordHash=" + passwordHash + ", type=" + type + "]";
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -6809700020784937591L;
	private String username;
	private String passwordHash;
	private final String type = Protocol.USER_LOGON;

	public UserLogon(String username, String passwordHash) {
		this.username = username;
		this.passwordHash = passwordHash;
	}

	public UserLogon(String stringRep) {
		String[] data = stringRep.split(", ");
		if (!data[0].equals(USER_LOGON))
			System.out.println("Something is wrong"); 
		this.username = data[1];
		this.passwordHash = data[2];
	}

	public String getStringRepresentation() {
		return USER_LOGON + ", " + username + ", " + passwordHash;
	}

	public String getUsername() {
		return username;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	@Override
	public boolean equals(Object O) {
		if (O instanceof UserLogon) {
			UserLogon m = (UserLogon) O;
			return this.username.equals(m.getUsername()) && this.passwordHash.equals(m.getPasswordHash());
		}
		return false;
	}

	@Override
	public String getType() {
		return type;
	}
}
