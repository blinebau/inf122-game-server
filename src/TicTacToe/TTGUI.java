package TicTacToe;

import BoardServer.BoardClient;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.Move;

/**
 * Created by Sophia on 3/4/2016.
 */
public class TTGUI {
    private Pane root = new Pane();
    private Scene tttscene;
    private BoardClient client;
    private IndTile[][] board = new IndTile[3][3];

    /**
     * Creates the board
     * @return
     */

    public TTGUI(String message, BoardClient client)
    {
        this.client = client;
        tttscene = new Scene(fillBoard(message));
    }

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
        return root;
    }

    public void updateBoard(Move move)
    {
        IndTile tile = board[move.getIndex().getColumnIndex()][move.getIndex().getRowIndex()];
        tile.drawOpponent();
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
