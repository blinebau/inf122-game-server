package Checkers.view;
import java.util.List;
import app.controller.BoardGameController;
import app.model.BoardIndex;
import app.model.Piece;
import app.view.*;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * Created by Roy on 3/8/16.
 */
public class CheckersGUI extends GameGUI {
	
	public CheckersGUI(BoardGameController controller, Piece[][] game2DArray) {
		super(controller, game2DArray);
		board = new BoardGameGridPane(game2DArray.length, game2DArray[0].length, 2, 2, 50, Color.BISQUE, Color.CHOCOLATE, this);
		board.setPieceStartingLayout(game2DArray);

        BorderPane border = new BorderPane();
//        Group gameStatusGrp = new Group();
        border.setCenter(board);

        this.getChildren().add(border);
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
    public void handleMouseEvent(MouseEvent event) {
        if (event.getSource() instanceof Rectangle) {
            Shape tile = (Rectangle) event.getSource();
            controller.tileSelected(board.getBoardIndex(tile));
        }
        // TODO Talk with Jiahao to separate concern in CheckerController.tileSelected()
        /*
        else if (event.getSource() instanceof ImageView) {
            ImageView imgView = (ImageView) event.getSource();
            controller.pieceSelected(board.getBoardIndex(clickedImgView));
        }
        */

    }

    @Override
    public void handleActionEvent(ActionEvent event) {
    }

}
