package edu.colostate.cs.cs414.betterbytes.p3.client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;

import edu.colostate.cs.cs414.betterbytes.p3.ui.*;

public class DemoClient {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, UnsupportedLookAndFeelException, IOException, InterruptedException {
		// UI.main(null); // start up UI
		System.out.println("Getting client connection");
		ClientConnection connection = ClientConnection.getInstance();
		System.out.println("Setting up connection");
		String in = JOptionPane.showInputDialog(null, "Enter Connection (Ex. localhost:8080): ", "Connection",
				JOptionPane.WARNING_MESSAGE);
		String addr = in.split(":")[0];
		int port = Integer.parseInt(in.split(":")[1]);
		connection.setUp(addr, port);
		connection.start();
		System.out.println("connection started"); 

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				try { 
					for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
							.getInstalledLookAndFeels()) {
						if ("Nimbus".equals(info.getName())) {
							javax.swing.UIManager.setLookAndFeel(info.getClassName());
							break;
						}
					}

					new UI(connection).setVisible(true);
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

	}
}
