package edu.colostate.cs.cs414.betterbytes.p3.client;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import edu.colostate.cs.cs414.betterbytes.p3.ui.*;
import edu.colostate.cs.cs414.betterbytes.p3.wireforms.UserRegistration;

public class DemoClient {
	public static void main(String[] args)
	{
			try {
				//UI.main(null); // start up UI
				System.out.println("Getting client connection");
				ClientConnection connection = ClientConnection.getInstance();
				System.out.println("Setting up connection");
				connection.setUp(InetAddress.getLocalHost().getHostName(), 8080);
				System.out.println("starting connetion");
				connection.start();
				//Unfortunatly, I think theres a race condition where if the client trys to send something to soon after starting, 
				// the message gets lost. I dont think this will present an issue in application, but for this basic test I'm leaving this
				// sleep in. 
				TimeUnit.SECONDS.sleep(1);
				System.out.println("sending registration");
				connection.send(new UserRegistration("USERNAME","PASSWORDHASH"));
				System.out.println("done");
			} catch (UnknownHostException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}
}
