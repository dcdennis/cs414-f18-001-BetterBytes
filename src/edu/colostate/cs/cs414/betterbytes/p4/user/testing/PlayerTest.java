package edu.colostate.cs.cs414.betterbytes.p4.user.testing;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.colostate.cs.cs414.betterbytes.p4.user.Account;
import edu.colostate.cs.cs414.betterbytes.p4.user.Player;

public class PlayerTest {

	
	@Test
	public void testPlayerAccount() {
		Account account = new Account("devin", "testPassword");
		Player player = new Player(account);
		assertEquals(player.getAccount(), account);
	}
	
	@Test
	public void testPlayerColor() {
		Account account = new Account("devin", "testPassword");
		Player player = new Player(account, "black");
		assertEquals(player.getColor(), "black");
	}
}
