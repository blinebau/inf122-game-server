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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

/**
 * TTTController: Logic behind TicTacToe game, extends BoardGameController
 * Created by Sophia on 3/12/2016.
 */
public class TTTController extends BoardGameController {

    private final Image MORTALKOMBATX = new Image("TicTacToe/MortalKombatX.jpg");
    private final Image MORTALKOMBATO = new Image("TicTacToe/MortalKombatO.jpg");

    private TTTPiece X = new TTTPiece(MORTALKOMBATX);
    private TTTPiece O = new TTTPiece(MORTALKOMBATO);

    private boolean host;
    private boolean myTurn;
    private boolean someoneWon = false;

    private List<WinCombo> possibleWins = new ArrayList<>();

    TTTGUI gameGUI;

    /**
     * getGameGUI(): returns attribute gameGUI
     * @return TTTGUI
     */
    public TTTGUI getGameGUI() {
        return gameGUI;
    }

    /**
     * Constructor that takes in a BoardClient object
     * @param client
     */
    public TTTController(BoardClient client){
        super(client);
        state = new GameState(3,3);

        O.setShape("O");
        X.setShape("X");

        //initialize with pieces
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                state.putPiece(new TTTPiece(), new BoardIndex(i,j));
            }
        }

        host = client.getHostStatus();
        if(host) {
            myTurn = true;
        } else {
            myTurn = false;
        }

        initGuiAndScene(state.getBoard());

        //populates the attribute possibleWins with all the possible winning combinations
        //horizontal
        for(int y = 0; y < 3; y++) {
            possibleWins.add(new WinCombo((TTTPiece) state.getPiece(new BoardIndex(0, y)),
                    (TTTPiece)state.getPiece(new BoardIndex(1, y)),
                    (TTTPiece)state.getPiece(new BoardIndex(2, y))));
        }

        //vertical
        for(int x = 0; x < 3; x++) {
            possibleWins.add(new WinCombo((TTTPiece)state.getPiece(new BoardIndex(x, 0)),
                    (TTTPiece)state.getPiece(new BoardIndex(x, 1)),
                    (TTTPiece)state.getPiece(new BoardIndex(x, 2))));
        }

        //diagonals
        possibleWins.add(new WinCombo((TTTPiece)state.getPiece(new BoardIndex(0, 0)),
                (TTTPiece)state.getPiece(new BoardIndex(1, 1)),
                (TTTPiece)state.getPiece(new BoardIndex(2, 2))));
        possibleWins.add(new WinCombo((TTTPiece)state.getPiece(new BoardIndex(2, 0)),
                (TTTPiece)state.getPiece(new BoardIndex(1, 1)),
                (TTTPiece)state.getPiece(new BoardIndex(0, 2))));
    }

    @Override
    protected void initGuiAndScene(Piece[][] game2DArray) {
        gameGUI = new TTTGUI(this, game2DArray);
        myScene = new Scene(gameGUI);
    }

    /**
     * tileSelected(): updates state based on player's move
     * @param pos
     */
    public void tileSelected(BoardIndex pos) {
        if(myTurn) {
            if(host) {
                state.getPiece(pos).setImage(MORTALKOMBATX);
                ((TTTPiece)state.getPiece(pos)).setShape("X");
            } else {
                state.getPiece(pos).setImage(MORTALKOMBATO);
                ((TTTPiece)state.getPiece(pos)).setShape("O");
            }
            makeMove(pos);
        }
    }

    /**
     * moveReceived(): updates state based on received move
     * @param move
     */
    public void moveReceived(Move move) {
        if(host) {
            state.getPiece(move.getDest()).setImage(MORTALKOMBATO);
            ((TTTPiece)state.getPiece(move.getDest())).setShape("O");
        } else {
            state.getPiece(move.getDest()).setImage(MORTALKOMBATX);
            ((TTTPiece)state.getPiece(move.getDest())).setShape("X");
        }
        updateBoard(move);
        if(someoneWon) {
            myTurn = false;
        } else {
            myTurn = true;
        }
    }

    /**
     * makeMove(): sends move to BoardClient
     * @param pos
     */
    protected void makeMove(BoardIndex pos){
        Move move = new Move(pos);
        client.sendMessage(move);
        updateBoard(move);
        myTurn = false;
    }

    /**
     * updateBoard(): updates game gui of the client
     * @param m
     */
    public void updateBoard(Move m) {
        Piece p;
        if(host) {
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

    /**
     * checkWin(): checks if one of the possible winning combos has been completed
     */
    private void checkWin() {
        for(WinCombo combo: possibleWins) {
            if(combo.isComplete()) {
                someoneWon = true;
                playWinAnimation();
            }
        }
    }

    /**
     * playWinAnimation(): plays the winning animation
     */
    private void playWinAnimation() {
        String endingStatus;
        if(!myTurn)
        {
            endingStatus = "You lost... :(";
        }
        else
        {
            endingStatus = "You won! :D";
        }

        Image fatality = new Image("TicTacToe/mortalKombatFatality.jpg");
        ImageView fatalityIV = new ImageView(fatality);

        Stage stage = client.getClientGUI().getStage();
        Window owner = myScene.getWindow();
        Alert gameConfirm = new Alert(Alert.AlertType.CONFIRMATION, "");
        gameConfirm.initOwner(owner);
        gameConfirm.initStyle(StageStyle.DECORATED);
        gameConfirm.getDialogPane().setGraphic(fatalityIV);
        gameConfirm.getDialogPane().setHeaderText(endingStatus);
        gameConfirm.setTitle("Game over");
        gameConfirm.getDialogPane().setContentText("Select 'Ok' to return to the Game Lobby");
        gameConfirm.getDialogPane().getButtonTypes().remove(ButtonType.CANCEL);
        gameConfirm.showAndWait().ifPresent(result -> stage.setScene(client.getClientGUI().drawTitleMenu()));
    }

}
