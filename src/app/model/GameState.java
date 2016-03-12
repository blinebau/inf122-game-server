package app.model;

public class GameState {
	
	private Piece[][] board;
	
	public GameState(int columns, int rows) {
		board = new Piece[columns][rows];
	}
	
	public void putPiece(Piece piece , BoardIndex index) {
		board[index.getColumnIndex()][index.getRowIndex()] = piece;
	}
	
	public Piece getIndex(BoardIndex index) {
		return board[index.getColumnIndex()][index.getRowIndex()];
	}
	
	public void removePiece(BoardIndex index) {
		board[index.getColumnIndex()][index.getRowIndex()] = null;
	}
}
