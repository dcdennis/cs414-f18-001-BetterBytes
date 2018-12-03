package edu.colostate.cs.cs414.betterbytes.p3.ui;

import java.awt.BorderLayout;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JFrame;

import edu.colostate.cs.cs414.betterbytes.p3.client.ClientConnection;
import edu.colostate.cs.cs414.betterbytes.p3.game.Game;
import edu.colostate.cs.cs414.betterbytes.p3.user.Player;
import edu.colostate.cs.cs414.betterbytes.p3.utilities.Tools;
import edu.colostate.cs.cs414.betterbytes.p3.wireforms.SubmitMove;
import edu.colostate.cs.cs414.betterbytes.p3.wireforms.SubmitMoveResponse;

/**
 * This class represents a physical Tafl game, including the grid which contains
 * cells that contain pieces. This is to be used as an interface to the
 * graphical representation of a single game.
 * 
 * @author Daniel McClure - 830437441
 *
 */
public class GameFrame extends JFrame implements Serializable {

	private static final long serialVersionUID = 1L;

	private BufferPanel back = null;
	private String status = "Welcome";
	private Grid grid = new Grid(-50, -50, this);
	private boolean isOurTurn = true;
	private boolean secondCheck = false;
	private int width = 886;
	private int height = 935;
	private Player turn = null;
	private Game game = null;
	private ClientConnection connection = null;
	public int moveCount = 0;

	public GameFrame(Game game, ClientConnection connection) {
		this.connection = connection;
		this.game = game;
		this.setup();
		this.display(game);
	}

	/**
	 * Sets up UI
	 */
	public void setup() {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(width, height);
		this.back = new BufferPanel(this);
		this.add(back, BorderLayout.CENTER);
		this.setVisible(true);
		new Thread(back).start();
		this.setTitle("Tafl V" + 1);
		this.setResizable(false);
		this.setupNewGame();
	}

	public static void main(String[] arg0) {
		GameFrame gf = new GameFrame(new Game(), null);

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
		if (this.canMove(old, nu.getX(), nu.getY()) && moveCount == 0) {
			this.getGrid().movePiece(p, old, nu);
			moveCount++;
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
		return UI.user != null && turn != null && turn.getAccount().equals(UI.user);
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
	 * This method returns whether the piece of Cell c can move to the x y given.
	 * 
	 * @param c Cell that contains the piece to move
	 * @param x destination x
	 * @param y destination y
	 * @return whether the piece can move to destination
	 */
	public boolean canMove(Cell c, int x, int y) {
		if (turn != null && turn.getColor() != null)
			// ools.log("Our Color: "+turn.getColor());
			if (!c.hasPiece() || (c.equals(new Cell(x, y, this.getGrid()))) || !isOurTurn()
					|| ((turn.getColor().equals("white") && (c.hasPiece() && !c.getPiece().isWhite()))
							|| (turn.getColor().equals("black") && (c.hasPiece() && c.getPiece().isWhite()))))
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
				
				if (c.getPiece().getType().equals(PieceType.ROOK)) {
					if(this.getCell(x, y).getIcon() != null) {
						return false;
					} else if(x == 6 && y == 6) {
						return false;
					}
				}
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	public void display(Game game) {
		ArrayList<Cell> cs = new ArrayList<Cell>();
		if (game.cells != null && game.cells.length > 0) {
			for (edu.colostate.cs.cs414.betterbytes.p3.game.Cell c : game.cells) {
				if (c != null) {
					Cell nu = new Cell(c.getX() + 1, c.getY() + 1, this.getGrid());
					edu.colostate.cs.cs414.betterbytes.p3.game.Piece p = c.getPiece();
					if (p != null) {
						Piece nup = new Piece(p.getType().equals("king") ? PieceType.KING : PieceType.ROOK,
								p.getColor().equals("white") ? true : false);
						nu.setPiece(nup);
					}
					cs.add(nu);
				}
			}
			this.getGrid().setBoard(cs);
		}

		moveCount = 0;
		this.game = game;
		this.turn = game.getTurn();
//		if (this.turn.getAccount().getUsername().equals("ctunnell"))
//			this.turn.color = "white";
//		else
//			this.turn.color = "black";		
		this.setTitle("Turn: "+this.turn.color + "    for account: " + turn.getAccount().getUsername());
	}

	public ArrayList<edu.colostate.cs.cs414.betterbytes.p3.game.Piece> convertGridForGame() {
		ArrayList<edu.colostate.cs.cs414.betterbytes.p3.game.Piece> pieces = new ArrayList<edu.colostate.cs.cs414.betterbytes.p3.game.Piece>();
		for (Cell c : this.getGrid().getCells()) {
			if (c.hasPiece()) {
				edu.colostate.cs.cs414.betterbytes.p3.game.Piece p = new edu.colostate.cs.cs414.betterbytes.p3.game.Piece(
						c.getPiece().getType().equals(PieceType.ROOK), c.getPiece().isWhite() ? "white" : "black");
			} else {
				pieces.add(null);
			}
		}
		return pieces;
	}

	public boolean sendMoveToServer() {
		ArrayList<edu.colostate.cs.cs414.betterbytes.p3.game.Cell> gamecells = new ArrayList<edu.colostate.cs.cs414.betterbytes.p3.game.Cell>();
		for (Cell c : this.getGrid().getCells()) {
			edu.colostate.cs.cs414.betterbytes.p3.game.Cell nu = new edu.colostate.cs.cs414.betterbytes.p3.game.Cell(
					c.getX() - 1, c.getY() - 1, null, null);
			if (c.hasPiece()) {
				edu.colostate.cs.cs414.betterbytes.p3.game.Piece nup = new edu.colostate.cs.cs414.betterbytes.p3.game.Piece(
						c.getPiece().getType().equals(PieceType.ROOK), c.getPiece().isWhite() ? "white" : "black");
				nu.setPiece(nup);
			}

			if ((nu.getX() == 0 || nu.getX() == 10) && (nu.getY() == 0 || nu.getY() == 10))
				nu.setType("C");
			else if (nu.getX() == 5 && nu.getY() == 5)
				nu.setType("T");
			else
				nu.setType("S");

			gamecells.add(nu);
		}
		if (game == null) {
			Tools.log("Game object is null");
			return false;
		}
		game.cells = gamecells.toArray(new edu.colostate.cs.cs414.betterbytes.p3.game.Cell[] {});
		if (((SubmitMoveResponse) connection.send(new SubmitMove(game))).getStatus()) {
			turn = null;
			return true;
		} else {
			return false;
		}
	}

}
