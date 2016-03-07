package Chess.CMD.model;

public class Knight extends ChessPiece {

	public String getInitial() { return "N"; }

	/*
	 * Specific for rules of Knight chess piece:
	 * Only piece that can jump over pieces
	 * "L" shape movement 	
	 */
	
	public boolean isValidMove(Square dest) 
	{
		int xPos = Math.abs( dest.getX() - getLocation().getX());
		int yPos= Math.abs( dest.getY() - getLocation().getY());
		
		if ((xPos == 2 && yPos == 1)) return true;
		
		if ((xPos == 1 && yPos == 2)) return true;


		return false;
	}

}
