package edu.colostate.cs.cs414.betterbytes.p3.wireforms;

import java.io.Serializable;

import edu.colostate.cs.cs414.betterbytes.p3.user.Account;

public class RecordsRequest implements Message, Protocol, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4473108516408911278L;
	private final String type = Protocol.RECORDS_REQUEST;
	private Account requester;
	
	public RecordsRequest(Account account)
	{
		this.requester = account;
	}
	
	public Account getAccount()
	{
		return requester;
	}
	
	public String getType() {
		return type;
	}

}
