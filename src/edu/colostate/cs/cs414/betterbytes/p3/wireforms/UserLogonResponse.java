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
	private String status;
	private String message;
	private final String type = Protocol.USER_LOGON_RESPONSE;
	
	public UserLogonResponse(String status, String message)
	{
		this.status = status;
		this.message = message;
	}
	
	public UserLogonResponse(String stringRep)
	{
		String[] data = stringRep.split(", ");
		if(!data[0].equals(USER_LOGON_RESPONSE))
			System.out.println("Something is wrong");
		this.status = data[1];
		this.message = data[2];
	}
	
	public String getStringRepresentation() {
		return USER_LOGON_RESPONSE + ", " + status + ", " + message;
	}
	
	public String getStatus() {return status;}
	public String getMessage() {return message;}
	
	public boolean equals(Object O)
	{
		if(O instanceof UserLogonResponse)
		{
			UserLogonResponse m = (UserLogonResponse) O;
			return this.status.equals(m.getStatus()) && this.message.equals(m.getMessage());
		}
		return false;
	}

	public String getType() {
		return type;
	}
}
