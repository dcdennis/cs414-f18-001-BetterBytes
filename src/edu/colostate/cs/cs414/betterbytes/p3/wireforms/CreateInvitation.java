package edu.colostate.cs.cs414.betterbytes.p3.wireforms;

import java.io.Serializable;
import java.util.List;

import edu.colostate.cs.cs414.betterbytes.p3.user.Account;
import edu.colostate.cs.cs414.betterbytes.p3.user.Invitation;

public class CreateInvitation  implements Message, Protocol, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7358388651791225247L;
	private final String type = Protocol.CREATE_INVITATION;
	
	private Account account;
	private List<Invitation> invites;
	
	
	
	public CreateInvitation(Account account, List<Invitation> invites)
	{
		this.account = account;
		this.invites = invites;
	}
	
	@Override
	public String getType() {
		return type;
	}

	public Account getAccount() {
		return account;
	}
	
	public List<Invitation> getInvitation() {
		return invites;
	}

}
