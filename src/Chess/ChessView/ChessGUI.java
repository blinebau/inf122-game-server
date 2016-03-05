package Chess.ChessView;

import Chess.Pieces.TempChessPiece;
import GeneralGameBoard.BoardGameGridPane;
import GeneralGameBoard.BoardIndex;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

/**
 * Created by Luke on 3/5/2016.
 */
public class ChessGUI extends Application implements EventHandler<MouseEvent> {

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
        board = new BoardGameGridPane(8, 8, 2, 2, 50, Color.AQUA, this);


        TempChessPiece[][] tempState = new TempChessPiece[8][8];

        tempState[0][0] = new TempChessPiece();
        tempState[1][0] = new TempChessPiece();
        tempState[2][0] = new TempChessPiece();
        tempState[3][0] = new TempChessPiece();
        tempState[4][0] = new TempChessPiece();
        tempState[5][0] = new TempChessPiece();
        tempState[6][0] = new TempChessPiece();
        tempState[7][0] = new TempChessPiece();

        // Set the starting piece layout of the board
        board.setBoardObjectStartingLayout(tempState);

        // Add a random object
        board.addBoardObjectToTile(new BoardIndex(2, 2), new TempChessPiece());

        myScene = new Scene(board);

        window.setScene(myScene);
        window.show();
    }

    @Override
    public void handle(MouseEvent event) {  // Handles mouse events from BoardGameGridPane
        //System.out.println("Mouse Event occurred");

        if (event.getSource() instanceof Rectangle) {
            System.out.println("Tile clicked");

            Shape clickedTile = (Rectangle) event.getSource();
            System.out.println(new BoardIndex(board.getColumnIndex(clickedTile), board.getRowIndex(clickedTile)));

        } else if (event.getSource() instanceof ImageView) {
            // Reset the tile that is clicked
            ImageView clickedImgView = (ImageView) event.getSource();
            board.resetTile(new BoardIndex(board.getColumnIndex(clickedImgView), board.getRowIndex(clickedImgView)));
        }
    }
}
