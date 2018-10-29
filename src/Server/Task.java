package Server;

import java.nio.channels.SelectionKey;
//import java.nio.channels.SocketChannel;

//This class needs to be safe for concurent access, it currently is not.

public class Task {
	//private byte[] bytes;
	//private final SocketChannel channel;
	private final SelectionKey key;
	//The really basic task, no networking 
	//public Task(byte[] bytes)
	//{
	//	this.bytes = bytes;
	//}
	//new version, takes a channel, does networking and such
	public Task(SelectionKey key)
	{
		//this.channel = channel;
		this.key = key;
	}
	//public byte[] getBytes(){return bytes;}
	//public SocketChannel getChannel(){return channel;}
	public SelectionKey getKey(){return key;}
}
