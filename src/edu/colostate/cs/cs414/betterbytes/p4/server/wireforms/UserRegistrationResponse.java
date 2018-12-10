package edu.colostate.cs.cs414.betterbytes.p4.server.wireforms;

import java.io.Serializable;

/**
 * UserRegistrationResponse class. Response to registering with the server.
 * @version 1.0
 */
public class UserRegistrationResponse implements Message, Protocol, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5558123529677680766L;
	private boolean status;
	private String message;
	private final String type = Protocol.USER_REGISTRATION_RESPONSE;

	@Override
	public String toString() {
		return "UserRegistrationResponse [status=" + status + ", message=" + message + ", type=" + type + "]";
	}

	public UserRegistrationResponse(boolean status, String message) {
		this.status = status;
		this.message = message;
	}

	public String getStringRepresentation() {
		return USER_REGISTRATION_RESPONSE + ", " + status + ", " + message;
	}

	public boolean getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public boolean equals(Object O) {
		if (O instanceof UserRegistrationResponse) {
			UserRegistrationResponse m = (UserRegistrationResponse) O;
			return this.status == m.getStatus() && this.message.equals(m.getMessage());
		}
		return false;
	}

	public String getType() {
		return type;
	}

}
