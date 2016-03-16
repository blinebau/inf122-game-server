package Chess.model;

import app.model.BoardIndex;
import app.model.Move;

/**
 * Created by Luke on 3/9/2016.
 */
public class ChessMove extends Move {

    private BoardIndex destination;
    private BoardIndex source;

    public ChessMove(BoardIndex source, BoardIndex destination){
        super(source);
        setDestination(destination);
        setSource(source);

    }


    public BoardIndex getDestination() {
        return destination;
    }

    public void setDestination(BoardIndex destination) {
        this.destination = destination;
    }


    public BoardIndex getSource() {
        return source;
    }

    public void setSource(BoardIndex source) {
        this.source = source;
    }
}
