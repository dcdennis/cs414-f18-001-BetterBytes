package edu.colostate.cs.cs414.betterbytes.p3.wireforms;

import java.io.Serializable;

import edu.colostate.cs.cs414.betterbytes.p3.user.Account;
import edu.colostate.cs.cs414.betterbytes.p3.user.Invitation;

public class RespondToInvitation implements Message, Protocol, Serializable {
	
	
	
	private static final long serialVersionUID = -1869969796906421019L;
	private final String type = Protocol.RESPOND_TO_INVITATION;
	
	private Invitation invite;
	private Account responder;
	
	
	public Invitation getInvitation()
	{
		return invite;
	}
	public Account getResponder()
	{
		return responder;
	}
	public String getType() {
		return type;
	}

}
