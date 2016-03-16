package app.model;

public class GameState {
	
	private Piece[][] board;
	
	public GameState(int columns, int rows) {
		board = new Piece[columns][rows];
	}

	public Piece[][] getBoard() {
		return board;
	}
	
	public void putPiece(Piece piece , BoardIndex index) {
		board[index.getColumnIndex()][index.getRowIndex()] = piece;
	}
	
	public Piece getPiece(BoardIndex index) {
		return board[index.getColumnIndex()][index.getRowIndex()];
	}
	
	public Piece removePiece(BoardIndex index) {
		Piece piece = board[index.getColumnIndex()][index.getRowIndex()];
		board[index.getColumnIndex()][index.getRowIndex()] = null;
		return piece;
	}
}
