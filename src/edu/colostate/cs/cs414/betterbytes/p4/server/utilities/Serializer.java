package edu.colostate.cs.cs414.betterbytes.p4.server.utilities;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import edu.colostate.cs.cs414.betterbytes.p4.hnefatafl.game.Cell;
import edu.colostate.cs.cs414.betterbytes.p4.hnefatafl.game.Game;
import edu.colostate.cs.cs414.betterbytes.p4.server.wireforms.Message;
import edu.colostate.cs.cs414.betterbytes.p4.user.Account;

/**
 * Serializer class. Serializes objects to byte arrays and deserialize byte arrays into either a Message,
 * an Account, a Cell, or a Game object.
 */
public abstract class Serializer {

	/**
	 * Serialize an Object into a byte array
	 * @param o Object to serialize
	 * @return Serialized byte array
	 */
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

	/**
	 * Deserialize a byte array into a Message object. Don't expect anything good using a non-message.
	 * @param bytes Serialized byte array
	 * @return Deserialized message object
	 */
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

	/**
	 * Deserialize a byte array into an Account object
	 * @param bytes Serialized byte array
	 * @return Deserialized Account object
	 */
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

	/**
	 * Deserialize a byte array into a Cell object
	 * @param bytes Serialized byte array
	 * @return Deserialized Cell object
	 */
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

	/**
	 * Deserialize a byte array into a Game object
	 * @param bytes Serialized byte array
	 * @return Deserialized Game object
	 */
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
