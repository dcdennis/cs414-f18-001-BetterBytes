package edu.colostate.cs.cs414.betterbytes.p3.wireforms;

import java.io.Serializable;

public class UserRegistrationResponse implements Message, Protocol, Serializable 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5558123529677680766L;
	private String status;
	private String message;
	private final String type = Protocol.USER_REGISTRATION_RESPONSE;
	
	@Override
	public String toString() {
		return "UserRegistrationResponse [status=" + status + ", message=" + message + ", type=" + type + "]";
	}

	public UserRegistrationResponse(String status, String message)
	{
		this.status = status;
		this.message = message;
	}
	
	public UserRegistrationResponse(String stringRep)
	{
		String[] data = stringRep.split(", ");
		if(!data[0].equals(USER_REGISTRATION_RESPONSE))
			System.out.println("Something is wrong");
		this.status = data[1];
		this.message = data[2];
	}
	
	public String getStringRepresentation() {
		return USER_REGISTRATION_RESPONSE + ", " + status + ", " + message;
	}
	
	public String getStatus() {return status;}
	public String getMessage() {return message;}
	
	public boolean equals(Object O)
	{
		if(O instanceof UserRegistrationResponse)
		{
			UserRegistrationResponse m = (UserRegistrationResponse) O;
			return this.status.equals(m.getStatus())&&this.message.equals(m.getMessage());
		}
		return false;
	}

	public String getType() {
		return type;
	}

}
