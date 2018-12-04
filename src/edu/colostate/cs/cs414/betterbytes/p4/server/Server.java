package edu.colostate.cs.cs414.betterbytes.p4.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class Server {

	volatile boolean running;
	int portNum;
	ThreadPoolManager manager = ThreadPoolManager.getInstance();

	public Server(int portNum, int poolSize) {
		this.portNum = portNum; 
		manager.initialize(poolSize);
		running = true;
	}

	public void serve() throws IOException {

		// Create and configure many things
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.configureBlocking(false);
		serverSocketChannel.socket().bind(new InetSocketAddress(portNum));
		serverSocketChannel.register(manager.selector(), SelectionKey.OP_ACCEPT);

		// main loops
		while (running) {

			try {
				// Wait for an event one of the registered channels
				manager.selector().selectNow();
				// Iterate over the set of keys for which events are available
				Iterator<SelectionKey> selectedKeys = manager.selector().selectedKeys().iterator();
				while (selectedKeys.hasNext()) {
					SelectionKey key = selectedKeys.next();
					selectedKeys.remove();

					if (!key.isValid()) {
						continue;
					}
					// Check what event is available and deal with it
					if (key.isAcceptable()) {
						System.out.println("Accepting: " + key.toString());
						accept(key);
					}
					if (key.isReadable()) {

						System.out.println("Readable key: " + key);
						// 0 out interest ops to prevent double reading
						key.interestOps(0);
						// add as new task to job queue
						manager.addTask(key);
					}
				}
			} catch (Exception e) {
				e.printStackTrace(); 
			}
		}
	}

	private void accept(SelectionKey key) throws IOException {
		// For an accept to be pending the channel must be a server socket channel.
		ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();

		// Accept the connection and make it non-blocking
		SocketChannel client = serverSocketChannel.accept();
		client.configureBlocking(false);

		// Register the new SocketChannel with selector
		client.register(manager.selector(), SelectionKey.OP_READ);
	}

	public static void main(String args[]) {
		if (args.length != 2)
			System.out.println("Usage: Server [Port] [Thread Pool Size]");
		Server server = new Server(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
		try {
			server.serve();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
