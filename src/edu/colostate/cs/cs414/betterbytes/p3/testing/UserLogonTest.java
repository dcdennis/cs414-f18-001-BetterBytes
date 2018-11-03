package edu.colostate.cs.cs414.betterbytes.p3.testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.colostate.cs.cs414.betterbytes.p3.wireforms.UserLogon;

class UserLogonTest {

	@Test
	void test() {
		UserLogon m1 = new UserLogon("USERNAME","PASSWORD");
		String m1String = m1.getStringRepresentation();
		UserLogon m2 = new UserLogon(m1String);
		assertTrue(m1.equals(m2));
	}

}
