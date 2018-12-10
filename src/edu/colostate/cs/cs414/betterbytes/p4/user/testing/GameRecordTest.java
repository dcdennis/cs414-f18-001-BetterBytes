package edu.colostate.cs.cs414.betterbytes.p4.user.testing;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.colostate.cs.cs414.betterbytes.p4.user.Account;
import edu.colostate.cs.cs414.betterbytes.p4.user.GameRecord;
import edu.colostate.cs.cs414.betterbytes.p4.user.Player;

public class GameRecordTest {

	@Test
	public void testGameRecordAttacker() {
		Account account = new Account("devin", "testPassword");
		Account account2 = new Account("john", "testPassword");
		Player attacker = new Player(account);
		Player defender = new Player(account2);
		GameRecord game_record = new GameRecord("00:00", "24:00", attacker, defender,attacker);
		assertEquals(game_record.getAttacker(), attacker);
	}
	
	@Test
	public void testGameRecordDefender() {
		Account account = new Account("devin", "testPassword");
		Account account2 = new Account("john", "testPassword");
		Player attacker = new Player(account);
		Player defender = new Player(account2);
		GameRecord game_record = new GameRecord("00:00", "24:00", attacker, defender,attacker);
		assertEquals(game_record.getDefender(), defender);
	}
	
	@Test
	public void testGameRecordWinner() {
		Account account = new Account("devin", "testPassword");
		Account account2 = new Account("john", "testPassword");
		Player attacker = new Player(account);
		Player defender = new Player(account2);
		GameRecord game_record = new GameRecord("00:00", "24:00", attacker, defender,attacker);
		assertEquals(game_record.getWinner(), attacker);
	}
	
	@Test
	public void testGameRecordStartTime() {
		Account account = new Account("devin", "testPassword");
		Account account2 = new Account("john", "testPassword");
		Player attacker = new Player(account);
		Player defender = new Player(account2);
		GameRecord game_record = new GameRecord("00:00", "24:00", attacker, defender,attacker);
		assertEquals(game_record.getStartTime(), "00:00");
	}
	
	@Test
	public void testGameRecordEndTime() {
		Account account = new Account("devin", "testPassword");
		Account account2 = new Account("john", "testPassword");
		Player attacker = new Player(account);
		Player defender = new Player(account2);
		GameRecord game_record = new GameRecord("00:00", "24:00", attacker, defender,attacker);
		assertEquals(game_record.getEndTime(), "24:00");
	}
}
