package edu.colostate.cs.cs414.betterbytes.p3.game;

public class Cell {

	public final String CORNER = "C"; // 4 corner cells
	public final String THRONE = "T"; // middle throne cell
	public final String SQUARE = "S"; // normal cells
	
	// GLOBALS
	private String type;
	private Piece piece;
	private boolean isOccupied;

	private int x;
	private int y;
	
	
	// CTOR
	public Cell() {}
	public Cell(String type, int x, int y) {
		this.type = type;
		this.x = x;
		this.y = y;
	}
	public Cell(String type, Piece piece, int x, int y) {
		this.piece = piece;
		this.type = type;
		this.x = x;
		this.y = y;
	}

	// ACCESSORS
	public boolean isCorner() {
		return this.type.equals(CORNER);
	}
	
	public boolean isThrone() {
		return this.type.equals(THRONE);
	}
	
	public boolean isSquare() {
		return this.type.equals(SQUARE);
	}
	
	public boolean isOccupied() {
		return this.isOccupied;
	}
	
	public Piece getPiece() {
		return this.piece;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	// MUTATORS
	public void setPiece(Piece piece) {
		this.piece = piece;
		if(isOccupied == false) {
			this.isOccupied = true;
		}
	}

	public void removePiece(){
		this.piece = null;
		this.isOccupied = false;
	}
	
	
}
