package edu.colostate.cs.cs414.betterbytes.p3.wireforms;

import java.io.Serializable;

import edu.colostate.cs.cs414.betterbytes.p3.user.Account;

public class RecordsRequestResponse implements Message, Protocol, Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -201557229537541274L;
	private final String type = Protocol.RECORDS_REQUEST_RESPONSE;
	
	private String status;
	private String message;
	private Account account;
	
	public RecordsRequestResponse(String status, String message, Account account)
	{
		this.status = status;
		this.message = message;
		this.account = account;
	}
	
	public String getStatus() {return status;}
	public String getMessage() {return message;}
	public Account getAccount() {return account;}
	@Override
	public String getType() {
		return type;
	}

}
