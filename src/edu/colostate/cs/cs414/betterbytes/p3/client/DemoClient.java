package edu.colostate.cs.cs414.betterbytes.p3.client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import javax.swing.UnsupportedLookAndFeelException;

import edu.colostate.cs.cs414.betterbytes.p3.ui.*;
import edu.colostate.cs.cs414.betterbytes.p3.wireforms.UserRegistration;

public class DemoClient {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, IOException, InterruptedException
	{
			try {
				//UI.main(null); // start up UI
				System.out.println("Getting client connection");
				ClientConnection connection = ClientConnection.getInstance();
				System.out.println("Setting up connection");
				connection.setUp(InetAddress.getLocalHost().getHostName(), 8080);
				connection.start();
				System.out.println("connection started");		
				
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
