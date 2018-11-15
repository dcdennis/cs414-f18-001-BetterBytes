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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((invitee == null) ? 0 : invitee.hashCode());
		result = prime * result + ((inviter == null) ? 0 : inviter.hashCode());
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
		CreateInvitation other = (CreateInvitation) obj;
		if (invitee == null) {
			if (other.invitee != null)
				return false;
		} else if (!invitee.equals(other.invitee))
			return false;
		if (inviter == null) {
			if (other.inviter != null)
				return false;
		} else if (!inviter.equals(other.inviter))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

}
