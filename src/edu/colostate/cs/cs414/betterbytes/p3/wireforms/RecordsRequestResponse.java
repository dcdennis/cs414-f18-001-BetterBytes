package edu.colostate.cs.cs414.betterbytes.p3.wireforms;

import java.io.Serializable;
import java.util.List;

import edu.colostate.cs.cs414.betterbytes.p3.game.Game;
import edu.colostate.cs.cs414.betterbytes.p3.user.Account;

public class RecordsRequestResponse implements Message, Protocol, Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -201557229537541274L;
	private final String type = Protocol.RECORDS_REQUEST_RESPONSE;
	
	private List<Game> games;
	private Account account;
	
	public RecordsRequestResponse(List<Game> games, Account account)
	{
		this.games = games;
		this.account = account;
	}
	
	public String getGames() {return games;}
	public Account getAccount() {return account;}
	@Override
	public String getType() {
		return type;
	}

}
