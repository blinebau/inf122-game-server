package TicTacToe;

import BoardServer.BoardClient;
import app.controller.BoardGameController;
import app.model.BoardIndex;
import app.model.GameState;
import app.model.Move;
import app.model.Piece;
import app.view.GameGUI;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Created by Sophia on 3/12/2016.
 */
public class TTTController extends BoardGameController {

    private final Image mortalKombatX = new Image("TicTacToe/MortalKombatX.jpg");
    private final Image mortalKombatO = new Image("TicTacToe/MortalKombatO.jpg");

    private Piece X = new TTTPiece(mortalKombatX);
    private Piece O = new TTTPiece(mortalKombatO);

    private String playerStatus;

//    private GameState gameState = new GameState(3, 3);
    TTTGUI gameGUI;
//    TTGUI gameGUI;

    public TTTGUI getGameGUI() {
        return gameGUI;
    }

//    public TTGUI getGameGUI() {
//        return gameGUI;
//    }

    public TTTController(BoardClient client){
        super(client);
        state = new GameState(3,3);

        gameGUI = new TTTGUI(this, state.getBoard());
//        gui = gameGUI;
        playerStatus = client.getPlayerStatus();
        myScene = new Scene(gameGUI);

//        gameGUI = new TTGUI(client.getPlayerStatus(), client);
//        gameGUI = new TTTGUI();
    }

    public void tileSelected(BoardIndex pos) {
        if(playerStatus == "Player 1" && client.myTurn) {
            state.putPiece(X, pos);
            makeMove(pos);
        } else if(playerStatus != "Player 1" && !client.myTurn){
            state.putPiece(O, pos);
            makeMove(pos);
        }
    } // Called by GUI component
    // Used by all of the games to complete a move
    // - Tic-Tac-Toe (used to immediately make a move)
    // - Chess / Checkers (used after a piece is currently selected)

    public void moveReceived(Move move) {
        if(playerStatus == "Player 1") {
            state.putPiece(O, move.getDest());
            updateBoard(move);
        } else {
            state.putPiece(X, move.getDest());
            updateBoard(move);
        }
    } // Called by BoardClient when a Move is received from the opponent.

    protected void makeMove(BoardIndex pos){
        Move move = new Move(pos);
        client.sendMessage(move);
    }

    public void updateBoard(Move m) {
        //gameGUI.updateBoard(m);
        Piece p;
        if(playerStatus == "Player 1") {
            p = O;
        } else {
            p = X;
        }
        gameGUI.updateGameBoard(m, p);
    }

    public void start(Stage s) {

    }
}
