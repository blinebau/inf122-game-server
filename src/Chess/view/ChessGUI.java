package Chess.view;

import Chess.controller.ChessController;
import app.model.BoardIndex;
import app.model.Piece;
import app.view.BoardGameGridPane;
import app.view.GameGUI;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Created by jgreene on 3/5/16.
 * GUI class for Chess
 */
public class ChessGUI extends GameGUI { // ChessGUI is a Group

    protected ChessController controller;
    private Text gameStatusText;

    public Piece copyOfPieceOnBoard(BoardIndex pos){
        Piece copy;
        copy = this.game2DArray[pos.getColumnIndex()][pos.getRowIndex()];
        return copy;
    }

    public void updateGame2DArray(BoardIndex source, BoardIndex destination, Piece movePiece){
        this.game2DArray[destination.getColumnIndex()][destination.getRowIndex()] = movePiece;
        this.game2DArray[source.getColumnIndex()][source.getRowIndex()] = null;
    }


    public ChessGUI(ChessController controller, Piece[][] game2DArray) {

        this.controller = controller;
        this.game2DArray = game2DArray;

        // Initialize the custom gridpane with 8 rows/cols, Set the horizonal and vertical gaps to 2
        board = new BoardGameGridPane(game2DArray.length, game2DArray[0].length, 2, 2, 50, Color.AQUA, Color.GREEN, this); // GameGUI extends CustomEventHandler

        // Set the starting piece layout of the board
        board.setPieceStartingLayout(game2DArray);

        // Add the board as an element of ChessGUI
        BorderPane border = new BorderPane();
        Text right = new Text("8\n7\n6\n5\n4\n3\n2\n1");
        right.setFont(Font.font("Arial", FontWeight.BOLD, 45));
        Text bottom = new Text(" a  b  c  d  e   f  g  h");
        bottom.setFont(Font.font("Arial", FontWeight.BOLD, 45));
        Text gameStatusLabel = new Text("Game Status: ");
        gameStatusLabel.setFont(Font.font("Arial", 20));
        gameStatusText = new Text();
        gameStatusText.setFont(Font.font("Arial", 20));
        gameStatusText.setX(gameStatusLabel.getLayoutBounds().getWidth());
        Group gameStatusGrp = new Group();
        gameStatusGrp.getChildren().addAll(gameStatusLabel, gameStatusText);

        border.setRight(right);
        border.setBottom(bottom);
        border.setTop(gameStatusGrp);
        border.setCenter(board);

        getChildren().add(border);
    }

    public Text getGameStatusText() {
        return gameStatusText;
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
