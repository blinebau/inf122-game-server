package TicTacToe;

import app.model.BoardIndex;
import app.model.Move;
import app.model.Piece;
import app.view.GameGUI;
import javafx.event.ActionEvent;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * Created by Sophia on 3/14/2016.
 */
public class TTTGUI extends GameGUI {

    public TTTGUI(TTTController controller, Piece[][] array) {
        super(controller, array);
    }

    public void updateGameBoard(Move m, Piece p) {
       this.game2DArray[m.getDest().getColumnIndex()][m.getDest().getRowIndex()] = p;
    }

    @Override
    public void handleMouseEvent(MouseEvent event) {
        if(event.getSource() instanceof Rectangle) {
            Shape clickedTile = (Rectangle) event.getSource();
            controller.tileSelected(board.getBoardIndex(clickedTile));
        } else if (event.getSource() instanceof ImageView) {
            ImageView clickedImage = (ImageView) event.getSource();
            controller.tileSelected(board.getBoardIndex(clickedImage));
        }
    }

    @Override
    public void handleActionEvent(ActionEvent event) {}
}
