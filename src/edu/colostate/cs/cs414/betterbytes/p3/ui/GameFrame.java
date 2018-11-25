package edu.colostate.cs.cs414.betterbytes.p3.ui;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;

import edu.colostate.cs.cs414.betterbytes.p3.game.Game;

/**
 * This class represents a physical Tafl game, including the grid which contains
 * cells that contain pieces. This is to be used as an interface to the
 * graphical representation of a single game.
 * 
 * @author Daniel McClure - 830437441
 *
 */
public class GameFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private BufferPanel back = null;
	private String status = "Welcome";
	private Grid grid = new Grid(-50, -50, this);
	private boolean isOurTurn = true;
	private boolean secondCheck = false;
	private int width = 886;
	private int height = 935;
	private Game game = null;
 
	/**
	 * This starts a new game.
	 */
	public GameFrame() {
		this.setup();
		this.setupNewGame();
	}

	/**
	 * This will start a game based off of an ArrayList of Cells
	 * 
	 * @param board
	 *            ArrayList of Cells
	 */
	public GameFrame(ArrayList<Cell> board) {
		this.setup();
		this.setBoard(board);
	}

	/**
	 * This will start a game based off a String of board data.
	 * 
	 * @param board
	 *            String of data
	 */
	public GameFrame(String board) {
		this.setup();
		this.getGrid().setBoardFromString(board);
	}

	/**
	 * Sets up UI
	 */
	public void setup() {
		this.setSize(886, 935);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(width, height);
		this.back = new BufferPanel(this);
		this.add(back, BorderLayout.CENTER);
		this.setVisible(true);
		new Thread(back).start();
		this.setTitle("Tafl V" + 1);
		this.setResizable(false);
	}

	public static void main(String[] arg0) {
		GameFrame gf = new GameFrame();

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

		// Tools.log(gf.getGrid().saveToString());

	}
	
	public void setupNewGame() {

		this.getGrid().setBoardFromString(
				"~1:1:null:false~1:2:null:false~1:3:null:false~1:4:ROOK:false~1:5:ROOK:false~1:6:ROOK:false~1:7:ROOK:false~1:8:ROOK:false~1:9:null:false~1:10:null:false~1:11:null:false~2:1:null:false~2:2:null:false~2:3:null:false~2:4:null:false~2:5:null:false~2:6:ROOK:false~2:7:null:false~2:8:null:false~2:9:null:false~2:10:null:false~2:11:null:false~3:1:null:false~3:2:null:false~3:3:null:false~3:4:null:false~3:5:null:false~3:6:null:false~3:7:null:false~3:8:null:false~3:9:null:false~3:10:null:false~3:11:null:false~4:1:ROOK:false~4:2:null:false~4:3:null:false~4:4:null:false~4:5:null:false~4:6:ROOK:true~4:7:null:false~4:8:null:false~4:9:null:false~4:10:null:false~4:11:ROOK:false~5:1:ROOK:false~5:2:null:false~5:3:null:false~5:4:null:false~5:5:ROOK:true~5:6:ROOK:true~5:7:ROOK:true~5:8:null:false~5:9:null:false~5:10:null:false~5:11:ROOK:false~6:1:ROOK:false~6:2:ROOK:false~6:3:null:false~6:4:ROOK:true~6:5:ROOK:true~6:6:KING:true~6:7:ROOK:true~6:8:ROOK:true~6:9:null:false~6:10:ROOK:false~6:11:ROOK:false~7:1:ROOK:false~7:2:null:false~7:3:null:false~7:4:null:false~7:5:ROOK:true~7:6:ROOK:true~7:7:ROOK:true~7:8:null:false~7:9:null:false~7:10:null:false~7:11:ROOK:false~8:1:ROOK:false~8:2:null:false~8:3:null:false~8:4:null:false~8:5:null:false~8:6:ROOK:true~8:7:null:false~8:8:null:false~8:9:null:false~8:10:null:false~8:11:ROOK:false~9:1:null:false~9:2:null:false~9:3:null:false~9:4:null:false~9:5:null:false~9:6:null:false~9:7:null:false~9:8:null:false~9:9:null:false~9:10:null:false~9:11:null:false~10:1:null:false~10:2:null:false~10:3:null:false~10:4:null:false~10:5:null:false~10:6:ROOK:false~10:7:null:false~10:8:null:false~10:9:null:false~10:10:null:false~10:11:null:false~11:1:null:false~11:2:null:false~11:3:null:false~11:4:ROOK:false~11:5:ROOK:false~11:6:ROOK:false~11:7:ROOK:false~11:8:ROOK:false~11:9:null:false~11:10:null:false~11:11:null:false");

	}

	public void defaultSetup() {
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
		if (this.canMove(old, nu.getX(), nu.getY())) {
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

	public void clearBoard() {
		for (Cell c : this.getGrid().getCells()) {
			c.setPiece(null);
		}
	}

	public ArrayList<Cell> getBoard() {
		return this.getGrid().getCells();
	}
	
	/**
	 * This method returns whether the piece of Cell c can move to the x y
	 * given.
	 * 
	 * @param c
	 *            Cell that contains the piece to move
	 * @param x
	 *            destination x
	 * @param y
	 *            destination y
	 * @return whether the piece can move to destination
	 */
	public boolean canMove(Cell c, int x, int y) {
		if (!c.hasPiece() || (c.equals(new Cell(x, y, this.getGrid()))))
			return false;
		if (c.getPiece() != null) {
			if (((x == c.getX() && y != c.getY()) || (y == c.getY() && x != c.getX()))
					&& !this.getCell(x, y).hasPiece()) {
				if (y == c.getY()) {
					if (x < c.getX()) {
						for (int xx = c.getX(); xx > x; xx--) {
							if (xx != c.getX() && this.getCell(xx, y).hasPiece()) {
								return false;
							}
						}
					} else {
						for (int xx = c.getX(); xx < x; xx++) {
							if (xx != c.getX() && this.getCell(xx, y).hasPiece()) {
								return false;
							}
						}
					}
				} else {
					if (y < c.getY()) {
						for (int yy = c.getY(); yy > y; yy--) {
							if (yy != c.getY() && this.getCell(x, yy).hasPiece()) {
								return false;
							}
						}
					} else {
						for (int yy = c.getY(); yy < y; yy++) {
							if (yy != c.getY() && this.getCell(x, yy).hasPiece()) {
								return false;
							}
						}
					}
				}
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public boolean sendMoveToServer() {
		if(this.getGame() != null) {
			this.getGame().sendGameToServer();
		}
		return false;
	}

}
