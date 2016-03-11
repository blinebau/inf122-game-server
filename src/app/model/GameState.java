package app.model;

public class GameState {
	
	private Piece[][] board;
	
	public GameState(int columns, int rows) {
		board = new Piece[columns][rows];
	}
	
	public void putPiece(Piece piece , BoardIndex index) {
		board[index.getColumnIndex()][index.getRowIndex()] = piece;
	}
	
	public void putPiece(Piece piece , int col, int row) {
		board[col][row] = piece;
	}
	
	public Piece getIndex(BoardIndex index) {
		return board[index.getColumnIndex()][index.getRowIndex()];
	}
	
	public Piece getIndex(int col, int row) {
		return board[col][row];
	}
	
	public void removePiece(BoardIndex index) {
		board[index.getColumnIndex()][index.getRowIndex()] = null;
	}
	
	public void removePiece(int col, int row) {
		board[col][row] = null;
	}
}
