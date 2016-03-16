package TicTacToe;

import app.model.BoardIndex;
import app.model.Move;
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
 * Created by Sophia on 3/14/2016.
 */
public class TTTGUI extends GameGUI {

    public TTTGUI(TTTController controller, Piece[][] array) {
//        super(controller, array);
        this.controller = controller;
        this.game2DArray = array;

        this.board = new BoardGameGridPane(game2DArray.length, game2DArray[0].length, 5, 5, 200, Color.LIGHTGREY, null, this);

        getChildren().add(board);
    }

    public void updateGameBoard(Move m, Piece p) {
        this.game2DArray[m.getDest().getColumnIndex()][m.getDest().getRowIndex()] = p;
        this.board.addPieceToTile(new BoardIndex(m.getDest().getColumnIndex(), m.getDest().getRowIndex()), p);
    }

    @Override
    public void handleMouseEvent(MouseEvent event) {
        if(event.getSource() instanceof Rectangle) {
            Shape clickedTile = (Rectangle) event.getSource();
            this.controller.tileSelected(this.board.getBoardIndex(clickedTile));
        }
//        } else if (event.getSource() instanceof ImageView) {
//            ImageView clickedImage = (ImageView) event.getSource();
//            this.controller.tileSelected(this.board.getBoardIndex(clickedImage));
//        }
    }

    @Override
    public void handleActionEvent(ActionEvent event) {}
}
