package edu.colostate.cs.cs414.betterbytes.p4.server;

import java.nio.channels.SocketChannel;
import java.util.HashMap;

import edu.colostate.cs.cs414.betterbytes.p4.user.Account;

// This class allows access to an active account's socket channel
// The server can now write to a specified client
// TODO: when a client logins in we need to create an instance of their account from the db
//		 when the Account class has been serialized we can hook it to their SocketChannel
public class ServerData {

	// FIELDS
	private HashMap<Account, SocketChannel> AccountChannels = new HashMap<Account, SocketChannel>();

	// CTOR INSTANCE
	private static final ServerData instance = new ServerData();

	public static ServerData getInstance() {
		return instance;
	}

	// ACCESSORS
	public SocketChannel getChannel(Account account) {
		return this.AccountChannels.get(account);
	}

	// MUTATORS
	public void addAccountChannelPair(Account account, SocketChannel channel) {
		this.AccountChannels.put(account, channel);
	}

}
