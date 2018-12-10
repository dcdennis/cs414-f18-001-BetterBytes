package edu.colostate.cs.cs414.betterbytes.p4.user.testing;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.colostate.cs.cs414.betterbytes.p4.user.Stats;

public class StatsTest {

	@Test
	public void testStatWins() {
		Stats stats = new Stats(10, 15);
		assertEquals(stats.getWins(), 10);
	}
	
	@Test
	public void testStatLosses() {
		Stats stats = new Stats(10, 15);
		assertEquals(stats.getLosses(), 15);
	}
	
	@Test
	public void testStatUpdate() {
		Stats stats = new Stats(10, 15);
		stats.addWin();
		assertEquals(stats.getWins(), 11);
	}
}
