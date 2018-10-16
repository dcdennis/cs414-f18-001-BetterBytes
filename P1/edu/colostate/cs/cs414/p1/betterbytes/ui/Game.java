package edu.colostate.cs.cs414.p1.betterbytes.ui;

import java.awt.BorderLayout;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;

import edu.colostate.cs.cs414.p1.betterbytes.utilities.Tools;

/**
 * This class represents a physical Tafl game, including the grid which contains
 * cells that contain pieces. This is to be used as an interface to the
 * graphical representation of a single game.
 * 
 * @author Daniel McClure - 830437441
 *
 */
public class Game extends JFrame {

	private static final long serialVersionUID = 1L;

	private BufferPanel back = null;
	private String status = "Welcome";
	private Grid grid = new Grid(-50, -50, this);
	private boolean isOurTurn = true;
	private boolean secondCheck = false;
	private int width = 886;
	private int height = 935;
	

	/**
	 * This starts a new game.
	 */
	public Game() {
		this.setup();
		this.setupNewGame();
	}

	/**
	 * This will start a game based off of an ArrayList of Cells
	 * 
	 * @param board
	 *            ArrayList of Cells
	 */
	public Game(ArrayList<Cell> board) {
		this.setup();
		this.setBoard(board);
	}

	/**
	 * This will start a game based off a String of board data.
	 * 
	 * @param board
	 *            String of data
	 */
	public Game(String board) {
		this.setup();
		this.getBufferPanel().getGrid().setBoardFromString(board);
	}

	/**
	 * Sets up UI
	 */
	public void setup() {
		this.setSize(886, 935);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(width, height);
		this.back = new BufferPanel(this);
		this.add(back, BorderLayout.CENTER);
		this.setVisible(true);
		new Thread(back).start();
		this.setTitle("Tafl V" + 1);
		this.setResizable(false);
	}

	public static void main(String[] arg0) {
		Game gf = new Game();

		/*
		 * gf.placePiece(new Piece(PieceType.KING, true), 1, 7);
		 * gf.placePiece(new Piece(PieceType.KING, true), 2, 5);
		 * gf.placePiece(new Piece(PieceType.KING, false), 3, 3);
		 * gf.placePiece(new Piece(PieceType.KING, true), 5, 8);
		 * gf.placePiece(new Piece(PieceType.KING, false), 4, 7);
		 * gf.placePiece(new Piece(PieceType.KING, true), 6, 6);
		 * gf.placePiece(new Piece(PieceType.KING, false), 7, 3);
		 * gf.placePiece(new Piece(PieceType.KING, true), 8, 4);
		 * 
		 * Tools.log(gf.getBufferPanel().getGrid().saveToString());
		 */

		// String it =
		// "~1:1:75:null:false~1:2:75:null:false~1:3:75:null:false~1:4:75:null:false~1:5:75:null:false~1:6:75:null:false~1:7:75:KING:true~1:8:75:null:false~2:1:75:null:false~2:2:75:null:false~2:3:75:null:false~2:4:75:null:false~2:5:75:KING:true~2:6:75:null:false~2:7:75:null:false~2:8:75:null:false~3:1:75:null:false~3:2:75:null:false~3:3:75:KING:false~3:4:75:null:false~3:5:75:null:false~3:6:75:null:false~3:7:75:null:false~3:8:75:null:false~4:1:75:null:false~4:2:75:null:false~4:3:75:null:false~4:4:75:null:false~4:5:75:null:false~4:6:75:null:false~4:7:75:KING:false~4:8:75:null:false~5:1:75:null:false~5:2:75:null:false~5:3:75:null:false~5:4:75:null:false~5:5:75:null:false~5:6:75:null:false~5:7:75:null:false~5:8:75:KING:true~6:1:75:null:false~6:2:75:null:false~6:3:75:null:false~6:4:75:null:false~6:5:75:null:false~6:6:75:KING:true~6:7:75:null:false~6:8:75:null:false~7:1:75:null:false~7:2:75:null:false~7:3:75:KING:false~7:4:75:null:false~7:5:75:null:false~7:6:75:null:false~7:7:75:null:false~7:8:75:null:false~8:1:75:null:false~8:2:75:null:false~8:3:75:null:false~8:4:75:KING:true~8:5:75:null:false~8:6:75:null:false~8:7:75:null:false~8:8:75:null:false";
		// gf.getBufferPanel().getGrid().setBoardFromString(it);

	}

	/**
	 * Hardcoded new game setup. Could be made smaller with int arrays, but that
	 * would essentially be close to the same amount of typing and harder to modify. 
	 */
	public void setupNewGame() {
		this.placePiece(new Piece(PieceType.ROOK, false), 4, 1);
		this.placePiece(new Piece(PieceType.ROOK, false), 5, 1);
		this.placePiece(new Piece(PieceType.ROOK, false), 6, 1);
		this.placePiece(new Piece(PieceType.ROOK, false), 7, 1);
		this.placePiece(new Piece(PieceType.ROOK, false), 8, 1);
		this.placePiece(new Piece(PieceType.ROOK, false), 6, 2);

		this.placePiece(new Piece(PieceType.ROOK, false), 1, 4);
		this.placePiece(new Piece(PieceType.ROOK, false), 1, 5);
		this.placePiece(new Piece(PieceType.ROOK, false), 1, 6);
		this.placePiece(new Piece(PieceType.ROOK, false), 1, 7);
		this.placePiece(new Piece(PieceType.ROOK, false), 1, 8);
		this.placePiece(new Piece(PieceType.ROOK, false), 2, 6);

		this.placePiece(new Piece(PieceType.ROOK, false), 11, 4);
		this.placePiece(new Piece(PieceType.ROOK, false), 11, 5);
		this.placePiece(new Piece(PieceType.ROOK, false), 11, 6);
		this.placePiece(new Piece(PieceType.ROOK, false), 11, 7);
		this.placePiece(new Piece(PieceType.ROOK, false), 11, 8);
		this.placePiece(new Piece(PieceType.ROOK, false), 10, 6);

		this.placePiece(new Piece(PieceType.ROOK, false), 4, 11);
		this.placePiece(new Piece(PieceType.ROOK, false), 5, 11);
		this.placePiece(new Piece(PieceType.ROOK, false), 6, 11);
		this.placePiece(new Piece(PieceType.ROOK, false), 7, 11);
		this.placePiece(new Piece(PieceType.ROOK, false), 8, 11);
		this.placePiece(new Piece(PieceType.ROOK, false), 6, 10);

		this.placePiece(new Piece(PieceType.KING, true), 6, 6);
		this.placePiece(new Piece(PieceType.ROOK, true), 5, 6);
		this.placePiece(new Piece(PieceType.ROOK, true), 4, 6);
		this.placePiece(new Piece(PieceType.ROOK, true), 6, 5);
		this.placePiece(new Piece(PieceType.ROOK, true), 6, 4);
		this.placePiece(new Piece(PieceType.ROOK, true), 6, 7);
		this.placePiece(new Piece(PieceType.ROOK, true), 6, 8);
		this.placePiece(new Piece(PieceType.ROOK, true), 7, 6);
		this.placePiece(new Piece(PieceType.ROOK, true), 8, 6);
		this.placePiece(new Piece(PieceType.ROOK, true), 5, 5);
		this.placePiece(new Piece(PieceType.ROOK, true), 7, 7);
		this.placePiece(new Piece(PieceType.ROOK, true), 5, 7);
		this.placePiece(new Piece(PieceType.ROOK, true), 7, 5);

	}

	public BufferPanel getBufferPanel() {
		return this.back;
	}

	public boolean setBoard(ArrayList<Cell> board) {
		return getGrid().setBoard(board);
	}

	public void placePiece(Piece p, int x, int y) {
		for (Cell c : this.getBufferPanel().getGrid().getCells())
			if (c.getX() == x && c.getY() == y)
				c.setPiece(p);
	}

	public boolean movePiece(Piece p, Cell old, Cell nu) {
		if (this.getGrid().canMove(old, nu.getX(), nu.getY())) {
			this.getGrid().movePiece(p, old, nu);
			return true;
		}
		return false;
	}

	public boolean removePiece(Cell c) {
		if (c != null && c.hasPiece()) {
			c.setPiece(null);
		}
		return false;
	}

	public Cell getCell(int x, int y) {
		for (Cell c : getGrid().getCells())
			if (c.getX() == x && c.getY() == y)
				return c;
		return null;
	}

	public boolean sendMoveToServer() {
		return getGrid().sendMoveToServer();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Grid getGrid() {
		return grid;
	}

	public void setGrid(Grid grid) {
		this.grid = grid;
	}

	public boolean isOurTurn() {
		return isOurTurn;
	}

	public void setOurTurn(boolean isOurTurn) {
		this.isOurTurn = isOurTurn;
	}

	public boolean isSecondCheck() {
		return secondCheck;
	}

	public void setSecondCheck(boolean secondCheck) {
		this.secondCheck = secondCheck;
	}

}
