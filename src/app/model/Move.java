package app.model;

import app.model.BoardIndex;
import java.io.Serializable;


/**
 *
 * @author Jiahao Chen
 *
 */

public class Move implements Serializable {

    protected final BoardIndex dest;

    public Move(BoardIndex index) {
        this.dest = index;
    }

	public BoardIndex getDest() {
		return dest;
	}
}
