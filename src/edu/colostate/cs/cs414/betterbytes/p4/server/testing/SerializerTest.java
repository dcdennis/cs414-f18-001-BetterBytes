package edu.colostate.cs.cs414.betterbytes.p4.server.testing;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import edu.colostate.cs.cs414.betterbytes.p4.hnefatafl.game.Cell;
import edu.colostate.cs.cs414.betterbytes.p4.hnefatafl.game.Game;
import edu.colostate.cs.cs414.betterbytes.p4.hnefatafl.game.Piece;
import edu.colostate.cs.cs414.betterbytes.p4.server.utilities.Serializer;
import edu.colostate.cs.cs414.betterbytes.p4.server.wireforms.CreateInvitation;
import edu.colostate.cs.cs414.betterbytes.p4.server.wireforms.Message;
import edu.colostate.cs.cs414.betterbytes.p4.user.Account;
import edu.colostate.cs.cs414.betterbytes.p4.user.Player;

class SerializerTest {

	@Test
	void testMessage() {
		Message a = new CreateInvitation("TEST1","TEST2");
		byte[] aBytes = Serializer.serialize(a);
		Message b = (CreateInvitation)Serializer.deserializeMessage(aBytes);
		assertEquals(a,b);
	}
	
	@Test
	void testAccount() {
		Account a = new Account("username","password");
		byte[] aBytes = Serializer.serialize(a);
		Account b = Serializer.deserializeAccount(aBytes);
		assertEquals(a,b);
	}
	
	
	@Test
	void testGame() {
		String start = "TEST";
		Player atk = new Player(new Account("test1","test2"),"black");	
		Player def = new Player(new Account("test3","test4"),"white");	
		Game a = new Game(start,atk,def);
		byte[] aBytes = Serializer.serialize(a);
		Game b = Serializer.deserializeGame(aBytes);
		assertEquals(a,b);
	}
	
	@Test
	void testCell() {
		Cell a = new Cell("S",new Piece(false,"black"));
		byte[] aBytes = Serializer.serialize(a);
		Cell b = Serializer.deserializeCell(aBytes);
		assertEquals(a,b);
	}

}
