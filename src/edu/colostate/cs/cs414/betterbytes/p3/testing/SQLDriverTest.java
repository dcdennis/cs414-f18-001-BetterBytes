package edu.colostate.cs.cs414.betterbytes.p3.testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import edu.colostate.cs.cs414.betterbytes.p3.game.Game;
import edu.colostate.cs.cs414.betterbytes.p3.server.SQLDriver;
import edu.colostate.cs.cs414.betterbytes.p3.user.Account;
import edu.colostate.cs.cs414.betterbytes.p3.user.Player;
import edu.colostate.cs.cs414.betterbytes.p3.utilities.Serializer;

class SQLDriverTest {

	@Test
	void testUsernameReturn() {
		SQLDriver sql = SQLDriver.getInstance();
		String[] results = sql.loginQuery("ctunnell@rams.colostate.edu", "TestPassword");
		assertEquals("ctunnell@rams.colostate.edu", results[0]);
	} 

	@Test
	void testPasswordReturn() {
		SQLDriver sql = SQLDriver.getInstance();
		String[] results = sql.loginQuery("ctunnell@rams.colostate.edu", "TestPassword");
		assertEquals("TestPassword", results[1]);
	}

	@Test
	void testValidLogin() {
		SQLDriver sql = SQLDriver.getInstance();
		assertEquals(true, sql.checkLogin("ctunnell@rams.colostate.edu", "TestPassword"));
	}

	@Test
	void testInvalidLogin() {
		SQLDriver sql = SQLDriver.getInstance();
		assertEquals(false, sql.checkLogin("ctunnell@rams.colostate.edu", "WrongPassword"));
	}

	@Test
	void testGetAccount() {
		Account testAcc = new Account("ctunnell", "TestPassword");
		SQLDriver sql = SQLDriver.getInstance();
		Account recAcc = sql.getAccount(testAcc.getUsername(), testAcc.getPassword());
		assertTrue(testAcc.equals(recAcc));
	}
	
	@Test
	void testGetAccountUsername() {
		Account testAcc = new Account("ctunnell", "TestPassword");
		SQLDriver sql = SQLDriver.getInstance();
		Account recAcc = sql.getAccount(testAcc.getUsername());
		assertTrue(testAcc.equals(recAcc));
	}

	@Test
	void testSetAccount() {
		Account testAcc = new Account("ctunnell", "TestPassword");
		SQLDriver sql = SQLDriver.getInstance();
		sql.setAccount(testAcc.getUsername(), testAcc.getPassword(), testAcc);
		String[] results = sql.loginQuery(testAcc.getUsername(), testAcc.getPassword());

		Account received = Serializer.deserializeAccount(results[2].getBytes());
		assertTrue(testAcc.equals(received));
	}

	@Test
	void testGetGameAcc() {
		
		SQLDriver sql = SQLDriver.getInstance();
		Account testAcc1 = sql.getAccount("ctunnell");
		Player p1 = new Player(testAcc1);
		Account testAcc2 = sql.getAccount("Jhpokorski");
		Player p2 = new Player(testAcc2);
		Game g1 = new Game("0:0", p1, p2);
		
		sql.addGame(p1, p2, g1);
		Game g2 = sql.getGame(testAcc1,testAcc2);
		assertEquals(true,g1.equals(g2));				
	}

	@Test
	void testGetGameAccFlipped() {
		
		SQLDriver sql = SQLDriver.getInstance();
		Account testAcc1 = sql.getAccount("ctunnell");
		Player p1 = new Player(testAcc1);
		Account testAcc2 = sql.getAccount("Jhpokorski");
		Player p2 = new Player(testAcc2);
		Game g1 = new Game("0:0", p1, p2);
		
		sql.addGame(p1, p2, g1);
		Game g2 = sql.getGame(testAcc2,testAcc1);
		assertEquals(true,g1.equals(g2));				
	}
	@Test
	void testGetGamePlr() {		
		SQLDriver sql = SQLDriver.getInstance();
		Account testAcc1 = sql.getAccount("ctunnell");
		Player p1 = new Player(testAcc1);
		Account testAcc2 = sql.getAccount("Jhpokorski");
		Player p2 = new Player(testAcc2);
		Game g1 = new Game("0:0", p1, p2);
		
		sql.addGame(p1, p2, g1);
		Game g2 = sql.getGame(p1,p2);
		assertEquals(true,g1.equals(g2));				
	}

	@Test
	void testGetGamePlrFlipped() {
		SQLDriver sql = SQLDriver.getInstance();
		Account testAcc1 = sql.getAccount("ctunnell");
		Player p1 = new Player(testAcc1);
		Account testAcc2 = sql.getAccount("Jhpokorski");
		Player p2 = new Player(testAcc2);
		Game g1 = new Game("0:0", p1, p2);
		
		sql.addGame(p1, p2, g1);
		Game g2 = sql.getGame(p2,p1);
		assertEquals(true,g1.equals(g2));				
	}



	/*
	 * 
	 * These tests have been removed since they affect the database state They have
	 * all been tested to pass They are here since they are not guaranteed to run in
	 * order and can ruin other tests
	 * 
	 * @Test void testDeleteGame() { SQLDriver sql = SQLDriver.getInstance();
	 * Account testAcc1 = new Account("ctunnell","TestPassword","ctunnell"); Account
	 * testAcc2 = new Account("Jhpokorski","TestPassword2","jhpokorski");
	 * assertEquals(true, sql.deleteGame(testAcc1, testAcc2)); }
	 * 
	 * 
	 * @Test void testAddGame() { SQLDriver sql = SQLDriver.getInstance(); Account
	 * testAcc1 = new Account("ctunnell","TestPassword","ctunnell"); Account
	 * testAcc2 = new Account("Jhpokorski","TestPassword2","jhpokorski");
	 * sql.addGame(testAcc1, testAcc2, "Game In Progress");
	 * assertEquals("Game In Progress", sql.getGame(testAcc1, testAcc2)); }
	 * 

	 * 
	 * 
	 * @Test void deleteUser() { SQLDriver sql = SQLDriver.getInstance();
	 * sql.deleteUser("ctunnell", "ValidPassword"); }
	 * 
	 * 
	 * 
	 */

}
