package edu.colostate.cs.cs414.betterbytes.p3.wireforms;

import java.io.Serializable;

import edu.colostate.cs.cs414.betterbytes.p3.user.Account;

public class RecordsRequest implements Message, Protocol, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4473108516408911278L;
	private final String type = Protocol.RECORDS_REQUEST;
	private String username;
	
	public RecordsRequest(String username)
	{
		this.username = username;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public String getType() {
		return type;
	}

}
