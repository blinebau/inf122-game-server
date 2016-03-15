package Checkers.view;
import java.util.List;
import app.controller.BoardGameController;
import app.model.BoardIndex;
import app.model.Piece;
import app.view.*;
import javafx.event.ActionEvent;
import javafx.scene.paint.Color;

/**
 * Created by Roy on 3/8/16.
 */
public class CheckersGUI extends GameGUI {
	
	public CheckersGUI(BoardGameController controller, Piece[][] game2DArray) {
		super(controller, game2DArray);
		board = new BoardGameGridPane(game2DArray.length, game2DArray[0].length, 2, 2, 50, Color.IVORY, Color.BLACK, this);
		board.setPieceStartingLayout(game2DArray);
	}
	
	public void hightlightTile(List<BoardIndex> pos) {
		board.highlightAssociatedTiles(pos);
	}
	
	public void clearHighlight() {
		board.resetHighlightedTiles();
	}
	
	// Called by controller when receiving a finish move from opponent
	public void showMyTurn() {
		// TODO: Shows a popup window says "Your Turn"
		
	}

    @Override
    public void handleActionEvent(ActionEvent event) {

    }
}
