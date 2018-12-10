package edu.colostate.cs.cs414.betterbytes.p4.user.testing;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import edu.colostate.cs.cs414.betterbytes.p4.user.Account;
import edu.colostate.cs.cs414.betterbytes.p4.user.Invitation;
import edu.colostate.cs.cs414.betterbytes.p4.user.Player;

public class AccountTest {

	@Test
	public void testAccountUsername() {
		Account account = new Account("devin", "testPassword");
		assertEquals(account.getUsername(), "devin");
	}
	
	@Test
	public void testAccountPassword() {
		Account account = new Account("devin", "testPassword");
		assertEquals(account.getPassword(), "testPassword");
	}
	
	@Test
	public void testAccountInvites() {
		Account account = new Account("devin", "testPassword");
		account.addInvite(new Invitation("devin", "carson"));
		assertEquals(account.getInvites().get(0), new Invitation("devin", "carson"));
	}
	
	@Test
	public void testAccountPlayers() {
		Account account = new Account("devin", "testPassword");
		account.addPlayer(new Player(account));
		ArrayList<Player> players = account.getPlayers();
		assertEquals(players.get(0), new Player(account));
	}
	
}
