package edu.colostate.cs.cs414.betterbytes.p3.user;

import java.io.Serializable;

public class Invitation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1112740004632087227L;
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
