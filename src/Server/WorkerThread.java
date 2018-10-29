package Server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

import WireForms.Message;
import WireForms.UserLogon;
import WireForms.UserRegistration;
import WireForms.UserRegistrationResponse;

public class WorkerThread extends Thread implements WireForms.Protocol  
{
	private volatile boolean running = true;
	private final boolean debug = true;
	private ThreadPoolManager manager;

	private int threadID;
	
	public WorkerThread(int threadID)
	{
		this.threadID = threadID;
		manager = ThreadPoolManager.getInstance();
	}
	
	public void run()
	{
		while(running)
		{
			if(debug)
				System.out.println("WORKER THREAD " + threadID + " Waiting for new task");
			
			//get the next task from the blocking queue
			Task newTask = manager.nextTask();
			
			//if(debug)
			//	System.out.println("Worker Thread " + threadID + " received new task");
			
			if(newTask != null)
			{
				if(debug)
					System.out.println("Worker Thread " + threadID + " received new task: " + newTask.toString());
				SocketChannel channel = (SocketChannel) newTask.getKey().channel();
				System.out.println("LIVE CHECK 0");
				ByteBuffer buffer = ByteBuffer.allocate(8000);
				System.out.println("LIVE CHECK 1");
				//int read = 0;
				try 
				{
					System.out.println("LIVE CHECK 1.1");
					//while (buffer.hasRemaining() && read != -1) 
						//read = channel.read(buffer);
					channel.read(buffer);
					System.out.println("LIVE CHECK 2");
					byte[] packet = buffer.array();
					if(debug)
						System.out.println("Worker Thread " + threadID + " received new message: " + packet.toString());
					String stringRep = new String(packet,"UTF8");
					if(debug)
						System.out.println("Worker Thread " + threadID + " received new message: " + stringRep);
					String messageCode = stringRep.substring(0,2);
					buffer.flip();
					buffer.clear();
					newTask.getKey().interestOps(SelectionKey.OP_WRITE);
					switch(messageCode)
					{
						/**
						 * This switch statement should be the only thing that has to get edited from here on out.
						 * When we want to add functionality, it should always either be adding a new case, if a new wireform is needed,
						 * or by altering code in the case of the message being responded to. 
						 */
						case(USER_REGISTRATION):
							UserRegistration registrationMessage = new UserRegistration(stringRep);
							//This is where calls to the database, rules engine, and other parts goes, before building the response
							Message outgoing = new UserRegistrationResponse(registrationMessage.getUsername(),registrationMessage.getPasswordHash());
							if(debug)
								System.out.println("Worker Thread " + threadID + " sending new message: " + outgoing.getStringRepresentation());
							buffer = ByteBuffer.wrap(outgoing.getStringRepresentation().getBytes());
							channel.write(buffer);
							if(debug)
								System.out.println("Worker Thread " + threadID + "sent message");
							break;
							
						case(USER_LOGON):
							@SuppressWarnings("unused") UserLogon logonMessage = new UserLogon(stringRep);
							break;
					}
					buffer.flip();
					buffer.clear();
					
				} 
				catch (IOException e) 
				{
					newTask.getKey().cancel();
					try 
					{
						channel.close();
					} 
					catch (IOException e1) 
					{
						e1.printStackTrace();
					}
				}

				newTask.getKey().interestOps(SelectionKey.OP_READ);
			}
		}
		System.out.println("THREAD " + threadID + " terminating");
	}
	public void terminate()
	{
		running = false;
	}	
}
