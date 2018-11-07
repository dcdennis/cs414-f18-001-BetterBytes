package edu.colostate.cs.cs414.betterbytes.p3.utilities;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import edu.colostate.cs.cs414.betterbytes.p3.wireforms.Message;

public abstract class MessageSerializer {
	public static byte[] serializeMessage(Message message)
	{
		byte[] res = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutput out = null;
		try
		{
			out = new ObjectOutputStream(bos);   
			out.writeObject(message);
			out.flush();
			res = bos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally 
		{
		  try 
		  {
		    bos.close();
		  } 
		  catch (IOException ex) {}
		}
		return res;
	}
	public static Message deserializeMessage(byte[] bytes)
	{
		ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
		ObjectInput in = null;
		Object object = null;
		Message message = null;
		try 
		{
			  in = new ObjectInputStream(bis);
			  object = in.readObject();
			  message = (Message) object;
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}finally {
			  try {
				    if (in != null) {
				      in.close();
				    }
				  } catch (IOException ex) {
				    // ignore close exception
				  }
				}
		return message;
	}
}
