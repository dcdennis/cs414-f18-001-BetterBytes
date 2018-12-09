package edu.colostate.cs.cs414.betterbytes.p4.server.wireforms;

import java.io.Serializable;

import edu.colostate.cs.cs414.betterbytes.p4.user.Account;

/**
 * UserLogonResponse class. Response to logon to the server.
 * @version 1.0
 */
public class UserLogonResponse implements Message, Protocol, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6406520340033670090L;
	/**
	 * 
	 */
	private boolean status;
	private String message;
	private final String type = Protocol.USER_LOGON_RESPONSE;
	private Account acc = null;

	public UserLogonResponse(boolean status, String message, Account accGiven) {
		this.status = status;
		this.message = message;
		acc = accGiven;
	}

	public String getStringRepresentation() {
		return USER_LOGON_RESPONSE + ", " + status + ", " + message;
	}

	public boolean getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public Account getAcc() {
		return acc;
	}

	public boolean equals(Object O) {
		if (O instanceof UserLogonResponse) {
			UserLogonResponse m = (UserLogonResponse) O;
			return this.status == m.getStatus() && this.message.equals(m.getMessage()) && this.acc == m.getAcc();
		}
		return false;
	}

	public String getType() {
		return type;
	}
}
