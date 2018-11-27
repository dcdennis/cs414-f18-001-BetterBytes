package edu.colostate.cs.cs414.betterbytes.p3.client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import javax.swing.UnsupportedLookAndFeelException;

import edu.colostate.cs.cs414.betterbytes.p3.game.Game;
import edu.colostate.cs.cs414.betterbytes.p3.game.GameResult;
import edu.colostate.cs.cs414.betterbytes.p3.user.Account;
import edu.colostate.cs.cs414.betterbytes.p3.user.Invitation;
import edu.colostate.cs.cs414.betterbytes.p3.wireforms.Message;
import edu.colostate.cs.cs414.betterbytes.p3.wireforms.RecordsRequest;
import edu.colostate.cs.cs414.betterbytes.p3.wireforms.RecordsRequestResponse;
import edu.colostate.cs.cs414.betterbytes.p3.wireforms.RespondToInvitation;
import edu.colostate.cs.cs414.betterbytes.p3.wireforms.SubmitMove;
import edu.colostate.cs.cs414.betterbytes.p3.wireforms.UserLogon;
import edu.colostate.cs.cs414.betterbytes.p3.wireforms.UserLogonResponse;


public class AIClient {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
	IllegalAccessException, UnsupportedLookAndFeelException, IOException, InterruptedException 
	{
		try 
		{
			System.out.println("Getting client connection");
			ClientConnection connection = ClientConnection.getInstance();
			System.out.println("Setting up connection");
			connection.setUp(InetAddress.getLocalHost().getHostName(), 8080);
			connection.start();
			System.out.println("connection started");
			UserLogonResponse loginResponse = (UserLogonResponse) connection.send(new UserLogon("AIUSER","AILOGGON"));
			System.out.println("Logged on");
			Account aiAccount = loginResponse.getAcc();
			while(true)
			{
				//Update the account object
				RecordsRequestResponse recordsResponse = (RecordsRequestResponse) connection.send(new RecordsRequest(aiAccount.getUsername()));
				aiAccount = recordsResponse.getAccount();
				List<Game> games = recordsResponse.getGames();
				//Accept all invites
				List<Invitation> invites = aiAccount.getInvites();
				for(Invitation invite : invites)
				{
					connection.send(new RespondToInvitation(invite));
				}
				//Act on all games
				for(Game game : games)
				{
					if(game.getResult() == GameResult.CONTINUE && game.getTurn().getAccount().getUsername().equals("AIUSER"));
					{
						//THis is where the python call goes
						Game gameUpdate = null;
						connection.send(new SubmitMove(gameUpdate));
					}
				}
			}
		} 
		catch (UnknownHostException e) 
		{
		e.printStackTrace();
		}

	}
}
