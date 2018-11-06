package edu.colostate.cs.cs414.betterbytes.p3.user;

public class Invitation {

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
