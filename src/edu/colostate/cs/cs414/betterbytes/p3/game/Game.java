package edu.colostate.cs.cs414.betterbytes.p3.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import edu.colostate.cs.cs414.betterbytes.p3.client.ClientConnection;
import edu.colostate.cs.cs414.betterbytes.p3.ui.GameFrame;
import edu.colostate.cs.cs414.betterbytes.p3.user.Player;
import edu.colostate.cs.cs414.betterbytes.p3.wireforms.Message;
import edu.colostate.cs.cs414.betterbytes.p3.wireforms.Protocol;
import edu.colostate.cs.cs414.betterbytes.p3.wireforms.SubmitMove;
import edu.colostate.cs.cs414.betterbytes.p3.wireforms.SubmitMoveResponse;

public class Game implements Serializable{

	// GLOBALS
	private String startTime;
	private String endTime;
	private Player turn; // Player that needs to make a move
	private Player attacker;
	private Player defender;
	private ArrayList<Move> moves;
	private GameFrame gameframe = null;
	private GameResult result;

	// CONSTRUCTOR
	public Game() {
		
	}

	public Game(String startTime, Player attacker, Player defender) {
		this.turn = attacker;
		this.attacker = attacker;
		this.defender = defender;
		this.startTime = startTime;
		this.moves = new ArrayList<Move>();
		gameframe = new GameFrame();
		gameframe.setGame(this);
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

	public void makeMove(Move nextMove) {
		edu.colostate.cs.cs414.betterbytes.p3.ui.Piece toMove = this.gameframe
				.getCell(nextMove.getStartX(), nextMove.getStartY()).getPiece();
		this.gameframe.movePiece(toMove, this.gameframe.getCell(nextMove.getStartX(), nextMove.getStartY()),
				this.gameframe.getCell(nextMove.getEndX(), nextMove.getEndY()));

	}

	public List<Move> getMoves() {
		return moves;
	}

	public edu.colostate.cs.cs414.betterbytes.p3.ui.Cell getCell(int x, int y) {
		return this.gameframe.getCell(x, y);
	}

	public GameResult getResult() {
		return result;
	}

	public void setResult(GameResult result) {
		this.result = result;
	}
	
}
