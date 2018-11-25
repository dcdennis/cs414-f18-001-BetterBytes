package edu.colostate.cs.cs414.betterbytes.p3.server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

import edu.colostate.cs.cs414.betterbytes.p3.game.Game;
import edu.colostate.cs.cs414.betterbytes.p3.user.Account;
import edu.colostate.cs.cs414.betterbytes.p3.user.Invitation;
import edu.colostate.cs.cs414.betterbytes.p3.utilities.Serializer;
import edu.colostate.cs.cs414.betterbytes.p3.wireforms.*;

public class WorkerThread extends Thread implements edu.colostate.cs.cs414.betterbytes.p3.wireforms.Protocol {
	private volatile boolean running = true;
	private final boolean debug = true;
	private ThreadPoolManager manager;
	private SQLDriver sql;

	private int threadID;

	public WorkerThread(int threadID) {
		this.threadID = threadID;
		manager = ThreadPoolManager.getInstance();
		sql = SQLDriver.getInstance();
	}

	@Override
	public void run() {
		while (running) {
			if (debug)
				System.out.println("WORKER THREAD " + threadID + " Waiting for new task");

			// get the next task from the blocking queue
			SelectionKey newTask = manager.nextTask();

			
			// if(debug)
			// System.out.println("Worker Thread " + threadID + " received new task");

			if (newTask != null) {
				if (debug)
					System.out.println("Worker Thread " + threadID + " received new task: " + newTask.toString());
				SocketChannel channel = (SocketChannel) newTask.channel();
				ByteBuffer buffer = ByteBuffer.allocate(8000);
				try {
					channel.read(buffer);
					byte[] packet = buffer.array();
					if (debug)
						System.out.println("Worker Thread " + threadID + " received new message: " + packet.toString());
					Message message = Serializer.deserializeMessage(packet);
					if (debug)
						System.out
								.println("Worker Thread " + threadID + " received new message: " + message.toString());
					String messageCode = message.getType();
					buffer.flip();
					buffer.clear();
					newTask.interestOps(SelectionKey.OP_WRITE);
						switch (messageCode) {
						/**
						 * This switch statement should be the only thing that has to get edited from
						 * here on out. When we want to add functionality, it should always either be
						 * adding a new case, if a new wireform is needed, or by altering code in the
						 * case of the message being responded to.
						 */
						case (USER_REGISTRATION): {
							UserRegistration registrationMessage = (UserRegistration) message;
							String username = registrationMessage.getUsername();
							String password = registrationMessage.getPasswordHash();
	
							boolean created = sql.addUser(username, password);
							Message outgoing = null;
	
							if (created) {
								outgoing = new UserRegistrationResponse(true, "User was created successfully");
							} else {
								outgoing = new UserRegistrationResponse(false,
										"Most likely a duplicated username, could also be a failed SQL connection");
							}
							send(outgoing,buffer,channel,debug);
							break;
						}
	
						case (USER_LOGON): {
							UserLogon logonMessage = (UserLogon) message;
	
							String username = logonMessage.getUsername();
							String password = logonMessage.getPasswordHash();
							// Returns true if the user is validated
							boolean loggedIn = sql.checkLogin(username, password);
							Message outgoing = null;
	
							if (loggedIn) {
								Account acc = sql.getAccount(username, password);
								outgoing = new UserLogonResponse(true, "User was verified and logged in",acc);
							} else {
								outgoing = new UserLogonResponse(false, "User was not Verified.",null);
							}
							// Outgoing is the reply to the client program
							send(outgoing,buffer,channel,debug);
							break;
						}
						case (RECORDS_REQUEST):
						{
							RecordsRequest requestMessage = (RecordsRequest) message;
							String requestUser = requestMessage.getUsername();
							
							
							//get newest updated account object and games from db TODO
							Account update = new Account();
							List<Game> games = new ArrayList<Game>();
							
							
							send(new RecordsRequestResponse(games, update),buffer,channel,debug);
							break;
						}
						case (CREATE_INVITATION):
						{
							CreateInvitation inviteMessage = (CreateInvitation) message;
							String inviter = inviteMessage.getInviter();
							String invitee = inviteMessage.getInvitee();
							
							//Register invitation to invitee's account object in the db TODO
							
							send(new CreateInvitationResponse(false,"UNIMPLIMENTED"),buffer,channel,debug);
							break;
						}
						case (RESPOND_TO_INVITATION):
						{
							
							RespondToInvitation respondMessage = (RespondToInvitation) message;
							Invitation acceptedInvite = respondMessage.getInvitation();
							
							//Create new game on db TODO
							
							send(new RespondToInvitationResponse(false,"UNIMPLIMENTED"),buffer,channel,debug);
							break;
						}
						case (SUBMIT_MOVE):
						{
							SubmitMove moveMessage = (SubmitMove) message;
							Game gameUpdate = moveMessage.getGameUpdate();
							//Check that the move is legal, process captures, check if won, update game state on DB TODO
							
							
							send(new RespondToInvitationResponse(false,"UNIMPLIMENTED"),buffer,channel,debug);
							
							break;
						}
					}
					
					buffer.flip();
					buffer.clear();

				} catch (IOException e) {
					newTask.cancel();
					try {
						channel.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				newTask.interestOps(SelectionKey.OP_READ);
			}
		}
		System.out.println("THREAD " + threadID + " terminating");
	}

	public void terminate() {
		running = false;
	}
	
	private void send(Message outgoing,ByteBuffer buffer, SocketChannel channel, boolean debug) throws IOException
	{
		if (debug)
			System.out.println(
					"Worker Thread " + threadID + " sending new message: " + outgoing.toString());
		buffer = ByteBuffer.wrap(Serializer.serialize(outgoing));
		channel.write(buffer);
		if (debug)
			System.out.println("Worker Thread " + threadID + "sent message");
	}

}
