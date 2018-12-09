package edu.colostate.cs.cs414.betterbytes.p4.user;

import java.io.Serializable;

/**
 * Invitation class. Records sender and recipient.
 * @version 1.0
 */
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

	@Override
	public boolean equals(Object obj) {
		
		if(this == null || obj == null)
			return false;
		if(this.sender.equals(((Invitation)obj).getSender()) &&
		   this.recipient.equals(((Invitation)obj).getRecipient()))
				return true;
		else
			return false;
	}

	public String getRecipient() {
		return this.recipient;
	}

}
