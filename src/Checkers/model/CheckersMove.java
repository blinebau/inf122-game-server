package Checkers.model;

import java.io.Serializable;
import app.model.BoardIndex;
import app.model.Move;

/**
 * 
 * @author Jiahao Chen
 *
 */

public class CheckersMove extends Move implements Serializable{
	
	private BoardIndex source;
	private boolean isTurnOver;

	public CheckersMove(BoardIndex src, BoardIndex dest, boolean b) {
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
