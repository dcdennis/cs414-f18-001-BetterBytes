package edu.colostate.cs.cs414.betterbytes.p4.client;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import javax.swing.UnsupportedLookAndFeelException;

import edu.colostate.cs.cs414.betterbytes.p4.hnefatafl.game.Game;
import edu.colostate.cs.cs414.betterbytes.p4.hnefatafl.game.GameResult;
import edu.colostate.cs.cs414.betterbytes.p4.server.wireforms.Message;
import edu.colostate.cs.cs414.betterbytes.p4.server.wireforms.RecordsRequest;
import edu.colostate.cs.cs414.betterbytes.p4.server.wireforms.RecordsRequestResponse;
import edu.colostate.cs.cs414.betterbytes.p4.server.wireforms.RespondToInvitation;
import edu.colostate.cs.cs414.betterbytes.p4.server.wireforms.SubmitMove;
import edu.colostate.cs.cs414.betterbytes.p4.server.wireforms.UserLogon;
import edu.colostate.cs.cs414.betterbytes.p4.server.wireforms.UserLogonResponse;
import edu.colostate.cs.cs414.betterbytes.p4.user.Account;
import edu.colostate.cs.cs414.betterbytes.p4.user.Invitation;

public class AIClient {

	static Path currentRelativePath = Paths.get("");
	static String path = currentRelativePath.toAbsolutePath().toString()
			+ "/edu/colostate/cs/cs414/betterbytes/p3/client/";
	private static final String AIScriptPath = "Python " + path + "HnefataflAI.py";

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, UnsupportedLookAndFeelException, IOException, InterruptedException {
		try {
			System.out.println("Getting client connection");
			ClientConnection connection = ClientConnection.getInstance();
			System.out.println("Setting up connection");
			connection.setUp(InetAddress.getLocalHost().getHostName(), 8080);
			connection.start();
			System.out.println("connection started");
			Thread.sleep(1000);
			// UserLogonResponse loginResponse = (UserLogonResponse) connection.send(new
			// UserLogon("AIUSER","AILOGON"));

			Message response = connection.send(new UserLogon("AIUSER", "AILOGON"));
			Account aiAccount = ((UserLogonResponse) response).getAcc();

			System.out.println("Logged on");
			// Account aiAccount = loginResponse.getAcc();
			while (true) {
				// Update the account object
				RecordsRequestResponse recordsResponse = (RecordsRequestResponse) connection
						.send(new RecordsRequest(aiAccount.getUsername()));
				aiAccount = recordsResponse.getAccount();
				List<Game> games = recordsResponse.getGames();
				// Accept all invites
				List<Invitation> invites = aiAccount.getInvites();
				if (invites != null)
					for (Invitation invite : invites) {
						connection.send(new RespondToInvitation(invite,true));
						Thread.sleep(100);
					}
				
				// Act on all games
				if (games != null) {
					for (Game game : games) {
						if (game.getResult() == GameResult.CONTINUE
								&& game.getTurn().getAccount().getUsername().equals("AIUSER"))
						{
							// Build the input strings for the python script
							String board = game.dump();
							String color = game.getTurn().getColor();

							// Write the input strings to a file
							System.out.println("Path: " + path);
							BufferedWriter writer = new BufferedWriter(new FileWriter(path + "incomingGame.txt"));
							writer.write(board + "\n" + color);
							writer.close();

							// Run the AI Script, wait for it to complete
							System.out.println("Running: " + AIScriptPath);
							Process AIScript = Runtime.getRuntime().exec(AIScriptPath);
							AIScript.waitFor();

							// Read in the output file, create a new Game object, and send it to the server
							String scriptOutput = "";
							try {
								scriptOutput = new String(Files.readAllBytes(Paths.get(path + "outgoingGame.txt")));
								Game gameUpdate = new Game(game, scriptOutput);
								connection.send(new SubmitMove(gameUpdate));
								Thread.sleep(100);

							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

	}
}
