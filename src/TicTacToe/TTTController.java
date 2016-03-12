package TicTacToe;

import BoardServer.BoardClient;
import app.controller.BoardGameController;
import app.model.BoardIndex;
import app.model.GameState;
import app.model.Move;
import app.model.Piece;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Created by Sophia on 3/12/2016.
 */
public class TTTController extends BoardGameController {

    private final Image mortalKombatX = new Image("MortalKombatX");
    private final Image mortalKombatO = new Image("MortalKombatO");

    private Piece X = new TTTPiece(mortalKombatX);
    private Piece O = new TTTPiece(mortalKombatO);

    private GameState gameState = new GameState(3, 3);

    public TTGUI getGameGUI() {
        return gameGUI;
    }

    TTGUI gameGUI;

    public TTTController(BoardClient client){
        super(client);
        gameGUI = new TTGUI(client.getPlayerStatus(), client);
    }

    public void tileSelected(BoardIndex pos) {} // Called by GUI component
    // Used by all of the games to complete a move
    // - Tic-Tac-Toe (used to immediately make a move)
    // - Chess / Checkers (used after a piece is currently selected)

    public void moveReceived(Move move) {} // Called by BoardClient when a Move is received from the opponent.

    protected void makeMove(BoardIndex pos){}

    protected boolean validateMove(BoardIndex pos){return true;}

    protected void updateModel(){}

    protected void updateView(){}

    public void updateBoard(Move m) {gameGUI.updateBoard(m);}

    public void start(Stage s) {

    }

}
