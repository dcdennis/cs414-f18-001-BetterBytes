package edu.colostate.cs.cs414.betterbytes.p3.testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.colostate.cs.cs414.betterbytes.p3.server.SQLDriver;

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
}
