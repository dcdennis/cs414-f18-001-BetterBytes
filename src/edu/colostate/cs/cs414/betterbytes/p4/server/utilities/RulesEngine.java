package edu.colostate.cs.cs414.betterbytes.p4.server.utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.colostate.cs.cs414.betterbytes.p4.hnefatafl.game.Cell;
import edu.colostate.cs.cs414.betterbytes.p4.hnefatafl.game.Game;
import edu.colostate.cs.cs414.betterbytes.p4.hnefatafl.game.GameResult;
import edu.colostate.cs.cs414.betterbytes.p4.hnefatafl.game.Move;
import edu.colostate.cs.cs414.betterbytes.p4.server.wireforms.RecordsRequest;
import edu.colostate.cs.cs414.betterbytes.p4.user.Player;

/**
 * RulesEngine class. Handles turn by turn game logic.
 * Note: Some variables have the full import path, but this doesn't appear necessary since we already imported.
 * As to not break the code on the last day, I'm not going to change it -Hayden.
 * @version 1.0
 */
public class RulesEngine {
	private static RulesEngine instance = new RulesEngine();

	private RulesEngine() {
	}

	/**
	 * Gets the singleton instance object
	 * @return instance object
	 */
	public static RulesEngine getInstance() {
		return instance; 
	}

	/**
	 * Figure out if a given game has ended
	 * @param game Game object
	 * @return True if the game has ended, false otherwise
	 */
	public GameResult gameHasEnded(Game game) {

		// CASE: King has been captured
		boolean kingFound = false;
		edu.colostate.cs.cs414.betterbytes.p4.hnefatafl.game.Cell kingLoc = null;
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 11; j++) {
				edu.colostate.cs.cs414.betterbytes.p4.hnefatafl.game.Cell checkLoc = game.getCell(i, j);
				if (checkLoc.hasPiece() && checkLoc.getPiece().isKing()) {
					kingFound = true;
					kingLoc = checkLoc;
				}

			}
		}
		//No king on the board, so the Black player must have won
		if (!kingFound)
		{	
			System.out.println("1"); 
			
			return GameResult.BLACK;
		}
		if(kingFound)
			System.out.println(kingLoc.toString());
		
		// CASE: King has escaped
		if (game.getCell(0, 0).hasPiece() || game.getCell(0, 10).hasPiece() || game.getCell(10, 0).hasPiece()
				|| game.getCell(10, 10).hasPiece())
			return GameResult.WHITE;

		// CASE: King is on an edge, surrounded by black pieces, and there are no other
		// white pieces

//		if (kingLoc.getX() == 0) {
//			System.out.println("2"); 
//			if (game.getCell(1, kingLoc.getY()).hasPiece() 
//					&& !game.getCell(1, kingLoc.getY()).getPiece().isWhite()
//					&& game.getCell(0, kingLoc.getY() + 1).hasPiece()
//					&& !game.getCell(0, kingLoc.getY() + 1).getPiece().isWhite()
//					&& game.getCell(0, kingLoc.getY() - 1).hasPiece()
//					&& !game.getCell(0, kingLoc.getY() - 1 ).getPiece().isWhite() && this.kingAlone(game))
//				return GameResult.BLACK;
//		}
//		if (kingLoc.getX() == 10) {
//			System.out.println("3"); 
//			if (game.getCell(9, kingLoc.getY()).hasPiece() 
//					&& !game.getCell(9, kingLoc.getY()).getPiece().isWhite()
//					&& game.getCell(10, kingLoc.getY() + 1).hasPiece()
//					&& !game.getCell(10, kingLoc.getY() + 1).getPiece().isWhite()
//					&& game.getCell(10, kingLoc.getY() - 1).hasPiece()
//					&& !game.getCell(10, kingLoc.getY() - 1).getPiece().isWhite() && this.kingAlone(game))
//				return GameResult.BLACK;
//		}
//		if (kingLoc.getY() == 0) {
//			System.out.println("4"); 
//			if (game.getCell(kingLoc.getX(), 1).hasPiece() && !game.getCell(kingLoc.getX(), 1).getPiece().isWhite()
//					&& game.getCell(kingLoc.getX() + 1, 0).hasPiece()
//					&& !game.getCell(kingLoc.getX() + 1, 0).getPiece().isWhite()
//					&& game.getCell(kingLoc.getX() - 1, 0).hasPiece()
//					&& !game.getCell(kingLoc.getX() - 1, 0).getPiece().isWhite() && this.kingAlone(game))
//				return GameResult.BLACK;
//		}
//		if (kingLoc.getY() == 10) {
//			System.out.println("5"); 
//			if (game.getCell(kingLoc.getX(), 9).hasPiece() && !game.getCell(kingLoc.getX(), 9).getPiece().isWhite()
//					&& game.getCell(kingLoc.getX() + 1, 10).hasPiece()
//					&& !game.getCell(kingLoc.getX() + 1, 10).getPiece().isWhite()
//					&& game.getCell(kingLoc.getX() - 1, 10).hasPiece()
//					&& !game.getCell(kingLoc.getX() - 1, 10).getPiece().isWhite() && this.kingAlone(game))
//				return GameResult.BLACK;
//		}
		// CASE: Move pattern repetition
		ArrayList<Move> moveHistory = new ArrayList<Move>((Arrays.asList(game.getMoves())));
		if (moveHistory.size() > 12) {
			ArrayList<Move> movePattern = (ArrayList<Move>) moveHistory.subList(moveHistory.size() - 3, moveHistory.size());
			if (moveHistory.subList(moveHistory.size() - 7, moveHistory.size() - 4).equals(movePattern)
					&& moveHistory.subList(moveHistory.size() - 11, moveHistory.size() - 8).equals(movePattern))
				return GameResult.DRAW;
		}
		return GameResult.CONTINUE;
	}

	/**
	 * Check if there is a nonKing piece in the game.
	 * @param game Game object
	 * @return true if a nonKingPiece was found, false otherwise
	 */
	private boolean kingAlone(Game game) {
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 11; j++) {
				edu.colostate.cs.cs414.betterbytes.p4.hnefatafl.game.Cell checkLoc = game.getCell(i, j);
				if (checkLoc.hasPiece() && checkLoc.getPiece().getType() == "rook") {
					return true;
				}

			}
		}
		return false;
	}

	/**
	 * Process a capture between an old game state and a new game state if one happened. Assumes oldGame was
	 * a previous state of newGame.
	 * @param oldGame old game state as Game object
	 * @param newGame new game state as Game object
	 * @return updated game state
	 */
	public Game processCaptures(Game oldGame, Game newGame) {
		Game gameUpdate = newGame;
		Cell newLoc = null;
		Cell oldLoc = null;
		//Checks to see where the two states differ
		for(int x = 0; x < 11; x++)
		{
			for(int y = 0; y< 11; y++)
			{
				if(gameUpdate.getCell(x, y).hasPiece() && !oldGame.getCell(x, y).hasPiece())
				{
					newLoc = gameUpdate.getCell(x, y);
				}
				if(!gameUpdate.getCell(x, y).hasPiece() && oldGame.getCell(x, y).hasPiece())
				{
					oldLoc = gameUpdate.getCell(x, y);
				}
			}
		}
		System.out.println(oldLoc.getX() + " " + oldLoc.getY());
		System.out.println(newLoc.getX() + " " + newLoc.getY());
		//TODO check it works
		ArrayList<Move> moveHistory = new ArrayList<Move> (Arrays.asList(gameUpdate.getMoves()));		
		moveHistory.add(new Move(oldLoc.getX(),oldLoc.getY(),newLoc.getX(),newLoc.getY()));
		//check above
		if(newLoc.getY() <= 8)
		{
			Cell captureLoc = gameUpdate.getCell(newLoc.getX(), newLoc.getY()+1);
			Cell checkLoc = gameUpdate.getCell(newLoc.getX(), newLoc.getY()+2);
			if(checkLoc.hasPiece() && checkLoc.getPiece().isWhite() == newLoc.getPiece().isWhite() && captureLoc.hasPiece() && captureLoc.getPiece().isWhite() != newLoc.getPiece().isWhite())
				//captureLoc.setPiece(null);
				{
					captureLoc.removePiece();
				}
		}
		//check below
		if(newLoc.getY() >= 2)
		{
			Cell captureLoc = gameUpdate.getCell(newLoc.getX(), newLoc.getY()-1);
			Cell checkLoc = gameUpdate.getCell(newLoc.getX(), newLoc.getY()-2);
			if(checkLoc.hasPiece() && checkLoc.getPiece().isWhite() == newLoc.getPiece().isWhite() && captureLoc.hasPiece() && captureLoc.getPiece().isWhite() != newLoc.getPiece().isWhite())
				//captureLoc.setPiece(null);
			{
				captureLoc.removePiece();
			}
		}
		//check left
		if(newLoc.getX() <= 8)
		{
			Cell captureLoc = gameUpdate.getCell(newLoc.getX()+1, newLoc.getY());
			Cell checkLoc = gameUpdate.getCell(newLoc.getX()+2, newLoc.getY());
			if(checkLoc.hasPiece() && checkLoc.getPiece().isWhite() == newLoc.getPiece().isWhite() && captureLoc.hasPiece() && captureLoc.getPiece().isWhite() != newLoc.getPiece().isWhite())
				//captureLoc.setPiece(null);
			{
				captureLoc.removePiece();
			}
		}
		//check right
		if(newLoc.getX() >= 2)
		{
			Cell captureLoc = gameUpdate.getCell(newLoc.getX()-1, newLoc.getY());
			Cell checkLoc = gameUpdate.getCell(newLoc.getX()-2, newLoc.getY());


			if(checkLoc.hasPiece() && checkLoc.getPiece().isWhite() == newLoc.getPiece().isWhite() && captureLoc.hasPiece() && captureLoc.getPiece().isWhite() != newLoc.getPiece().isWhite())
				//captureLoc.setPiece(null);
			{
				captureLoc.removePiece();
			}
		}
					
		return gameUpdate;
	}
}