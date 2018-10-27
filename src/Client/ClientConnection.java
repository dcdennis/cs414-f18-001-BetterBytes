package Client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import WireForms.Message;



public class ClientConnection extends Thread
{
	private String serverHost;
	private int serverPort;
	boolean running;
	private SocketChannel channel;
	private Selector selector;
	private SelectionKey serverKey;
	
	private static final ClientConnection instance = new ClientConnection();

	public static ClientConnection getInstance(){return instance;}
	
	private ClientConnection()
	{
		running = true;
		serverKey = null;
	}
	
	public void setUp(String serverHost, int serverPort)
	{
		this.serverHost = serverHost;
		this.serverPort = serverPort;
	}
	
	public void run()
	{
		try
		{
		//Configure Channel and selector and initiate connection
		channel = SocketChannel.open();		 
        channel.configureBlocking(false); 
        channel.connect(new InetSocketAddress(serverHost, serverPort));
        selector = Selector.open();
        channel.register(selector, SelectionKey.OP_CONNECT);
		
        
		//Start the statistics collector thread
			
		//Main Loop
		while(running)
		{		
			//Check if channels are available for operations
			int readyChannels = selector.select();
			if(readyChannels == 0) continue;
			//generate keyset of available channels. this should be redundant as theirs only one, but if it works.
			Set<SelectionKey> selectedKeys = selector.selectedKeys();
			Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
	
			//Loop through all (one) active key
			while(keyIterator.hasNext()) 
			{
				  	//get the key and remove it from iterator set
				  	SelectionKey key = keyIterator.next();
				  	keyIterator.remove();
				  	
				  	//Complete Connection
					if(key.isConnectable())
					{
						connect(key);
					}
			  }			
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	private void connect(SelectionKey key) 
	{
		try 
		{
			//Complete Connection
			SocketChannel channel = (SocketChannel) key.channel();
			channel.finishConnect();
			key.interestOps(SelectionKey.OP_WRITE); 
			serverKey = key;
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	private byte[] read(SelectionKey key)
	{
		byte[] message = null;
		SocketChannel channel = (SocketChannel) key.channel();
		try
		{
			//Create new buffer and read hash into it.
			ByteBuffer buffer = ByteBuffer.allocate(8000);
			
			if(buffer.hasRemaining())
				channel.read(buffer);	
			buffer.flip();
			message = buffer.array();
			buffer.clear();
		}
		catch (IOException e)
		{
			// Cancel the key and close the socket channel 
			key.cancel();
			running = false;
		}
		//Set key to ready to write
		key.interestOps(SelectionKey.OP_WRITE);
		return message;
	}
	
	public synchronized byte[] send(Message message)
	{
		byte[] response = null;
		if(serverKey != null)
		{
			try
			{
				//Wrap bytes of the string representation of the message in buffer and send
				SocketChannel channel = (SocketChannel) serverKey.channel();
				ByteBuffer buffer = ByteBuffer.wrap(message.getStringRepresentation().getBytes());
				System.out.println("Sending Message: " + message.getStringRepresentation());
				channel.write(buffer);
				buffer.flip();
				buffer.clear();
			}
			catch (IOException e){}
		
			serverKey.interestOps(SelectionKey.OP_READ);
		
			//wait for response
			while(response == null)
			{
				if(serverKey.isReadable())
				{
					response = read(serverKey);
					System.out.println("Recieved response: " + new String(response));
				}
			}
		}
		return response;
	}

	public void setUp(InetAddress localHost, int serverPort2) {
		// TODO Auto-generated method stub
		
	}
}