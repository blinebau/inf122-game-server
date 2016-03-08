package Chess.controller;

import BoardServer.BoardClient;
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

    BoardGameGridPane board;
    app.model.Piece[][] game2DArray;
    ChessGUI chessGUI;

    public ChessController() {};

    public ChessController(BoardClient client) {
        super(client);
        System.out.println("constructed");
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
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


        // Set up the encompassing gui, which uses the array to set up the board
        chessGUI = new ChessGUI(this, game2DArray);

        myScene = new Scene(chessGUI);

        window.setScene(myScene);
        window.show();

        // Test reseting a tile after the scene is shown
//        chessGUI.getBoard().resetTile(new BoardIndex(0,0));
    }

    public void tileSelected(BoardIndex pos) {
        System.out.println("Tile Selected" + pos.toString());
    }

    public void pieceSelected(BoardIndex pos) {
        System.out.println("Piece Selected" + pos.toString());
    }

    public void makeMove(BoardIndex pos) { // TODO

    }

    public boolean validateMove(BoardIndex pos) { // TODO
        return false;
    }

    public void updateModel() { // TODO

    }

    public void updateView() { // TODO

    }
}
