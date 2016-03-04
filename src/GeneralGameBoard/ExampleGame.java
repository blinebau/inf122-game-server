package GeneralGameBoard;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * Created by jgreene on 3/2/16.
 */
public class ExampleGame extends Application implements EventHandler<MouseEvent> {

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
        board = new BoardGameGridPane(8, 8, 2, 2, this);

        myScene = new Scene(board);

        window.setScene(myScene);
        window.show();
    }

    @Override
    public void handle(MouseEvent event) {  // Handles mouse events from BoardGameGridPane
        //System.out.println("Mouse Event occurred");

        // Demo
        System.out.println(((IndexImageView) event.getSource()).getColRowIndex());
        //board.replaceTileWithImage(2,2, new Image("GeneralGameBoard/Pawn_2.png")); // Example of one of the gridpane's nodes being updated
    }

}


