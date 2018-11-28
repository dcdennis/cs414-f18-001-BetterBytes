/**
 * 
 */
package edu.colostate.cs.cs414.betterbytes.p3.wireforms;

import java.io.Serializable;

public class UserRegistration implements Message, Protocol, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2896709563621328730L;
	private String username;
	private String passwordHash;
	private final String type = Protocol.USER_REGISTRATION;
	// private String passwordSalt;

	@Override
	public String toString() {
		return "UserRegistration [username=" + username + ", passwordHash=" + passwordHash + ", type=" + type + "]";
	}

	public UserRegistration(String username, String passwordHash) {
		this.username = username;
		this.passwordHash = passwordHash;
		// this.passwordSalt = passwordSalt;
	}

	public UserRegistration(String stringRep) {
		String[] data = stringRep.split(", ");
		if (!data[0].equals(USER_REGISTRATION))
			System.out.println("Something is wrong");
		this.username = data[1];
		this.passwordHash = data[2];
		// this.passwordSalt = data[3];
	}

	public String getStringRepresentation() {
		return USER_REGISTRATION + ", " + username + ", " + passwordHash; // + ", " + passwordSalt;
	}

	public String getUsername() {
		return username;
	}

	public String getPasswordHash() {
		return passwordHash;
	}
	// public String getPasswordSalt() {return passwordSalt;}

	public boolean equals(Object O) {
		if (O instanceof UserRegistration) {
			UserRegistration m = (UserRegistration) O;
			return this.username.equals(m.getUsername()) && this.passwordHash.equals(m.getPasswordHash());
		}
		return false;
	}

	public String getType() {
		return type;
	}

}
