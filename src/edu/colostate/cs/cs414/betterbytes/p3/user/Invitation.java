package edu.colostate.cs.cs414.betterbytes.p3.user;

public class Invitation {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((recipient == null) ? 0 : recipient.hashCode());
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
		Invitation other = (Invitation) obj;
		if (recipient == null) {
			if (other.recipient != null)
				return false;
		} else if (!recipient.equals(other.recipient))
			return false;
		return true;
	}

	Player recipient;
	
	public Invitation() {}
	public Invitation(Player recipient) {
		this.recipient = recipient;
	}
	
	public void setInvitation(Player player) {
		this.recipient = player;
	}
		
	public Player getRecipient() {
		return this.recipient;
	}
	
}
