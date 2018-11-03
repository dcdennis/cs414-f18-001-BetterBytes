package edu.colostate.cs.cs414.betterbytes.p3.testing;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.net.InetAddress;

import org.junit.jupiter.api.Test;

import edu.colostate.cs.cs414.betterbytes.p3.client.ClientConnection;
import edu.colostate.cs.cs414.betterbytes.p3.server.Server;
import edu.colostate.cs.cs414.betterbytes.p3.wireforms.UserRegistration;
import edu.colostate.cs.cs414.betterbytes.p3.wireforms.Protocol;

class ClientConnectionTest {

	@Test
	void test() {
		
		try {
			Server server = new Server(8080,1);
			
			ClientConnection cc = ClientConnection.getInstance();
			cc.setUp(InetAddress.getLocalHost().getHostName(), 8080);
			server.serve();
			byte[] response = cc.send(new UserRegistration("Username","password"));
			String responseString = new String(response);
			assertTrue(responseString.substring(0,2).equals(Protocol.USER_REGISTRATION_RESPONSE));
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		
		
	}

}
