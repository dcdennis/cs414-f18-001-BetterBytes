package edu.colostate.cs.cs414.betterbytes.p3.client;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import javax.swing.UnsupportedLookAndFeelException;

import edu.colostate.cs.cs414.betterbytes.p3.ui.*;
import edu.colostate.cs.cs414.betterbytes.p3.wireforms.UserRegistration;

public class DemoClient {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException
	{
			try {
				//UI.main(null); // start up UI
				System.out.println("Getting client connection");
				ClientConnection connection = ClientConnection.getInstance();
				System.out.println("Setting up connection");
				connection.setUp(InetAddress.getLocalHost().getHostName(), 8080);
				connection.start();
				System.out.println("connection started");
				//Unfortunatly, I think theres a race condition where if the client trys to send something to soon after starting, 
				// the message gets lost. I dont think this will present an issue in application, but for this basic test I'm leaving this
				// sleep in. 
				//TimeUnit.SECONDS.sleep(5);
				//System.out.println("sending registration");
				//connection.send(new UserRegistration("USERNAME","PASSWORDHASH"));
				//System.out.println("done");
				
				
				java.awt.EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							new UI(connection).setVisible(true);
						} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
								| UnsupportedLookAndFeelException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}
}
