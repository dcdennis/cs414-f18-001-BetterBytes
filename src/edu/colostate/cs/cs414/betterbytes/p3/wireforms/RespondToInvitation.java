package edu.colostate.cs.cs414.betterbytes.p3.wireforms;

import java.io.Serializable;

import edu.colostate.cs.cs414.betterbytes.p3.user.Account;
import edu.colostate.cs.cs414.betterbytes.p3.user.Invitation;

public class RespondToInvitation implements Message, Protocol, Serializable {
	
	
	
	private static final long serialVersionUID = -1869969796906421019L;
	private final String type = Protocol.RESPOND_TO_INVITATION;
	
	private Invitation invite;
	
	public RespondToInvitation(Invitation invite)
	{
		this.invite = invite;
	}
	
	public Invitation getInvitation()
	{
		return invite;
	}

	public String getType() {
		return type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((invite == null) ? 0 : invite.hashCode());
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
		RespondToInvitation other = (RespondToInvitation) obj;
		if (invite == null) {
			if (other.invite != null)
				return false;
		} else if (!invite.equals(other.invite))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

}
