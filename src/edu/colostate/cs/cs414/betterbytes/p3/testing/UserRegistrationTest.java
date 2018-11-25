package edu.colostate.cs.cs414.betterbytes.p3.testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.colostate.cs.cs414.betterbytes.p3.wireforms.UserRegistration;

class UserRegistrationTest {

	@Test
	void test() {
		UserRegistration m1 = new UserRegistration("USERNAME", "PASSWORDHASH");
		String m1String = m1.getStringRepresentation();
		UserRegistration m2 = new UserRegistration(m1String);
		assertTrue(m1.equals(m2));
	}

}
