package edu.colostate.cs.cs414.betterbytes.p3.testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.colostate.cs.cs414.betterbytes.p3.server.SQLDriver;
import edu.colostate.cs.cs414.betterbytes.p3.user.Account;

class SQLDriverTest {
	
	@Test
	void testAddUser()
	{
		// ctunnell must not be in the database before running this
		SQLDriver sql = SQLDriver.getInstance();
		assertEquals(true,sql.addUser("ctunnell", "ValidPassword"));		
	}
	
	
	@Test
	void deleteUser()
	{
		SQLDriver sql = SQLDriver.getInstance();
		sql.deleteUser("ctunnell", "ValidPassword");
	}
	
	@Test
	void testUsernameReturn()
	{
    	SQLDriver sql = SQLDriver.getInstance();
		String[] results = sql.loginQuery("ctunnell@rams.colostate.edu","TestPassword");
		assertEquals("ctunnell@rams.colostate.edu",results[0]);
	}
	
	@Test
	void testPasswordReturn()
	{
    	SQLDriver sql = SQLDriver.getInstance();
		String[] results = sql.loginQuery("ctunnell@rams.colostate.edu","TestPassword");
		assertEquals("TestPassword",results[1]);
	}
	
	@Test
	void testValidLogin()
	{
    	SQLDriver sql = SQLDriver.getInstance();		
		assertEquals(true,sql.checkLogin("ctunnell@rams.colostate.edu","TestPassword"));
	}
	
	@Test
	void testInvalidLogin()
	{
    	SQLDriver sql = SQLDriver.getInstance();		
		assertEquals(false,sql.checkLogin("ctunnell@rams.colostate.edu","WrongPassword"));
	}
	
	@Test
	//TODO doesnt return account from seriel object
	void testAccountRetrieve()
	{
		SQLDriver sql = SQLDriver.getInstance();
		String[] results = sql.loginQuery("ctunnell@rams.colostate.edu","TestPassword");
		assertEquals(null,results[2]);
	}
	
	@Test
	//TODO doesn't set serial object yet
	// Current implemantation sets account value to the username
	// Checking that when we return the account it is equal to the username
	// will be changed when we serialize Account
	void testSetAccount()
	{
		Account testAcc = new Account("ctunnell@rams.colostate.edu","TestPassword","ctunnell");
		
		SQLDriver sql = SQLDriver.getInstance();
		sql.setAccount(testAcc.getEmail(),testAcc.getPassword(), testAcc);		
		String[] results = sql.loginQuery(testAcc.getEmail(),testAcc.getPassword());
		
		assertEquals(testAcc.getUsername(),results[2]);
	}
}
