package edu.colostate.cs.cs414.betterbytes.p3.testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.colostate.cs.cs414.betterbytes.p3.wireforms.UserLogonResponse;

class UserLogonResponseTest {

	@Test
	void test() {
		UserLogonResponse m1 = new UserLogonResponse("STATUS","MESSAGE");
		String m1String = m1.getStringRepresentation();
		UserLogonResponse m2 = new UserLogonResponse(m1String);
		assertTrue(m1.equals(m2));
	}

}
