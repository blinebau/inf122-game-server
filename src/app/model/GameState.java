package app.model;

public class GameState {
	
	private Piece[][] board;
	
	public GameState(int columns, int rows) {
		board = new Piece[columns][rows];
	}
	
	public void putPiece(Piece piece /*, Boardindex index*/) {
		
	}
}
