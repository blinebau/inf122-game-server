package GeneralGameBoard;

import app.model.BoardIndex;
import app.model.Piece;
import app.view.BoardGameGridPane;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Created by jgreene on 3/2/16.
 */
public class ExampleGame extends Application implements EventHandler<Event> {

    Stage window;
    Scene myScene;

    BoardGameGridPane board;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Testing BoardGameGridPane");

        // Initialize the custom gridpane with 8 rows/cols, Set the horizonal and vertical gaps to 2, Pass this class since it will serve
        // as the board's handler for mouse events
        board = new BoardGameGridPane(8, 8, 2, 2, 50, Color.AQUA, null, this);

        Piece[][] tempState = new Piece[8][8];

        tempState[0][0] = new TempPiece();
        tempState[1][0] = new TempPiece();
        tempState[2][0] = new TempPiece();
        tempState[3][0] = new TempPiece();
        tempState[4][0] = new TempPiece();
        tempState[5][0] = new TempPiece();
        tempState[6][0] = new TempPiece();
        tempState[7][0] = new TempPiece();

        // Set the starting piece layout of the board
        board.setPieceStartingLayout(tempState);

        // Add a random object
        board.addPieceToTile(new BoardIndex(2, 2), new TempPiece());

        // Testing showValidMovesWithHighlight
        ArrayList<BoardIndex> tempHighlightedPos = new ArrayList<BoardIndex>();

        tempHighlightedPos.add(new BoardIndex(2, 0));
        tempHighlightedPos.add(new BoardIndex(2, 1));
        tempHighlightedPos.add(new BoardIndex(2, 3));
        tempHighlightedPos.add(new BoardIndex(2, 4));
        tempHighlightedPos.add(new BoardIndex(3, 2));
        tempHighlightedPos.add(new BoardIndex(4, 2));
        tempHighlightedPos.add(new BoardIndex(1, 2));
        tempHighlightedPos.add(new BoardIndex(0, 2));

        board.highlightAssociatedTiles(tempHighlightedPos);

        myScene = new Scene(board);

        window.setScene(myScene);
        window.show();
    }

    @Override
    public void handle(Event event) {  // Handles events from BoardGameGridPane

        if (event.getSource() instanceof Rectangle) {
            System.out.println("Tile clicked");

            Shape clickedTile = (Rectangle) event.getSource();
            System.out.println(new BoardIndex(board.getColumnIndex(clickedTile), board.getRowIndex(clickedTile)));
        } else if (event.getSource() instanceof ImageView) {
            // Reset the highlighted tiles
            ImageView clickedImgView = (ImageView) event.getSource();
            board.resetHighlightedTiles();
        }
    }
}


