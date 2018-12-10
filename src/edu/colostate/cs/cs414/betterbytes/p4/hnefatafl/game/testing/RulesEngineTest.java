package edu.colostate.cs.cs414.betterbytes.p4.hnefatafl.game.testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import edu.colostate.cs.cs414.betterbytes.p4.hnefatafl.game.Game;
import edu.colostate.cs.cs414.betterbytes.p4.hnefatafl.game.GameResult;
import edu.colostate.cs.cs414.betterbytes.p4.hnefatafl.game.Move;
import edu.colostate.cs.cs414.betterbytes.p4.hnefatafl.game.Piece;
import edu.colostate.cs.cs414.betterbytes.p4.server.utilities.RulesEngine;
import edu.colostate.cs.cs414.betterbytes.p4.user.Account;
import edu.colostate.cs.cs414.betterbytes.p4.user.Player;

public class RulesEngineTest {

	@Test
	public void testProcessCaptures() {
		Account account = new Account("devin", "testPassword");
		Account account2 = new Account("john", "testPassword");
		Player attacker = new Player(account);
		Player defender = new Player(account2);
		Game oldGame = new Game("00:00AM", attacker, defender);
		Game newGame = new Game("00:00AM", attacker, defender);
		RulesEngine engine = RulesEngine.getInstance();
		
		oldGame.getCell(6, 6).setPiece(null);
		oldGame.getCell(9, 6).setPiece(new Piece(true, "white"));
		newGame.getCell(10, 7).setPiece(null);
		newGame.getCell(9,7).setPiece(new Piece(true, "black"));
		
		Game processedGame = engine.processCaptures(oldGame, newGame);
		assertNull(processedGame.getCell(9,6).getPiece());
		
		
	}
	
	@Test
	public void testGameHasEnded_noKing() {
		Account account = new Account("devin", "testPassword");
		Account account2 = new Account("john", "testPassword");
		Player attacker = new Player(account);
		Player defender = new Player(account2);
		Game game = new Game("00:00AM", attacker, defender);
		RulesEngine engine = RulesEngine.getInstance();
		
		game.getCell(5, 5).setPiece(null);
		assertEquals(engine.gameHasEnded(game), GameResult.BLACK);	
	}
	
	@Test
	public void testGameHasEnded_kingEscaped() {
		Account account = new Account("devin", "testPassword");
		Account account2 = new Account("john", "testPassword");
		Player attacker = new Player(account);
		Player defender = new Player(account2);
		Game game = new Game("00:00AM", attacker, defender);
		RulesEngine engine = RulesEngine.getInstance();
		
		game.getCell(5, 5).setPiece(null);
		game.getCell(0, 0).setPiece(new Piece(false, "white"));
		assertEquals(engine.gameHasEnded(game), GameResult.WHITE);	
	}
	
	
	@Test
	public void testGameHasEnded_Continue() {
		Account account = new Account("devin", "testPassword");
		Account account2 = new Account("john", "testPassword");
		Player attacker = new Player(account);
		Player defender = new Player(account2);
		Game game = new Game("00:00AM", attacker, defender);
		RulesEngine engine = RulesEngine.getInstance();
		
		assertEquals(engine.gameHasEnded(game), GameResult.CONTINUE);	
	}
	
}
