package edu.colostate.cs.cs414.betterbytes.p4.user.testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.colostate.cs.cs414.betterbytes.p4.user.Invitation;

public class InvitationTest {

	
	@Test
	public void testInviteSender() {
		Invitation invite = new Invitation("devin", "john");
		assertEquals(invite.getSender(), "devin");
	}
	
	@Test
	public void testInviteRecepient() {
		Invitation invite = new Invitation("devin", "john");
		assertEquals(invite.getRecipient(), "john");
	}
	
	@Test 
	public void testInviteEquals() {
		Invitation invite = new Invitation("devin", "john");
		Invitation inviteCpy = new Invitation("devin", "john");
		assertTrue(invite.equals(inviteCpy));
	}
}
