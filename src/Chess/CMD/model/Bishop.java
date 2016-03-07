package Chess.CMD.model;

public class Bishop extends ChessPiece {
	


	public String getInitial() { return "B"; }
	
	/*
	 * Specific for rules of Bishop chess piece:
	 * The bishop may move any number of squares in a diagonal direction 	
	 */
	
	public boolean isValidMove(Square dest) {
		int xPos = Math.abs( dest.getX() - getLocation().getX());
		int yPos= Math.abs( dest.getY() - getLocation().getY());
		if(xPos != yPos) //checking diagonal positions
		{
			return false;
		}
		return this.clearPathTo(dest);	//it can move if the diagonals are clear
	}

}
