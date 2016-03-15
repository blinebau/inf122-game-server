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
	
	public Piece removePiece(BoardIndex index) {
		Piece piece = board[index.getColumnIndex()][index.getRowIndex()];
		board[index.getColumnIndex()][index.getRowIndex()] = null;
		return piece;
	}
	
	public Piece[][] getBoard() {
		return board;
	}
}
