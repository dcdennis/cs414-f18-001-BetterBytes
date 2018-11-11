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
	
	private String inviter;
	private String invitee;
	
	
	
	public CreateInvitation(String inviter, String invitee)
	{
		this.inviter = inviter;
		this.invitee = invitee;
	}
	
	@Override
	public String getType() {
		return type;
	}

	public String getInviter() {
		return inviter;
	}
	
	public String getInvitee() {
		return invitee;
	}

}
