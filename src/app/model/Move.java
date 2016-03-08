package app.model;

//import Chess.Logic.Board;
import app.model.BoardIndex;

import java.io.Serializable;


/**
 *
 * @author Jiahao Chen
 *
 */

public class Move implements Serializable{

    private BoardIndex index;
	//tuple for source and dest; represented as pair or multiple values
	//private final int  source;
	//private final int destination;
	private final boolean isMyTurn = false;	// Change the variable name if it is not descriptive enough.

	/*
	 * 	boolean variable is used to indicate whether one player is done with his turn.
	 * 	In Checkers, a player can jump multiple times when there are multiple opponent's pieces can be captured.
	 *  But the move is sent with one src and one dest
	 *  The receiver of a Move object need to check if he can make his move or he needs to wait for another move from the opponent.
	 */

    public Move(BoardIndex index)
    {
        this.index = index;
    }

	public BoardIndex getIndex() {
		return index;
	}

	public boolean isMyTurn() {
		return isMyTurn;
	}
}
