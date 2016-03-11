package TicTacToe;

import BoardServer.BoardClient;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import app.model.Move;
import javafx.scene.shape.Line;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sophia on 3/4/2016.
 */
public class TTGUI {
    private static Pane root = new Pane();
    private Scene tttscene;
    private BoardClient client;
    private IndTile[][] board = new IndTile[3][3];
    static List<WinCombo> possibleWins = new ArrayList<>();
    static WinCombo winnersCombo;
    static boolean someoneWon = false;

    /**
     * Constructor that takes in a String and BoardClient
     * @param message
     * @param client
     */
    public TTGUI(String message, BoardClient client)
    {
        this.client = client;
        tttscene = new Scene(fillBoard(message));
    }

    /**
     * Creates and fills the board
     * @return
     */
    private Parent fillBoard(String message) {
        root.setPrefSize(600,600);

        // creates the 3 x 3 board
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                IndTile tile = new IndTile(i, j, message, client);
                tile.setTranslateX(j * 200);
                tile.setTranslateY(i * 200);

                root.getChildren().add(tile);

                board[j][i] = tile;
            }
        }

        // horizontal
        for (int y = 0; y < 3; y++) {
            possibleWins.add(new WinCombo(board[0][y], board[1][y], board[2][y]));
        }

        // vertical
        for (int x = 0; x < 3; x++) {
            possibleWins.add(new WinCombo(board[x][0], board[x][1], board[x][2]));
        }

        // diagonals
        possibleWins.add(new WinCombo(board[0][0], board[1][1], board[2][2]));
        possibleWins.add(new WinCombo(board[2][0], board[1][1], board[0][2]));

        return root;
    }

    /**
     * Updates board based on other player's move
     * @param move
     */
    public void updateBoard(Move move)
    {
        IndTile tile = board[move.getDest().getColumnIndex()][move.getDest().getRowIndex()];
        tile.drawOpponent();
        checkWin();
        if(someoneWon) {
            client.sendMessage("Congratulations, " + client.getPlayerStatus()+ " - " + client.getUsername()+ ", has lost. Good game!");
            client.myTurn = false;
        }
    }

    /**
     * Checks all possible combinations to win
     */
    public static void checkWin() {

        for (WinCombo combo : possibleWins) {
            if (combo.isComplete()) {
                playWinAnimation(combo);
                someoneWon = true;
                break;
            }
        }
    }

    /**
     * Displays the winning trio with a line through them
     * @param combo
     */
    private static void playWinAnimation(WinCombo combo) {
        Line line = new Line();
        line.setStartX(combo.getTile(0).getCenterX());
        line.setStartY(combo.getTile(0).getCenterY());
        line.setEndX(combo.getTile(0).getCenterX());
        line.setEndY(combo.getTile(0).getCenterY());

        root.getChildren().add(line);

        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1),
                new KeyValue(line.endXProperty(), combo.getTile(2).getCenterX()),
                new KeyValue(line.endYProperty(), combo.getTile(2).getCenterY())));
        timeline.play();
        winnersCombo = combo;
    }

    public void setScene(Scene scene)
    {
        tttscene = scene;
    }

    public Scene getScene()
    {
        return tttscene;
    }

//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        primaryStage.setScene(new Scene(fillBoard()));
//        //primaryStage.show();
//        //passBoardToLogic();
//    }

//    public static void main(String[] args) {
//        launch(args);
//    }

    public void passBoardToLogic() {
        TTLogic logic = new TTLogic(board);
    }

}
