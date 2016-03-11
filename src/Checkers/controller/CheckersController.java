package Checkers.controller;
import java.util.ArrayList;
import java.util.List;

import BoardServer.BoardClient;
import Checkers.model.CheckerPiece;
import app.controller.BoardGameController;
import app.model.*;
import javafx.stage.Stage;



/**
 * Created by Roy on 3/8/16.
 */

/*
 *  Black goes first.
 */
public class CheckersController extends BoardGameController{
	
	public enum PieceColor { WHITE, BLACK }
	
	private final PieceColor MY_COLOR;
	private final PieceColor OPP_COLOR;
	
    private Piece pieceSelected;
    private List<CheckerPiece> myPieces;
    private List<CheckerPiece> oppPieces;

    public CheckersController(boolean isFirst, BoardClient c) {
    	
    	super(c);
    	
    	if(isFirst) {
    		MY_COLOR = PieceColor.BLACK;
    		OPP_COLOR = PieceColor.WHITE;
    	} else {
    		MY_COLOR = PieceColor.WHITE;
    		OPP_COLOR = PieceColor.BLACK;
    	}
    	
    	initStateAndLists();
    	// TODO: initialize GUI
    	initGUI();
    }
    
    private void initStateAndLists() {
    	state = new GameState(8, 8);
    	myPieces = new ArrayList<>();
    	oppPieces = new ArrayList<>();
    	CheckerPiece p;
    	
    	// Initializing my pieces
    	int col, row;
    	for(int i = 0; i < 12; ++i) {
    		p = new CheckerPiece(MY_COLOR);
    		myPieces.add(p);
    		// Placing piece in state.
    		row = 7 - i/4;
    		col = row%2 == 0 ? (2*i+1)%8 : (2*i)%8;
    		state.putPiece(p, col, row);
    	}
    	
    	// Initializing opponent's pieces
    	for(int i = 0; i < 12; ++i) {
    		p = new CheckerPiece(OPP_COLOR);
    		oppPieces.add(p);
    		// Placing piece in state.
    		row = i/4;
    		col = row%2 == 0 ? (2*i+1)%8 : (2*i)%8;
    		state.putPiece(p, col, row);
    	}
    }
    
    private void initGUI() {
    	// TODO
    }
    
    private Piece selectPiece(BoardIndex bi) {
        //TODO

        return null;
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
