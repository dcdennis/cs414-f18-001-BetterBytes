package edu.colostate.cs.cs414.betterbytes.p3.testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.colostate.cs.cs414.betterbytes.p3.wireforms.UserRegistrationResponse;

class UserRegistrationResponseTest {

	@Test
	void test() {
		UserRegistrationResponse m1 = new UserRegistrationResponse("STATUS","MESSAGE");
		String m1String = m1.getStringRepresentation();
		UserRegistrationResponse m2 = new UserRegistrationResponse(m1String);
		assertTrue(m1.equals(m2));
	}

}
