package edu.colostate.cs.cs414.betterbytes.p3.wireforms;

import java.io.Serializable;

public class RecordsRequestResponse implements Message, Protocol, Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -201557229537541274L;
	private final String type = Protocol.RECORDS_REQUEST_RESPONSE;
	
	private String status;
	private String message;
	
	public RecordsRequestResponse(String status, String message)
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
