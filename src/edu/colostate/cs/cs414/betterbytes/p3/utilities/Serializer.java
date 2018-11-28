package edu.colostate.cs.cs414.betterbytes.p3.utilities;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import edu.colostate.cs.cs414.betterbytes.p3.game.Cell;
import edu.colostate.cs.cs414.betterbytes.p3.game.Game;
import edu.colostate.cs.cs414.betterbytes.p3.user.Account;
import edu.colostate.cs.cs414.betterbytes.p3.wireforms.Message;

public abstract class Serializer {
	public static byte[] serialize(Object o) {
		byte[] res = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutput out = null;
		try {
			out = new ObjectOutputStream(bos);
			out.writeObject(o);
			out.flush();
			res = bos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bos.close();
			} catch (IOException ex) {
			}
		}
		// System.out.println("SERIALIZE(): '" + new String(res) + "'");
		return res;
	}

	public static Message deserializeMessage(byte[] bytes) {
		ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
		ObjectInput in = null;
		Object object = null;
		Message message = null;
		
		try {
			in = new ObjectInputStream(bis);
			object = in.readObject();
			message = (Message) object;
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		} finally {
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

	public static Account deserializeAccount(byte[] bytes) {
		// System.out.println("DESERIALIZE(): '" + new String(bytes) + "'");

		ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
		ObjectInput in = null;
		Object object = null;
		Account account = null;
		try {
			in = new ObjectInputStream(bis);
			object = in.readObject();
			account = (Account) object;
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				// ignore close exception
			}
		}
		return account;
	}
	
	public static Cell deserializeCell(byte[] bytes) {
		// System.out.println("DESERIALIZE(): '" + new String(bytes) + "'");

		ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
		ObjectInput in = null;
		Object object  = null;
		Cell cell      = null;
		try {
			in = new ObjectInputStream(bis);
			object = in.readObject();
			cell = (Cell) object;
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				// ignore close exception
			}
		}
		return cell;
	}

	public static Game deserializeGame(byte[] bytes) {
		ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
		ObjectInput in = null;
		Object object = null;
		Game game = null;
		try {
			in = new ObjectInputStream(bis);
			
			object = in.readObject();
			game = (Game) object;
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				// ignore close exception
			}
		}
		return game;
	}
}
