package edu.colostate.cs.cs414.betterbytes.p3.wireforms;

import java.io.Serializable;

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
	
	public UserLogonResponse(boolean status, String message)
	{
		this.status = status;
		this.message = message;
	}
	
	public String getStringRepresentation() {
		return USER_LOGON_RESPONSE + ", " + status + ", " + message;
	}
	
	public boolean getStatus() {return status;}
	public String getMessage() {return message;}
	
	public boolean equals(Object O)
	{
		if(O instanceof UserLogonResponse)
		{
			UserLogonResponse m = (UserLogonResponse) O;
			return this.status == m.getStatus() && this.message.equals(m.getMessage());
		}
		return false;
	}

	public String getType() {
		return type;
	}
}
