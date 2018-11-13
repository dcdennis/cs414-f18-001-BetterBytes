package edu.colostate.cs.cs414.betterbytes.p3.game;

public class Board {
	
	
	private Cell cells[];

	private Cell kingCell;

	
	public Board() {
		this.cells = initializeBoard() ;
	}
	public Board(String boardData) {
		//TODO initialize the cells from a string
		//EX: dan_devin.txt (PKG: edu.colostate.cs.cs414.betterbytes.p3.data.games)
	}

	
	public Cell[] getSquaresOnBoard() {
		return this.cells;
	}
	
	
	public Cell[] initializeBoard(){

		Cell cells[]=new Cell[121];
		//TODO: add Corner and Throne Cells
		//Create new Cell("C") and new Cell("T") based off index of board
		for(int i=0;i<121;i++){
			cells[i]= new Cell("S",i%11, (i/11)%11);
		}
		return cells;

	}
	
	public Cell getCell(int x, int y) {
		// CHECK INDEX LOGIC
		return cells[11*y+x];
	}

	public Cell findKing(){
		if(kingCell != null){
			return kingCell;
		}
		if(getCell(5, 5).isOccupied() && getCell(5, 5).getPiece().isKing()){
			return getCell(5,5);
		}
		for (int i = 0; i < 121; i++){
			if(cells[i].isOccupied() && cells[i].getPiece().isKing()){
				return cells[i];
			}
		}
		return null;
	}

	public static void main(String[] args){
		Board b = new Board();
		System.out.println(b.getCell(5, 8).getX() + " " + b.getCell(5, 8).getY());
	}
}
