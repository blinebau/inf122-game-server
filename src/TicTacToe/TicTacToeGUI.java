import java.util.ArrayList;
import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


public class TicTacToeGUI extends Application {

    private Tile[][] board = new Tile[3][3];
    private List<TicTacToeLogic> logics = new ArrayList<>();

    private Pane root = new Pane();

    private Parent createContent() {
        root.setPrefSize(600, 600);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Tile tile = new Tile();
                tile.setTranslateX(j * 200);
                tile.setTranslateY(i * 200);

                root.getChildren().add(tile);

                board[j][i] = tile;
            }
        }

        // horizontal
        for (int y = 0; y < 3; y++) {
            logics.add(new TicTacToeLogic(board[0][y], board[1][y], board[2][y]));
        }

        // vertical
        for (int x = 0; x < 3; x++) {
            logics.add(new TicTacToeLogic(board[x][0], board[x][1], board[x][2]));
        }

        // diagonals
        logics.add(new TicTacToeLogic(board[0][0], board[1][1], board[2][2]));
        logics.add(new TicTacToeLogic(board[2][0], board[1][1], board[0][2]));

        return root;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}