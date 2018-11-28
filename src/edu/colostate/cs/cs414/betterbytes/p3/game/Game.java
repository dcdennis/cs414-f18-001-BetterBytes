package edu.colostate.cs.cs414.betterbytes.p3.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
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

	public Cell [] cells = new Cell [121];

	// CONSTRUCTOR
	public Game() {

	}

	public Game(String startTime, Player attacker, Player defender) {
		this.turn = attacker;
		this.attacker = attacker;
		this.defender = defender;
		this.startTime = startTime;
		//this.moves = new ArrayList<Move>();
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attacker == null) ? 0 : attacker.hashCode());
		result = prime * result + Arrays.hashCode(cells);
		result = prime * result + ((defender == null) ? 0 : defender.hashCode());
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + ((moves == null) ? 0 : moves.hashCode());
		result = prime * result + ((this.result == null) ? 0 : this.result.hashCode());
		result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
		result = prime * result + ((turn == null) ? 0 : turn.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Game other = (Game) obj;
		if (attacker == null) {
			if (other.attacker != null)
				return false;
		} else if (!attacker.equals(other.attacker))
			return false;
		if (!Arrays.equals(cells, other.cells))
			return false;
		if (defender == null) {
			if (other.defender != null)
				return false;
		} else if (!defender.equals(other.defender))
			return false;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (moves == null) {
			if (other.moves != null)
				return false;
		} else if (!moves.equals(other.moves))
			return false;
		if (result != other.result)
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		if (turn == null) {
			if (other.turn != null)
				return false;
		} else if (!turn.equals(other.turn))
			return false;
		return true;
	}



	
}
