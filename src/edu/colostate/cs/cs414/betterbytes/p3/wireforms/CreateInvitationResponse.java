package edu.colostate.cs.cs414.betterbytes.p3.wireforms;

import java.io.Serializable;

public class CreateInvitationResponse implements Message, Protocol, Serializable {

	/**
	 * 
	 */
	
	
	private static final long serialVersionUID = 1524313760540559931L;
	private final String type = Protocol.CREATE_INVITATION_RESPONSE;
	
	private String status;
	private String message;
	public CreateInvitationResponse(String status, String message)
	{
		this.status = status;
		this.message = message;
	}
	public String getStatus() {return status;}
	public String getMessage() {return message;}
	
	@Override
	public String getType() {
		return type;
	}
}
