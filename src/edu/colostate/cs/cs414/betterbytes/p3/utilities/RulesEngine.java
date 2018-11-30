package edu.colostate.cs.cs414.betterbytes.p3.utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.colostate.cs.cs414.betterbytes.p3.game.Cell;
import edu.colostate.cs.cs414.betterbytes.p3.game.Game;
import edu.colostate.cs.cs414.betterbytes.p3.game.GameResult;
import edu.colostate.cs.cs414.betterbytes.p3.game.Move;

public class RulesEngine {
	private static RulesEngine instance = new RulesEngine();

	private RulesEngine() {
	}

	public static RulesEngine getInstance() {
		return instance; 
	}

	public GameResult gameHasEnded(Game game) {

		// CASE: King has been captured
		boolean kingFound = false;
		edu.colostate.cs.cs414.betterbytes.p3.game.Cell kingLoc = null;
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 11; j++) {
				edu.colostate.cs.cs414.betterbytes.p3.game.Cell checkLoc = game.getCell(i, j);
				if (checkLoc.hasPiece() && checkLoc.getPiece().getType() == "king") {
					kingFound = true;
					kingLoc = checkLoc;
				}

			}
		}
		if (!kingFound)
			return GameResult.BLACK;

		// CASE: King has escaped
		if (game.getCell(0, 0).hasPiece() || game.getCell(0, 10).hasPiece() || game.getCell(10, 0).hasPiece()
				|| game.getCell(10, 10).hasPiece())
			return GameResult.WHITE;

		// CASE: King is on an edge, surrounded by black pieces, and there are no other
		// white pieces

		if (kingLoc.getX() == 0) {
			if (game.getCell(1, kingLoc.getY()).hasPiece() && !game.getCell(1, kingLoc.getY()).getPiece().isWhite()
					&& game.getCell(0, kingLoc.getY() + 1).hasPiece()
					&& !game.getCell(1, kingLoc.getY()).getPiece().isWhite()
					&& game.getCell(0, kingLoc.getY() - 1).hasPiece()
					&& !game.getCell(1, kingLoc.getY()).getPiece().isWhite() && this.kingAlone(game))
				return GameResult.BLACK;
		}
		if (kingLoc.getX() == 10) {
			if (game.getCell(9, kingLoc.getY()).hasPiece() && !game.getCell(1, kingLoc.getY()).getPiece().isWhite()
					&& game.getCell(10, kingLoc.getY() + 1).hasPiece()
					&& !game.getCell(1, kingLoc.getY()).getPiece().isWhite()
					&& game.getCell(10, kingLoc.getY() - 1).hasPiece()
					&& !game.getCell(1, kingLoc.getY()).getPiece().isWhite() && this.kingAlone(game))
				return GameResult.BLACK;
		}
		if (kingLoc.getY() == 0) {
			if (game.getCell(kingLoc.getX(), 1).hasPiece() && !game.getCell(kingLoc.getX(), 1).getPiece().isWhite()
					&& game.getCell(kingLoc.getX() + 1, 0).hasPiece()
					&& !game.getCell(kingLoc.getX() + 1, 0).getPiece().isWhite()
					&& game.getCell(kingLoc.getX() - 1, 0).hasPiece()
					&& !game.getCell(kingLoc.getX() - 1, 0).getPiece().isWhite() && this.kingAlone(game))
				return GameResult.BLACK;
		}
		if (kingLoc.getY() == 10) {
			if (game.getCell(kingLoc.getX(), 9).hasPiece() && !game.getCell(kingLoc.getX(), 9).getPiece().isWhite()
					&& game.getCell(kingLoc.getX() + 1, 10).hasPiece()
					&& !game.getCell(kingLoc.getX() + 1, 10).getPiece().isWhite()
					&& game.getCell(kingLoc.getX() - 1, 10).hasPiece()
					&& !game.getCell(kingLoc.getX() - 1, 10).getPiece().isWhite() && this.kingAlone(game))
				return GameResult.BLACK;
		}
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

	private boolean kingAlone(Game game) {
		boolean nonKingFound = false;
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 11; j++) {
				edu.colostate.cs.cs414.betterbytes.p3.game.Cell checkLoc = game.getCell(i, j);
				if (checkLoc.hasPiece() && checkLoc.getPiece().getType() == "rook") {
					nonKingFound = true;
				}

			}
		}
		return nonKingFound;
	}

	public Game processCaptures(Game oldGame, Game gameUpdate) {
		Cell newLoc = null;
		Cell oldLoc = null;
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
		//TODO check it works
		ArrayList<Move> moveHistory = (ArrayList<Move>) (Arrays.asList(gameUpdate.getMoves()));		
		moveHistory.add(new Move(oldLoc.getX(),oldLoc.getY(),newLoc.getX(),newLoc.getY())); 
		
		//check above
		if(newLoc.getY() <= 8)
		{
			Cell captureLoc = gameUpdate.getCell(newLoc.getX(), newLoc.getY()+1);
			Cell checkLoc = gameUpdate.getCell(newLoc.getX(), newLoc.getY()+2);		
			if(checkLoc.hasPiece() && checkLoc.getPiece().isWhite() == newLoc.getPiece().isWhite() && captureLoc.hasPiece() && captureLoc.getPiece().isWhite() != newLoc.getPiece().isWhite())
				gameUpdate.getCell(newLoc.getX(), newLoc.getY()+1).setPiece(null);
		}
		//check below
		if(newLoc.getY() >= 2)
		{
			Cell captureLoc = gameUpdate.getCell(newLoc.getX(), newLoc.getY()-1);
			Cell checkLoc = gameUpdate.getCell(newLoc.getX(), newLoc.getY()-2);
			if(checkLoc.hasPiece() && checkLoc.getPiece().isWhite() == newLoc.getPiece().isWhite() && captureLoc.hasPiece() && captureLoc.getPiece().isWhite() != newLoc.getPiece().isWhite())
				captureLoc.setPiece(null);
		}
		//check left
		if(newLoc.getX() <= 8)
		{
			Cell captureLoc = gameUpdate.getCell(newLoc.getX()+1, newLoc.getY());
			Cell checkLoc = gameUpdate.getCell(newLoc.getX()+2, newLoc.getY());
			if(checkLoc.hasPiece() && checkLoc.getPiece().isWhite() == newLoc.getPiece().isWhite() && captureLoc.hasPiece() && captureLoc.getPiece().isWhite() != newLoc.getPiece().isWhite())
				captureLoc.setPiece(null);
		}
		//check right
		if(newLoc.getX() >= 2)
		{
			Cell captureLoc = gameUpdate.getCell(newLoc.getX()-1, newLoc.getY());
			Cell checkLoc = gameUpdate.getCell(newLoc.getX()-2, newLoc.getY());
			if(checkLoc.hasPiece() && checkLoc.getPiece().isWhite() == newLoc.getPiece().isWhite() && captureLoc.hasPiece() && captureLoc.getPiece().isWhite() != newLoc.getPiece().isWhite())
				captureLoc.setPiece(null);
		}
					
		return gameUpdate;
	}
}
