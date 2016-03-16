package TicTacToe;

import BoardServer.BoardClient;
import app.controller.BoardGameController;
import app.model.BoardIndex;
import app.model.GameState;
import app.model.Move;
import app.model.Piece;
import app.view.GameGUI;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sophia on 3/12/2016.
 */
public class TTTController extends BoardGameController {

    private final Image mortalKombatX = new Image("TicTacToe/MortalKombatX.jpg");
    private final Image mortalKombatO = new Image("TicTacToe/MortalKombatO.jpg");

    private Piece X = new TTTPiece(mortalKombatX);
    private Piece O = new TTTPiece(mortalKombatO);

    private String playerStatus;
    private boolean myTurn;

    private List<WinCombo> possibleWins = new ArrayList<>();

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

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                state.putPiece(new TTTPiece(), new BoardIndex(i,j));
            }
        }

        gameGUI = new TTTGUI(this, state.getBoard());
//        gui = gameGUI;
        playerStatus = client.getPlayerStatus();

        myTurn = client.myTurn;
        myScene = new Scene(gameGUI);

        //horizontal
        for(int y = 0; y < 3; y++) {
            possibleWins.add(new WinCombo(state.getPiece(new BoardIndex(0, y)),
                    state.getPiece(new BoardIndex(1, y)),
                    state.getPiece(new BoardIndex(2, y))));
        }

        //vertical
        for(int x = 0; x < 3; x++) {
            possibleWins.add(new WinCombo(state.getPiece(new BoardIndex(x, 0)),
                    state.getPiece(new BoardIndex(x, 1)),
                    state.getPiece(new BoardIndex(x, 2))));
        }

        //diagonals
        possibleWins.add(new WinCombo(state.getPiece(new BoardIndex(0, 0)),
                state.getPiece(new BoardIndex(1, 1)),
                state.getPiece(new BoardIndex(2, 2))));
        possibleWins.add(new WinCombo(state.getPiece(new BoardIndex(2, 0)),
                state.getPiece(new BoardIndex(1, 1)),
                state.getPiece(new BoardIndex(0, 2))));

//        gameGUI = new TTGUI(client.getPlayerStatus(), client);
//        gameGUI = new TTTGUI();
    }

    public void tileSelected(BoardIndex pos) {
        if(myTurn) {
            state.removePiece(pos);
            if(playerStatus.equals("Player 1")) {
                state.putPiece(X, pos);
            } else {
                state.putPiece(O, pos);
            }
            makeMove(pos);
        }
    } // Called by GUI component
    // Used by all of the games to complete a move
    // - Tic-Tac-Toe (used to immediately make a move)
    // - Chess / Checkers (used after a piece is currently selected)

    public void moveReceived(Move move) {
        state.removePiece(new BoardIndex(move.getDest().getColumnIndex(), move.getDest().getRowIndex()));
        if(playerStatus.equals("Player 1")) {
            state.putPiece(O, move.getDest());
        } else {
            state.putPiece(X, move.getDest());
        }
        updateBoard(move);
        myTurn = true;
    } // Called by BoardClient when a Move is received from the opponent.

    protected void makeMove(BoardIndex pos){
        Move move = new Move(pos);
        client.sendMessage(move);
        updateBoard(move);
        myTurn = false;
    }

    public void updateBoard(Move m) {
        //gameGUI.updateBoard(m);
        Piece p;
        if(playerStatus.equals("Player 1")) {
            if(myTurn) {
                p = X;
            } else {
                p = O;
            }
        } else {
            if (myTurn) {
                p = O;
            } else {
                p = X;
            }
        }
        gameGUI.updateGameBoard(m, p);
        checkWin();
    }

    private void checkWin() {
        for(WinCombo combo: possibleWins) {
            if(combo.isComplete()) {
                playWinAnimation(combo);
            }
        }
    }

    private void playWinAnimation(WinCombo combo) {
        Line line = new Line();
        line.setStartX(combo.getTile(0).getImage().getWidth()/2);//.getCenterX());
        line.setStartY(combo.getTile(0).getImage().getHeight()/2);//getCenterY());
        line.setEndX(combo.getTile(0).getImage().getWidth()/2);//getCenterX());
        line.setEndY(combo.getTile(0).getImage().getHeight()/2);//getCenterY());

        gameGUI.getBoard().getChildren().add(line);

        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1),
                new KeyValue(line.endXProperty(), combo.getTile(2).getImage().getWidth()/2),//getCenterX()),
                new KeyValue(line.endYProperty(), combo.getTile(2).getImage().getHeight()/2)));//getCenterY())));
        timeline.play();

//        String endingStatus;
//        if(client.myTurn)
//        {
//            endingStatus = "You lost";
//        }
//        else
//        {
//            endingStatus = "You won!";
//        }
//
//        Stage stage = (Stage) root.getScene().getWindow();
//        Window owner = tttscene.getWindow();
//        Alert gameConfirm = new Alert(Alert.AlertType.CONFIRMATION, "");
//        gameConfirm.initOwner(owner);
//        gameConfirm.initStyle(StageStyle.DECORATED);
//        gameConfirm.getDialogPane().setHeaderText(endingStatus);
//        gameConfirm.setTitle("Game over");
//        gameConfirm.getDialogPane().setContentText("Select 'Ok' to return to the Game Lobby");
//        gameConfirm.getDialogPane().getButtonTypes().remove(ButtonType.CANCEL);
////        client(null);
//        gameConfirm.showAndWait().ifPresent(result -> stage.setScene(client.getClientGUI().drawTitleMenu()));
    }

    public void start(Stage s) {

    }
}
