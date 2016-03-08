package Chess.view;

import Chess.controller.ChessController;
import app.model.Piece;
import app.view.BoardGameGridPane;
import app.view.GameGUI;
import javafx.event.ActionEvent;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * Created by jgreene on 3/5/16.
 */
public class ChessGUI extends GameGUI { // ChessGUI is a Group

    protected ChessController controller;

    public ChessGUI(ChessController controller, Piece[][] game2DArray) {

        this.controller = controller;
        this.game2DArray = game2DArray;

        // Initialize the custom gridpane with 8 rows/cols, Set the horizonal and vertical gaps to 2
        board = new BoardGameGridPane(game2DArray.length, game2DArray[0].length, 2, 2, 50, Color.AQUA, Color.GREEN, this); // GameGUI extends CustomEventHandler

        // Set the starting piece layout of the board
        board.setPieceStartingLayout(game2DArray);

        // Add the board as an element of ChessGUI
        getChildren().add(board);
    }

    @Override
    public void handleMouseEvent(MouseEvent event) {
        if (event.getSource() instanceof Rectangle) {
            Shape clickedTile = (Rectangle) event.getSource();
            controller.tileSelected(board.getBoardIndex(clickedTile));

        } else if (event.getSource() instanceof ImageView) { // Piece clicked (why this method is being overriden)
            ImageView clickedImgView = (ImageView) event.getSource();
            controller.pieceSelected(board.getBoardIndex(clickedImgView));
        }
    }

    @Override
    public void handleActionEvent(ActionEvent event) {

    }
}
