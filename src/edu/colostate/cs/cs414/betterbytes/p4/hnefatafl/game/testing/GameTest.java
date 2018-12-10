package edu.colostate.cs.cs414.betterbytes.p4.hnefatafl.game.testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.colostate.cs.cs414.betterbytes.p4.hnefatafl.game.Game;
import edu.colostate.cs.cs414.betterbytes.p4.hnefatafl.game.GameResult;
import edu.colostate.cs.cs414.betterbytes.p4.user.Account;
import edu.colostate.cs.cs414.betterbytes.p4.user.Player;

public class GameTest {
  
	
	@Test
	public void testGameBoard() {
		Game game = new Game("00:00AM", new Player(), new Player());
		assertEquals(game.getCells().length, 121);
	}
	
	@Test
	public void testGameResult() {
		Game game = new Game("00:00AM", new Player(), new Player());
		assertEquals(game.getResult(), GameResult.CONTINUE);
	}
	
	@Test
	public void testGameTurn() {
		Game game = new Game("00:00AM", new Player(), new Player(new Account("a", "b")));
		assertTrue(game.getTurn().equals(new Player(new Account("a", "b")) ));
	}
	
	@Test
	public void testGameTurnChange() {
		Game game = new Game("00:00AM", new Player(new Account("c", "d")), new Player(new Account("a", "b")));
		game.changeTurns();
		assertTrue(game.getTurn().equals(new Player(new Account("c", "d")) ));
	}
	
	@Test 
	public void testGameAttacker() {
		Game game = new Game("00:00AM", new Player(new Account("c", "d")), new Player(new Account("a", "b")));
		assertTrue(game.getAttacker().equals(new Player(new Account("c", "d"))));
	}
	
	@Test 
	public void testGameDefender() {
		Game game = new Game("00:00AM", new Player(new Account("c", "d")), new Player(new Account("a", "b")));
		assertTrue(game.getDefender().equals(new Player(new Account("a", "b"))));
	}
	
	
}
