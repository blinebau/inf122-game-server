package Checkers.model;

import app.model.BoardIndex;
import app.model.Move;

/**
 * 
 * @author Jiahao Chen
 *
 */

public class CheckersMove extends Move {
	
	private BoardIndex source;
	private boolean isTurnOver;

	public CheckersMove(BoardIndex dest, BoardIndex src, boolean b) {
		super(dest);
		source = src;
		isTurnOver = b;
	}
	
	public BoardIndex getSource() {
		return source;
	}
	
	public boolean isTurnOver() {
		return isTurnOver;
	}
}
