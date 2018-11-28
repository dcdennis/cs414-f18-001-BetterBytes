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

	public RecordsRequestResponse(List<Game> games, Account account) {
		this.games = games;
		this.account = account;
	}

	public List<Game> getGames() {
		return games;
	}

	public Account getAccount() {
		return account;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((account == null) ? 0 : account.hashCode());
		result = prime * result + ((games == null) ? 0 : games.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RecordsRequestResponse other = (RecordsRequestResponse) obj;
		if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
			return false;
		if (games == null) {
			if (other.games != null)
				return false;
		} else if (!games.equals(other.games))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

}
