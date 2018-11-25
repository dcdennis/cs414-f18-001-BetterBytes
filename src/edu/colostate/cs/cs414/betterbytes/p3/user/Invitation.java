package edu.colostate.cs.cs414.betterbytes.p3.user;

public class Invitation {

	String sender;
	String recipient;
	
	public Invitation(String sender, String recipient) {
		this.sender = sender;
		this.recipient = recipient;
	}

	public String getSender() {
		return this.sender;
	}
	
	public String getRecipient() {
		return this.recipient;
	}
	
}
