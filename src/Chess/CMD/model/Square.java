package Chess.CMD.model;

import java.io.Serializable;


public class Square implements Serializable{

	
	private int x;	//Rank is row, x value
	private int y;	//File is column, y value
	
	private static final long serialVersionUID = 1L;
	
	private ChessPiece piece; //this chess piece is occupying the current position (square)
	
	public int getX() { return x; }
	
	public int getY() { return y; }
	
	public Square(int r, int c) {
		this.y = r;
		this.x = c;
	}
	
	public ChessPiece getPiece() { return piece; }
	
	public void setPiece(ChessPiece piece) { this.piece = piece; }
	
	@Override
	public boolean equals(Object o) {
		
		if (!(o instanceof Square)) return false;
		
		if (this.getX() == ((Square) o).getX() && this.getY() == ((Square) o).getY()) return true;
		
		return false;
	}
	
	/*
	 * These methods don't help too much, if we want to compare rank between two squares, it's just square.getRank == othersquare.getrank
	 * 
	public boolean sameRank(Square eqRank) {
		if (this.rank == eqRank.getRank())
			return true;
		else
			return false;
		
	}
	public boolean sameFile(Square eqFile) {
			if (this.file == eqFile.getFile())
				return true;
			else
				return false;
		
		
	}
	*/
	
}
