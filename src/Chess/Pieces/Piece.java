package Chess.Pieces;

import Chess.Logic.Square;

public abstract class Piece extends Square{
	
	public Piece(String colorIn, String typeIn) {
		super(typeIn);
		color = colorIn;
	} 
}
