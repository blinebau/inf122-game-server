package Chess.controller;

import BoardServer.BoardClient;
import Chess.model.ChessMove;
import Chess.model.ChessPiece;
import Chess.model.Game;
import Chess.view.Display;
import Chess.view.*;
import app.controller.BoardGameController;
import app.model.BoardIndex;
import app.model.Move;
import app.model.Piece;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Luke on 3/5/2016.
 * The Controller for Chess
 */
public class ChessController extends BoardGameController {


    ChessGUI gui;
    Game chessGame;
    BoardIndex moveSource;
    BoardIndex moveDestination;
    String playerStatus;


    public ChessController(BoardClient client) {
        super(client);
        this.playerStatus = client.getPlayerStatus();
        constructGame();
        System.out.println("constructed");
    }

//    public static void main(String[] args) {
//        launch(args);
//    }

    private void constructGame(){
        // Set up the game's state
        app.model.Piece[][] game2DArray = new Piece[8][8];

        for(int i = 0; i < 8; i++) {
            game2DArray[i][1] = new PawnPiece("Black");
        }
        for(int i = 0; i < 8; i++){
            game2DArray[i][6] = new PawnPiece("White");
        }

        game2DArray[1][0] = new KnightPiece("Black");
        game2DArray[6][0] = new KnightPiece("Black");

        game2DArray[2][0] = new BishopPiece("Black");
        game2DArray[5][0] = new BishopPiece("Black");

        game2DArray[1][7] = new KnightPiece("White");
        game2DArray[6][7] = new KnightPiece("White");

        game2DArray[2][7] = new BishopPiece("White");
        game2DArray[5][7] = new BishopPiece("White");

        game2DArray[4][7] = new KingPiece("White");
        game2DArray[4][0] = new KingPiece("Black");

        game2DArray[3][7] = new QueenPiece("White");
        game2DArray[3][0] = new QueenPiece("Black");

        game2DArray[0][7] = new RookPiece("White");
        game2DArray[7][7] = new RookPiece("White");

        game2DArray[7][0] = new RookPiece("Black");
        game2DArray[0][0] = new RookPiece("Black");


        // Set up the encompassing gui, which uses the array to set up the board
        gui = new ChessGUI(this, game2DArray);
        if (playerStatus.equals("Player 1")) {
            gui.getGameStatusText().setText("Your Turn");
        } else {
            gui.getGameStatusText().setText("Opponent's Turn");
            gui.getBoard().disable();
        }
        chessGame = new Game();
        myScene = new Scene(gui);

    }


    public void tileSelected(BoardIndex pos) {

        if (moveSource != null) {
            makeMove(pos);
        }
    }

    public void pieceSelected(BoardIndex pos) {

        makeMove(pos);
    }


    // Take the boardIndex and change it to a chess location (4,6) = e2
    private String boardIndexToChessTile(BoardIndex pos){
        String chessLocation = "";
        // Column
        switch (pos.getColumnIndex()){
            case 0:
                chessLocation += "a";
                break;
            case 1:
                chessLocation += "b";
                break;
            case 2:
                chessLocation += "c";
                break;
            case 3:
                chessLocation += "d";
                break;
            case 4:
                chessLocation += "e";
                break;
            case 5:
                chessLocation += "f";
                break;
            case 6:
                chessLocation += "g";
                break;
            case 7:
                chessLocation += "h";
                break;
            default:
                break;
        }

        //Row
        switch (pos.getRowIndex()){
            case 0:
                chessLocation += "8";
                break;
            case 1:
                chessLocation += "7";
                break;
            case 2:
                chessLocation += "6";
                break;
            case 3:
                chessLocation += "5";
                break;
            case 4:
                chessLocation += "4";
                break;
            case 5:
                chessLocation += "3";
                break;
            case 6:
                chessLocation += "2";
                break;
            case 7:
                chessLocation += "1";
                break;
            default:
                break;
        }
        return chessLocation;
    }

    public void makeMove(BoardIndex pos) {
        String chessLocation = boardIndexToChessTile(pos);
        System.out.println("Piece Selected:" + pos.toString()
                + " Location:" + chessLocation);

        if (moveSource == null){
            moveSource = pos;
            highlightValidMoveTiles();
//            chessGame.getBoard()[0][0].getPiece().canMoveTo()
//            gui.getBoard().highlightAssociatedTiles();
        } else if (moveDestination == null){
            moveDestination = pos;
            gui.getBoard().resetHighlightedTiles();
        }
        if(moveDestination != null) {
            makeChessMove(moveSource, moveDestination, false);
        }

    }

    private void highlightValidMoveTiles(){
        String chessLocation = boardIndexToChessTile(moveSource);
        int c = chessGame.fileToIndex(chessLocation.charAt(0));
        int r = 8 - Character.getNumericValue(chessLocation.charAt(1));
        ChessPiece piece = chessGame.getBoard()[r][c].getPiece();
        List<BoardIndex> validMoves = new ArrayList<>();
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if (piece.canMoveTo(chessGame.getBoard()[j][i])){
                    validMoves.add(new BoardIndex(i,j));
                }
            }
        }
        gui.getBoard().highlightAssociatedTiles(validMoves);
    }

    private void makeChessMove(BoardIndex moveSrc, BoardIndex moveDes, boolean fromServer){
        String chessSource = boardIndexToChessTile(moveSrc);
        String chessDestination = boardIndexToChessTile(moveDes);
        System.out.println("Make move: " + chessSource + " " + chessDestination);
        //TODO: deal with 3rd parameter which deals with promotions
        if(!chessGame.move(chessSource, chessDestination, null)){
            System.out.println("Illegal Move");
        } else {
            Display.showBoard(chessGame);
            Piece movePiece =  gui.copyOfPieceOnBoard(moveSrc);
            gui.updateGame2DArray(moveSrc, moveDes);
            gui.getBoard().resetTile(moveSrc);
            gui.getBoard().addPieceToTile(moveDes, movePiece);
            gui.getBoard().resetTile(moveSrc);
            if(!fromServer) {
                sendMoveToServer(moveSrc, moveDes);
            }
        }
        moveSource = null;
        moveDestination = null;
        if(chessGame.getWinner() != null){
            showGameOverScreen();
        }
    }

    private void showGameOverScreen(){
        String winner = chessGame.getWinner();

        Stage stage = (Stage) gui.getScene().getWindow();
        Window owner = gui.getScene().getWindow();
        Alert gameConfirm = new Alert(Alert.AlertType.CONFIRMATION, "");
        gameConfirm.initOwner(owner);
        gameConfirm.initStyle(StageStyle.DECORATED);
        gameConfirm.getDialogPane().setHeaderText(winner + " is the winner");
        gameConfirm.setTitle("Game over");
        gameConfirm.getDialogPane().setContentText("Select 'Ok' to return to the Game Lobby");
        gameConfirm.getDialogPane().getButtonTypes().remove(ButtonType.CANCEL);
        gameConfirm.showAndWait().ifPresent(result -> stage.setScene(client.getClientGUI().drawTitleMenu()));
    }

    private void sendMoveToServer(BoardIndex moveSrc, BoardIndex moveDes){
        Move move = new ChessMove(moveSrc, moveDes);
        client.sendMessage(move);
        // Opponent's Turn
        gui.getBoard().disable();
        gui.getGameStatusText().setText("Opponent's Turn");
    }

    @Override
    public void moveReceived(Move move){
        ChessMove chessMove = (ChessMove) move;
        makeChessMove(chessMove.getSource(), chessMove.getDestination(), true);
        // Player's Turn
        String inCheck = "";
        if (playerStatus.equals("Player 1")) {
            if(chessGame.whiteInCheck()){
                inCheck = ", you're in Check";
            }
        } else {
            if(chessGame.blackInCheck()){
                inCheck = ", you're in Check";
            }
        }
        gui.getBoard().activate();
        gui.getGameStatusText().setText("Your Turn" + inCheck);
    }
}
