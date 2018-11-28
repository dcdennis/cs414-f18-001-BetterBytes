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

	public Cell [] cells;
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
		ArrayList<Cell> tempCells = new ArrayList<Cell>();
		for(int i = 0; i < 11; i++)
		{
			for(int j = 0; j < 11; j++)
			{
				if((i == 0 || i==10) && (j == 0 || j == 10))
					tempCells.add(new Cell(i,j,"C",null));
				else if(i == 5 && j == 5)
					tempCells.add(new Cell(i,j,"T",null));
				else
					tempCells.add(new Cell(i,j,"S",null));
			}
		}
		tempCells.toArray(cells);
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
