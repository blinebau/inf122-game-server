package Checkers.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PrimitiveIterator.OfDouble;

import BoardServer.BoardClient;
import Checkers.model.CheckerPiece;
import Checkers.model.CheckersMove;
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
	
    private BoardIndex pieceSelected;
    private CheckersMove lastMove;
    private List<BoardIndex> myPieces;
    private List<BoardIndex> oppPieces;
    
    private boolean isMyTurn;

    public CheckersController(boolean isFirst, BoardClient c) {
    	
    	super(c);
    	
    	if(isFirst) {
    		isMyTurn = true;
    		MY_COLOR = PieceColor.BLACK;
    		OPP_COLOR = PieceColor.WHITE;
    	} else {
    		isMyTurn = false;
    		MY_COLOR = PieceColor.WHITE;
    		OPP_COLOR = PieceColor.BLACK;
    	}
    	pieceSelected = null;
    	
    	initStateAndLists();
    	// TODO: initialize GUI
    	initGUI();
    }
    
    private void initStateAndLists() {
    	state = new GameState(8, 8);
    	myPieces = new ArrayList<>();
    	oppPieces = new ArrayList<>();
    	CheckerPiece p;
    	BoardIndex index;
    	
    	// Initializing my pieces
    	int col, row;
    	for(int i = 0; i < 12; ++i) {
    		p = new CheckerPiece(MY_COLOR);
    		row = 7 - i/4;
    		col = row%2 == 0 ? (2*i+1)%8 : (2*i)%8;
    		index = new BoardIndex(col, row);
    		myPieces.add(index);
    		// Placing piece in state.
    		state.putPiece(p, index);
    	}
    	
    	// Initializing opponent's pieces
    	for(int i = 0; i < 12; ++i) {
    		p = new CheckerPiece(OPP_COLOR);
    		row = i/4;
    		col = row%2 == 0 ? (2*i+1)%8 : (2*i)%8;
    		index = new BoardIndex(col, row);
    		oppPieces.add(index);
    		// Placing piece in state.
    		state.putPiece(p, index);
    	}
    }
    
    private void initGUI() {
    	// TODO
    }
    
    @Override
    public void tileSelected(BoardIndex pos) {
    	
    	if(!isMyTurn)
    		return;
    	
    	CheckerPiece piece = (CheckerPiece)state.getIndex(pos);
    	
    	// Clicked on empty space
    	if(pieceSelected == null && piece == null)
    		return;
    	
    	
    	// A piece is selected
    	if(piece != null) {
    		
    		// Change of selection
    		if(pieceSelected != null) {
    			// TODO: clear highlight of the original selected piece in GUI
    			
    			closeCaptureCombo();
    		}
    		
    		pieceSelected = pos;
    		
    		// Generate valid moves for the selected piece
    		generateValidMove(pos);
    		
    		// TODO: update GUI view to highlight the selected and show the valid moves
    		
    		return;
    	}
    	
    	// Clicked on empty space. Clear selection
    	if(!validMoves.contains(pos)) {
    		
    		// TODO: clear highlight of the original selected piece in GUI
    		
    		closeCaptureCombo();
    		
    		pieceSelected = null;
    		return;
    	}
    	
    	
    	// [Valid move]:
    	
    	// TODO: clear highlighting of selected piece in GUI
    	
    	// TODO: check if it is a capture move
    	// if not:
    	
    		// TODO: make move
    		// TODO: set isTurnOver to true in Move object, set this.isMyTurn to false
    		
    	// else:
    		
    		// TODO: make move
    		
    		// TODO: check if another capture can be performed
    		// if yes:
    			// TODO: set isTurnOver to false in Move object
    			// Generate valid moves for the selected piece
    			generateValidMove(pos);
    			// TODO: update GUI view to highlight the selected and show the valid moves
    		
    		//if no:
    			// TODO: set isTurnOver to true in Move object, set this.isMyTurn to false
    		
    	// TODO: send move to the server
    		
    	// TODO: Check if the piece is normal and it has reached opponent's side, set piece's king to true;
    		
    	pieceSelected = null;
    	
    	// TODO: check winning condition: no more valid moves for each piece in oppPieces
    }
    
    private void generateValidMove(BoardIndex pos) {
    	
    	validMoves = new ArrayList<>();
    	// TODO
    	
    }
    
    private void closeCaptureCombo() {
		
		if(!lastMove.isTurnOver()) {
			CheckersMove move = new CheckersMove(lastMove.getDest(), lastMove.getDest(), true);
			lastMove = move;
			// TODO: send move to server
			
		}
    }

	@Override
	public void moveReveived(Move m) {
		CheckersMove move = (CheckersMove)m;
		
		if(!move.getSource().equals(move.getDest())) {
			pieceSelected = move.getSource();
			makeMove(move.getDest());
			pieceSelected = null;
		}
		
		isMyTurn = move.isTurnOver();
		
		if(isMyTurn) {
			// TODO: check losing condition: no more valid moves for each piece in myPieces
		}
	}

    @Override
    protected void makeMove(BoardIndex pos) {
    	// TODO
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

	public void updateBoard(Move move){

	}

//    @Override
//    public void start(Stage primaryStage) throws Exception {
//
//    }

}
