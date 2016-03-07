package Chess.CMD.model;

import java.io.Serializable;

public class Move implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	
	public ChessPiece chessPiece;
	public ChessPiece capture;
	public Square source;
	public Square destination;
	
	public Move(ChessPiece chessPiece, ChessPiece capture, Square source, Square destination) {
		this.chessPiece = chessPiece;
		this.capture = capture;
		this.source = source;
		this.destination = destination;
	}

	

}

