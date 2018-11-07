package edu.colostate.cs.cs414.betterbytes.p3.wireforms;

import java.io.Serializable;

public class SubmitMoveResponse implements Message, Protocol, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1393201663237341644L;
	/**
	 * 
	 */
	private final String type = Protocol.SUBMIT_MOVE_RESPONSE;
	private String status;
	private String message;
	
	public SubmitMoveResponse(String status, String message)
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
