package edu.colostate.cs.cs414.betterbytes.p3.server;

import java.nio.channels.SocketChannel;
import java.util.HashMap;

import edu.colostate.cs.cs414.betterbytes.p3.user.Account;

// This class allows access to an active account's socket channel
// The server can now write to a specified client
// TODO: when a client logins in we need to create an instance of their account from the db
//		 when the Account class has been serialized we can hook it to their SocketChannel

/**
 * Allows access to account's socket channel, allowing for writing to a specified client.
 * @version 1.0
 */
public class ServerData {

	// FIELDS
	private HashMap<Account, SocketChannel> AccountChannels = new HashMap<Account, SocketChannel>();

	// CTOR INSTANCE
	private static final ServerData instance = new ServerData();

	/**
	 * Gets the singleton instance object
	 * @return the instance of the server data
	 */
	public static ServerData getInstance() {
		return instance;
	}

	// ACCESSORS

	/**
	 * Gets an accounts current communication channel
	 * @param account Active account in the database
	 * @return the current channel linked to that account
	 */
	public SocketChannel getChannel(Account account) {
		return this.AccountChannels.get(account);
	}

	// MUTATORS

	/**
	 * Adds a mapping between the given account and channel
	 * @param account Account to pair
	 * @param channel Channel to pair
	 */
	public void addAccountChannelPair(Account account, SocketChannel channel) {
		this.AccountChannels.put(account, channel);
	}

}
