package TicTacToe;

import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;

/**
 * Created by Sophia on 3/4/2016.
 */
public class TTLogic extends StackPane {
    private boolean playable = true;
    private boolean firstPlayerTurn = true;
    private IndTile[][] board;

    public TTLogic(IndTile[][] newBoard) {
        board = newBoard;
    }
    public boolean getFirstPlayerTurn() {
        return firstPlayerTurn;
    }

    public void setFirstPlayerTurn(boolean newBoolean) {
        firstPlayerTurn = newBoolean;
    }
}
