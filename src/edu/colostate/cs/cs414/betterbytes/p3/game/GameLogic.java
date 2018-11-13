package edu.colostate.cs.cs414.betterbytes.p3.game;

public class GameLogic {

    private Board board;

    public GameLogic(){
        this.board = new Board();
    }

    public GameLogic(String boardData){
        this.board = new Board(boardData);
    }

    public void setBoard(String boardData){
        this.board = new Board(boardData);
    }

    public Board getBoard() {
        return board;
    }

    public boolean isEnemy(Cell cellA, Cell cellB){
        if (!(cellA.isOccupied() && cellB.isOccupied())){return false;}
        return isEnemy(cellA.getPiece(), cellB.getPiece());
    }

    public boolean isEnemy(Piece pieceA, Piece pieceB){
        if(pieceA == null || pieceA == null){return false;}
        return !(pieceA.getColor().equals(pieceB.getColor()));
    }

    public boolean isSurrounded(Cell cell){
        if (!cell.isOccupied()){return false;}
        Piece piece = cell.getPiece();
        int needed = (piece.isKing()) ? 3 : 2;
        int x = cell.getX();
        int y = cell.getY();
        if (x + 1 <= 10 && isEnemy(cell, board.getCell(x+1, y))){needed--;}
        if (x - 1 >= 0 && isEnemy(cell, board.getCell(x-1, y))){needed--;}
        if (y + 1 <= 10 && isEnemy(cell, board.getCell(x, y+1))){needed--;}
        if (y - 1 >= 0 && isEnemy(cell, board.getCell(x, y-1))){needed--;}
        return needed <= 0;
    }

    public boolean checkWin(){
        Cell king = board.findKing();
        if (king == null){return false;}
        return (isSurrounded(king));
    }

    public boolean canMove(Cell c, int x, int y){
        if (c == null || !c.isOccupied() || (c.getX() == x && c.getY() == y) || (c.getX() != x && c.getY() != y)){
            return false;
        }
        if (c.getPiece() == null){return false;}
        if (c.getX() != x){
            int low = Math.min(c.getX(), x) + 1;
            int high = Math.max(c.getX(), x);
            while(low < high){
                if(board.getCell(low, y).isOccupied()) { return false; }
                low++;
            }
        }
        else if (c.getY() != y){
            int low = Math.min(c.getY(), y) + 1;
            int high = Math.max(c.getY(), y);
            while(low < high){
                if(board.getCell(x, low).isOccupied()) { return false; }
                low++;
            }
        }
        return true;
    }

    public boolean canMove(Cell cellA, Cell cellB){
        return canMove(cellA, cellB.getX(), cellB.getY());
    }

    public boolean movePiece(Cell cellA, Cell cellB){
        if(canMove(cellA, cellB)){
            Piece piece = cellA.getPiece();
            cellA.removePiece();
            cellB.setPiece(piece);
            return true;
        }
        return false;
    }

    public static void main(String[] args){
        GameLogic GL = new GameLogic();
        Cell c1 = GL.board.getCell(5, 5);
        c1.setPiece(new Piece(false, "black"));
        Cell c2 = GL.board.getCell(4,5);
        c2.removePiece();
        System.out.println(GL.canMove(c1, c2));
        GL.movePiece(c1, c2);
    }
}
