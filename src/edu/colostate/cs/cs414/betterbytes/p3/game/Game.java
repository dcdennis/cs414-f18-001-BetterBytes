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

/**
 * Represents the entire game of taffle
 * @version 1.0
 */
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
	private Move  [] moves;
	private GameResult result;	
//	public ArrayList<Cell> cells = new ArrayList<Cell>(); //These need to be public
//	public ArrayList<Cell> backup = new ArrayList<Cell>(); //These need to be public

	public Cell [] cells = new Cell [121];

	// CONSTRUCTOR
	public Game() {

	}

	/**
	 * Construct a new game with a initial time, one attacker player, and a defender player.
	 * Sets up the board to the base state and places the correct pieces on the cells.
	 * @param startTime time the match would start at
	 * @param attacker attacking player
	 * @param defender defending player
	 */
	public Game(String startTime, Player attacker, Player defender) {
		this.turn = attacker;
		this.attacker = attacker;
		this.defender = defender;
		this.startTime = startTime;
		this.moves = new Move [0];
		setResult(GameResult.CONTINUE);
		
		int counter = 0;
		for(int i = 0; i < 11; i++)
		{
			for(int j = 0; j < 11; j++)
			{
				if((i == 0 || i==10) && (j == 0 || j == 10))				
					cells[counter] = new Cell(i,j,"C",null);				
				else if(i == 5 && j == 5)
					cells[counter] = new Cell(i,j,"T",null);
				else
					cells[counter] = new Cell(i,j,"S",null);
				counter++;
			}
		}		
		this.getCell(3,0).setPiece(new Piece(true,"black"));
		this.getCell(4,0).setPiece(new Piece(true,"black"));
		this.getCell(5,0).setPiece(new Piece(true,"black"));
		this.getCell(6,0).setPiece(new Piece(true,"black"));
		this.getCell(7,0).setPiece(new Piece(true,"black"));
		this.getCell(5,1).setPiece(new Piece(true,"black"));
		
		this.getCell(0,3).setPiece(new Piece(true,"black"));
		this.getCell(0,4).setPiece(new Piece(true,"black"));
		this.getCell(0,5).setPiece(new Piece(true,"black"));
		this.getCell(0,6).setPiece(new Piece(true,"black"));
		this.getCell(0,7).setPiece(new Piece(true,"black"));
		this.getCell(1,5).setPiece(new Piece(true,"black"));
		
		this.getCell(3,10).setPiece(new Piece(true,"black"));
		this.getCell(4,10).setPiece(new Piece(true,"black"));
		this.getCell(5,10).setPiece(new Piece(true,"black"));
		this.getCell(6,10).setPiece(new Piece(true,"black"));
		this.getCell(7,10).setPiece(new Piece(true,"black"));
		this.getCell(5,9).setPiece(new Piece(true,"black"));
		
		this.getCell(10,3).setPiece(new Piece(true,"black"));
		this.getCell(10,4).setPiece(new Piece(true,"black"));
		this.getCell(10,5).setPiece(new Piece(true,"black"));
		this.getCell(10,6).setPiece(new Piece(true,"black"));
		this.getCell(10,7).setPiece(new Piece(true,"black"));
		this.getCell(9,5).setPiece(new Piece(true,"black"));
		
		this.getCell(5,7).setPiece(new Piece(true,"white"));
		
		this.getCell(4,6).setPiece(new Piece(true,"white"));
		this.getCell(5,6).setPiece(new Piece(true,"white"));
		this.getCell(6,6).setPiece(new Piece(true,"white"));
		
		this.getCell(3,5).setPiece(new Piece(true,"white"));
		this.getCell(4,5).setPiece(new Piece(true,"white"));
		this.getCell(5,5).setPiece(new Piece(false,"white"));
		this.getCell(6,5).setPiece(new Piece(true,"white"));
		this.getCell(7,5).setPiece(new Piece(true,"white"));

		this.getCell(4,4).setPiece(new Piece(true,"white"));
		this.getCell(5,4).setPiece(new Piece(true,"white"));
		this.getCell(6,4).setPiece(new Piece(true,"white"));
		
		this.getCell(5,3).setPiece(new Piece(true,"white"));
	}

	// SETTERS

	/**
	 * Changes the turn the next players turn
	 */
	public void changeTurns() {
		if (turn == attacker) {
			this.turn = defender;
		} else {
			this.turn = attacker;
		}
	}

	/**
	 * Set the end time of the game
	 * @param end
	 */
	public void setEndTime(String end) {
		this.endTime = end;
	}

	/**
	 * Sends a move, not the entire game, to the server.
	 * @return true if the server responds correctly, false otherwise
	 */
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

	/**
	 * Gets the start time of the game
	 * @return game start time
	 */
	public String getStartTime() {
		return this.startTime;
	}

	/**
	 * Gets the end time of the game
	 * @return game end time
	 */
	public String getEndTime() {
		return this.endTime;
	}

	/**
	 * Gets the turn player
	 * @return current turn
	 */
	public Player getTurn() {
		return this.turn;
	}

	/**
	 * Gets the attacker player
	 * @return attacker
	 */
	public Player getAttacker() {
		return this.attacker;
	}

	/**
	 * Gets the defender player
	 * @return defender
	 */
	public Player getDefender() {
		return this.defender;
	}

	/**
	 * Gets the arrays of moves
	 * @return moves
	 */
	public Move [] getMoves() {
		return moves;
	}

	/**
	 * Get cell at position (x,y)
	 * @param x position x
	 * @param y position y
	 * @return the cell at position (x,y) if one exists, null otherwise
	 */
	public Cell getCell(int x, int y) {
		for (Cell c : cells)
			if (c.getX() == x && c.getY() == y)
				return c;
		return null;
	}

	/**
	 * Gets the result of the game
	 * @return game result
	 */
	public GameResult getResult() {
		return result;
	}

	/**
	 * Sets the result of the game
	 * @param result game result
	 */
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
		result = prime * result + Arrays.hashCode(moves);
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
		if (!Arrays.equals(moves, other.moves))
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

}// End Class
