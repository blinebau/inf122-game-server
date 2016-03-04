package models;

import java.awt.Point;	// favor awt Point over javafx Point2D because awt.Point is recorded with int coordinates

/**
 * 
 * @author Jiahao Chen
 *
 */

public class Move {

	private Point source;
	private Point destination;
	private boolean isMyTurn;	// Change the variable name if it is not descriptive enough.
	/*
	 * 	boolean variable is used to indicate whether one player is done with his turn.
	 * 	In Checkers, a player can jump multiple times when there are multiple opponent's pieces can be captured.
	 *  But the move is sent with one src and one dest
	 *  The receiver of a Move object need to check if he can make his move or he needs to wait for another move from the opponent.
	 */
	
	public Move(Point src, Point dest, boolean turn) {
		source = src;
		destination = dest;
		isMyTurn = turn;
	}
	
	public Point getSource() {
		return source;
	}
	
	public Point getDest() {
		return destination;
	}
	
	public boolean isMyTurn() {
		return isMyTurn;
	}
}
