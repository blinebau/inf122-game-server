import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Created by Sophia on 3/4/2016.
 */
public class TTGUI extends Application {
    private Pane root = new Pane();
    private IndTile[][] board = new IndTile[3][3];

    /**
     * Creates the board
     * @return
     */
    private Parent fillBoard() {
        root.setPrefSize(600,600);

        // creates the 3 x 3 board
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                IndTile tile = new IndTile();
                tile.setTranslateX(j * 200);
                tile.setTranslateY(i * 200);

                root.getChildren().add(tile);

                board[j][i] = tile;
            }
        }

        return root;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(fillBoard()));
        primaryStage.show();
        passBoardToLogic();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void passBoardToLogic() {
        TTLogic logic = new TTLogic(board);
    }

}
