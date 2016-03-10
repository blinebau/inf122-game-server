package Chess.controller;

import BoardServer.BoardClient;
import Chess.CMD.controller.Game;
import Chess.CMD.view.Display;
import Chess.model.*;
import Chess.view.ChessGUI;
import app.controller.BoardGameController;
import app.model.BoardIndex;
import app.model.Piece;
import app.view.BoardGameGridPane;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Luke on 3/5/2016.
 */
public class ChessController extends BoardGameController {

    Stage window;
    Scene myScene;

//    BoardGameGridPane board;
//    app.model.Piece[][] game2DArray;
    ChessGUI chessGUI;
    Game chessGame;
    BoardIndex moveSource;
    BoardIndex moveDestination;

    public ChessController() {};

    public ChessController(BoardClient client, Stage stage) {
        super(client, stage);
        System.out.println("constructed");

        setUpModelAndView();
    }

    public void setUpModelAndView() {
        window = stage;
        window.setTitle("Chess");

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

        // Set up the view by constructing the encompassing gui, which uses the array to set up the board
        chessGUI = new ChessGUI(this, game2DArray);

        myScene = new Scene(chessGUI);
        chessGame = new Game();

        window.setScene(myScene);
        window.show();
    }

    public void tileSelected(BoardIndex pos) {

        makeMove(pos);

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
        } else if (moveDestination == null){
            moveDestination = pos;
        }
        if(moveSource != null && moveDestination != null) {
            String chessSource = boardIndexToChessTile(moveSource);
            String chessDestination = boardIndexToChessTile(moveDestination);
            System.out.println("Make move: " + chessSource + " " + chessDestination);
            //TODO: deal with 3rd parameter which deals with promotions
            if(!chessGame.move(chessSource, chessDestination, null)){
                System.out.println("Illegal Move");
            } else {
                Display.showBoard(chessGame);
                Piece movePiece = chessGUI.copyOfPieceOnBoard(moveSource);
                chessGUI.updateGame2DArray(moveSource, moveDestination);
                chessGUI.getBoard().resetTile(moveDestination);
                chessGUI.getBoard().addPieceToTile(moveDestination, movePiece);
                chessGUI.getBoard().resetTile(moveSource);

            }
            moveSource = null;
            moveDestination = null;
        }

    }

    public boolean validateMove(BoardIndex pos) { // TODO
        return false;
    }

    public void updateModel() { // TODO

    }

    public void updateView() { // TODO

    }
}
