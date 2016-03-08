package Checkers.controller;
import Checkers.model.CheckerPiece;
import app.controller.BoardGameController;
import app.model.*;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Created by Roy on 3/8/16.
 */
public class CheckersController extends BoardGameController{
    private Piece pieceSelected;
    private ArrayList<Piece> playerBlack;
    private ArrayList<Piece> PlayerWhite;
    private ArrayList<BoardIndex> validMovies;


    private Piece selectPiece(BoardIndex bi) {
        //TODO

        Piece p = new CheckerPiece();

        return p;
    }

    public void pieceSelected(BoardIndex bi) {
        //TODO
    }

    @Override
    public void tileSelected(BoardIndex pos) {

    }

    @Override
    protected void makeMove(BoardIndex pos) {

    }

    @Override
    protected boolean validateMove(BoardIndex pos) {
        return false;
    }

    @Override
    protected void updateModel() {

    }

    @Override
    protected void updateView() {

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }
}
