package Checkers.controller;
import java.util.ArrayList;
import java.util.List;
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

    public CheckersController(boolean isFirst) {
    	
    	if(isFirst) {
    		MY_COLOR = PieceColor.BLACK;
    		OPP_COLOR = PieceColor.WHITE;
    	} else {
    		MY_COLOR = PieceColor.WHITE;
    		OPP_COLOR = PieceColor.BLACK;
    	}
    	
    	initStateAndLists();
    }
    
    private void initStateAndLists() {
    	state = new GameState(8, 8);
    	myPieces = new ArrayList<>();
    	oppPieces = new ArrayList<>();
    	CheckerPiece p;
    	for(int i = 0; i < 12; ++i) {
    		p = new CheckerPiece(MY_COLOR);
    		myPieces.add(p);
    		// TODO: placing all pieces in place in state.
    		
    	}
    	
    	for(int i = 0; i < 12; ++i) {
    		p = new CheckerPiece(OPP_COLOR);
    		myPieces.add(p);
    		// TODO: placing all pieces in place in state.
    		
    	}
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
