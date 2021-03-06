package edu.colostate.cs.cs414.betterbytes.p4.server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import edu.colostate.cs.cs414.betterbytes.p4.client.ClientConnection;
import edu.colostate.cs.cs414.betterbytes.p4.client.ui.GameFrame;
import edu.colostate.cs.cs414.betterbytes.p4.hnefatafl.game.Game;
import edu.colostate.cs.cs414.betterbytes.p4.hnefatafl.game.GameResult;
import edu.colostate.cs.cs414.betterbytes.p4.server.utilities.RulesEngine;
import edu.colostate.cs.cs414.betterbytes.p4.server.utilities.Serializer;
import edu.colostate.cs.cs414.betterbytes.p4.server.utilities.Tools;
import edu.colostate.cs.cs414.betterbytes.p4.server.wireforms.*;
import edu.colostate.cs.cs414.betterbytes.p4.user.Account;
import edu.colostate.cs.cs414.betterbytes.p4.user.Invitation;
import edu.colostate.cs.cs414.betterbytes.p4.user.Player;

/**
 * WorkerThread class. Implements the Protocol interface. Checks incoming tasks to a switch statement.
 * @version 1.0
 * @see Protocol
 */
public class WorkerThread extends Thread implements edu.colostate.cs.cs414.betterbytes.p4.server.wireforms.Protocol {
	private volatile boolean running = true;
	private final boolean debug = true;
	private ThreadPoolManager manager;
	private SQLDriver sql;
	private RulesEngine rules = RulesEngine.getInstance();

	private int threadID;

	/**
	 * WorkerThread constructor. Sets the threadID and sets the ThreadPoolManager to the singleton instance object.
	 * @param threadID ID of the thread
	 */
	public WorkerThread(int threadID) {
		this.threadID = threadID;
		manager = ThreadPoolManager.getInstance();
		sql = SQLDriver.getInstance();
	}

	/**
	 * Overrides Thread.run. While the Thread isn't terminated, wait for a new task. Execute the switch case based
	 * on the task given by the ThreadPoolManager when a WorkerThread receives it.
	 */
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

						boolean created = sql.addUser(new Account(username, password));
						Message outgoing = null;

						if (created) {
							outgoing = new UserRegistrationResponse(true, "User was created successfully");
						} else {
							outgoing = new UserRegistrationResponse(false,
									"Most likely a duplicated username, could also be a failed SQL connection");
						}
						send(outgoing, buffer, channel, debug);
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
							outgoing = new UserLogonResponse(true, "User was verified and logged in", acc);
						} else {
							outgoing = new UserLogonResponse(false, "User was not Verified.", null);
						}
						// Outgoing is the reply to the client program
						send(outgoing, buffer, channel, debug);
						break;
					}
					case (RECORDS_REQUEST): {
						RecordsRequest requestMessage = (RecordsRequest) message;
						String requestUser = requestMessage.getUsername();

						// done, but relies on methods that are themselves TODO
						Account update = sql.getAccount(requestUser);
						System.out.println("ACC: " + update.getUsername());
						List<Game> games = sql.getGames(update);

						for (Game g : games) {
							System.out.println(g.toString());
						}

						send(new RecordsRequestResponse(games, update), buffer, channel, debug);
						break;
					}
					case (CREATE_INVITATION): {
						CreateInvitation inviteMessage = (CreateInvitation) message;
						String inviter = inviteMessage.getInviter();
						String invitee = inviteMessage.getInvitee();

						Account inviteeAccount = sql.getAccount(invitee);
						inviteeAccount.addInvite(new Invitation(inviter, invitee));
						sql.setAccount(inviteeAccount);

						send(new CreateInvitationResponse(true, "Invitation Sent"), buffer, channel, debug);
						break;
					}
					case (RESPOND_TO_INVITATION): {
						RespondToInvitation respondMessage = (RespondToInvitation) message;
						Invitation acceptedInvite = respondMessage.getInvitation();
						String recipient = acceptedInvite.getRecipient();
						String sender = acceptedInvite.getSender();
						Account updateRecipient = sql.getAccount(recipient);
						Account updateSender = sql.getAccount(sender);

						if (updateRecipient.getInvites().remove(acceptedInvite)) {
							System.out.println("-----------------------------    Removed invite from account: " + updateRecipient.getUsername());
							sql.setAccount(updateRecipient);
						}

						if (respondMessage.isAccept()) {
							Player attacker = new Player(updateSender, "black");
							Player defender = new Player(updateRecipient, "white");

							updateSender.addPlayer(attacker);
							updateRecipient.addPlayer(defender);

							sql.setAccount(updateSender);
							sql.setAccount(updateRecipient);

							Game g1 = new Game(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").toString(), attacker,
									defender);
							sql.addGame(sender, recipient, g1);

							send(new RespondToInvitationResponse(true, "Game Added to Account"), buffer, channel,
									debug);
						} else {
							send(new RespondToInvitationResponse(true, "Invited Rejected"), buffer, channel, debug);
						}
						break;
					}
					case (SUBMIT_MOVE): {
						SubmitMove moveMessage = (SubmitMove) message;
						Game gameUpdate = moveMessage.getGameUpdate();
						Game oldGame = sql.getGame(gameUpdate.getAttacker(), gameUpdate.getDefender());

						GameResult status;
						if(gameUpdate.getResult() ==GameResult.CONTINUE)
						{
							gameUpdate = rules.processCaptures(oldGame, gameUpdate);
							status = rules.gameHasEnded(gameUpdate);
							gameUpdate.setResult(status);
						}
						else
							status = gameUpdate.getResult();
						if (status != GameResult.CONTINUE) {
							gameUpdate.setEndTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").toString());
							Account defender = gameUpdate.getDefender().getAccount();
							Account attacker = gameUpdate.getAttacker().getAccount();
							if (status == GameResult.WHITE) {
								attacker.getStats().addLoss();
								defender.getStats().addWin();
							} else if (status == GameResult.BLACK) {
								attacker.getStats().addWin();
								defender.getStats().addLoss();
							} else {
								attacker.getStats().addLoss();
								defender.getStats().addLoss();
							}
							sql.setAccount(defender);
							sql.setAccount(attacker);
							sql.deleteGame(attacker, defender);
						}
						else {
						gameUpdate.changeTurns();
						sql.updateGame(gameUpdate.getAttacker(), gameUpdate.getDefender(), gameUpdate);
						}
						send(new SubmitMoveResponse(true, "Move Submitted"), buffer, channel, debug);

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

	/**
	 * Sets running to false. This stops the main while loop of WorkerThread.run()
	 */
	public void terminate() {
		running = false;
	}

	/**
	 * Send an outgoing message with a buffer over a given channel
	 * @param outgoing Message to send
	 * @param buffer Buffer to place the message into
	 * @param channel Channel to send the message over
	 * @param debug Debug setting. True has the WorkerThread print off when it sends the message.
	 * @throws IOException
	 */
	private void send(Message outgoing, ByteBuffer buffer, SocketChannel channel, boolean debug) throws IOException {
		if (debug)
			System.out.println("Worker Thread " + threadID + " sending new message: " + outgoing.toString());
		buffer = ByteBuffer.wrap(Serializer.serialize(outgoing));
		channel.write(buffer);
		if (debug)
			System.out.println("Worker Thread " + threadID + "sent message");
	}

}
