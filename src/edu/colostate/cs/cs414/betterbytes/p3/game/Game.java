package edu.colostate.cs.cs414.betterbytes.p3.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import edu.colostate.cs.cs414.betterbytes.p3.client.ClientConnection;
import edu.colostate.cs.cs414.betterbytes.p3.user.Player;
import edu.colostate.cs.cs414.betterbytes.p3.wireforms.Message;
import edu.colostate.cs.cs414.betterbytes.p3.wireforms.Protocol;
import edu.colostate.cs.cs414.betterbytes.p3.wireforms.SubmitMove;
import edu.colostate.cs.cs414.betterbytes.p3.wireforms.SubmitMoveResponse;

public class Game implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// GLOBALS
	private String startTime;
	private String endTime;
	private Player turn; // Player that needs to make a move
	private Player attacker;
	private Player defender;
	private ArrayList<Move> moves;
	private GameResult result;

//	public ArrayList<Cell> cells = new ArrayList<Cell>(); //These need to be public
//	public ArrayList<Cell> backup = new ArrayList<Cell>(); //These need to be public

	public ArrayList<Cell> cells = new ArrayList<Cell>();

	// CONSTRUCTOR
	public Game() {

	}

	public Game(String startTime, Player attacker, Player defender) {
		this.turn = attacker;
		this.attacker = attacker;
		this.defender = defender;
		this.startTime = startTime;
		this.moves = new ArrayList<Move>();
		setResult(GameResult.CONTINUE);
	}

	// SETTERS
	public void changeTurns() {
		if (turn == attacker) {
			this.turn = defender;
		} else {
			this.turn = attacker;
		}
	}

	public void setEndTime(String end) {
		this.endTime = end;
	}

	public boolean sendGameToServer() {
		SubmitMove move = new SubmitMove(this);
		Message reply = ClientConnection.getInstance().send(move);
		if (reply != null) {
			if (reply.getType().equals(Protocol.SUBMIT_MOVE_RESPONSE)) {
				SubmitMoveResponse re = (SubmitMoveResponse) reply;
				return re.getStatus();
			}
		}
		return false;
	}

	// GETTERS
	public String getStartTime() {
		return this.startTime;
	}

	public String getEndTime() {
		return this.endTime;
	}

	public Player getTurn() {
		return this.turn;
	}

	public Player getAttacker() {
		return this.attacker;
	}

	public Player getDefender() {
		return this.defender;
	}

//	public void makeMove(Move nextMove) {
//		edu.colostate.cs.cs414.betterbytes.p3.ui.Piece toMove = this.gameframe
//				.getCell(nextMove.getStartX(), nextMove.getStartY()).getPiece();
//		this.gameframe.movePiece(toMove, this.gameframe.getCell(nextMove.getStartX(), nextMove.getStartY()),
//				this.gameframe.getCell(nextMove.getEndX(), nextMove.getEndY()));
//
//	}

	public List<Move> getMoves() {
		return moves;
	}
	public Cell getCell(int x, int y) {
		for (Cell c : cells)
			if (c.getX() == x && c.getY() == y)
				return c;
		return null;
	}

	public GameResult getResult() {
		return result;
	}

	public void setResult(GameResult result) {
		this.result = result;
	}
}
