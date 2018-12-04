package edu.colostate.cs.cs414.betterbytes.p4.client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import edu.colostate.cs.cs414.betterbytes.p4.server.utilities.Serializer;
import edu.colostate.cs.cs414.betterbytes.p4.server.wireforms.Message;

public class ClientConnection extends Thread {
	private String serverHost;
	private int serverPort;
	boolean running;
	private SocketChannel channel;
	private Selector selector;
	private SelectionKey serverKey;

	private static final ClientConnection instance = new ClientConnection();

	public static ClientConnection getInstance() {
		return instance;
	}

	private ClientConnection() {
		running = true;
		serverKey = null;
	}

	public void setUp(String serverHost, int serverPort) {
		this.serverHost = serverHost;
		this.serverPort = serverPort;
	}

	public void run() {
		try {
			// Configure Channel and selector and initiate connection
			channel = SocketChannel.open();
			channel.configureBlocking(false);
			channel.connect(new InetSocketAddress(serverHost, serverPort));
			selector = Selector.open();
			channel.register(selector, SelectionKey.OP_CONNECT);

			// Start the statistics collector thread

			// Main Loop
			while (running) {
				// Check if channels are available for operations
				int readyChannels = selector.select();
				if (readyChannels == 0)
					continue;
				// generate keyset of available channels. this should be redundant as theirs
				// only one, but if it works.
				Set<SelectionKey> selectedKeys = selector.selectedKeys();
				Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

				// Loop through all (one) active key
				while (keyIterator.hasNext()) {
					// get the key and remove it from iterator set
					SelectionKey key = keyIterator.next();

					// Complete Connection
					if (key.isConnectable()) {
						connect(key);
					}
					/*
					 * if(key.isReadable()){ read(key); }
					 */
					keyIterator.remove();
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void connect(SelectionKey key) {
		try {
			// Complete Connection
			SocketChannel channel = (SocketChannel) key.channel();
			channel.finishConnect();
			key.interestOps(SelectionKey.OP_WRITE);
			serverKey = key;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private byte[] read(SelectionKey key) {
		byte[] message = null;
		SocketChannel channel = (SocketChannel) key.channel();
		try {
			// Create new buffer and read hash into it.
			ByteBuffer buffer = ByteBuffer.allocate(8000000);

			if (buffer.hasRemaining())
				channel.read(buffer);
			buffer.flip();
			message = buffer.array();
			buffer.clear();
		} catch (IOException e) {
			// Cancel the key and close the socket channel
			key.cancel();
			running = false;
		}
		// Set key to ready to writes
		key.interestOps(SelectionKey.OP_WRITE);
		// System.out.println("Recieved response22: " + new String(message));
		return message;
	}

	@SuppressWarnings("static-access")
	public synchronized Message send(Message message) {
		Message response = null;
		if (serverKey != null) {
			try {
				// Wrap bytes of the string representation of the message in buffer and send
				SocketChannel channel = (SocketChannel) serverKey.channel();
				ByteBuffer buffer = ByteBuffer.wrap(Serializer.serialize(message));
				System.out.println("Sending Message: " + message.toString());
				channel.write(buffer);
				buffer.flip();
				buffer.clear();
			} catch (IOException e) {
			}

			serverKey.interestOps(SelectionKey.OP_READ);

			while (response == null) {
				try {
					this.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace(); 
				}
 
				if (serverKey.isReadable()) {
					byte[] responseBytes = read(serverKey);
					response = Serializer.deserializeMessage(responseBytes);
					System.out.println("Recieved response: ");// + response.toString());
				}
			}
		}
		return response;
	}

	public void setUp(InetAddress localHost, int serverPort2) {
		// TODO Auto-generated method stub

	}

}
