package Checkers.controller;
import java.util.ArrayList;
import java.util.List;
import BoardServer.BoardClient;
import Checkers.model.CheckerPiece;
import Checkers.model.CheckersMove;
import Checkers.view.CheckersGUI;
import app.controller.BoardGameController;
import app.model.*;
import javafx.scene.Scene;
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
	private final CheckersGUI gui;
	
    private BoardIndex pieceSelected;
    private CheckersMove lastMove;
    private List<BoardIndex> myPieces;
    private List<BoardIndex> oppPieces;
    private int[][] directions;
    private boolean isMyTurn;


	// ---------------------------------------------------------------// TESTING START
	public CheckersController() {
		if(true) {
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
    	initDir();
    	gui = new CheckersGUI(this, state.getBoard());
    	myScene = new Scene(gui);
    	if(isMyTurn)
    		gui.showMyTurn();

	}
	// ---------------------------------------------------------------// TESTING END
    public CheckersController(BoardClient c, boolean isFirst) {
    	
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
    	initDir();
    	
    	gui = new CheckersGUI(this, state.getBoard());
    	myScene = new Scene(gui);
    	if(isMyTurn)
    		gui.showMyTurn();
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
    
    private void initDir() {
    	directions = new int[4][2];
    	
    	directions[0][0] = -1;	directions[0][1] = -1;
    	directions[1][0] = 1;	directions[1][1] = -1;
    	directions[2][0] = -1;	directions[2][1] = 1;
    	directions[3][0] = 1;	directions[3][1] = 1;
    }
    
    // Called by GUI component
    @Override
    public void tileSelected(BoardIndex pos) {
    	
    	if(!isMyTurn || pieceSelected == null)
    		return;
    	
    	CheckerPiece piece = (CheckerPiece)state.getIndex(pos);
    	
    	// A piece is selected
    	if(piece != null) {
    		

    	}
    	
    	// Clicked on empty space. Clear selection
    	if(!validMoves.contains(pos)) {
    		
    		// Clear highlight
    		gui.clearHighlight();
    		closeCaptureCombo();
    		
    		pieceSelected = null;
    		return;
    	}
    	
    	
    	/*************** [Valid move] *****************/
    	CheckersMove move;
    	
    	// Clear highlight
    	gui.clearHighlight();
    	
    	// Make move
    	makeMove(pos);
    	
    	// Check if it is a capture move 						
    	// If not:
    	if(!isCapture(pieceSelected, pos)) {
    		// Set isTurnOver to true in Move object, set this.isMyTurn to false
    		move = new CheckersMove(pieceSelected, pos, true);
    		isMyTurn = false;
    		pieceSelected = null;
    		lastMove = move;
    	// else:
    	} else {
    		// Remove opponent's piece
    		removePieceBetween(pieceSelected, pos);
    		
    		// Generate valid moves for the selected piece
			generateValidMove(pos);
			
    		// Check if another capture can be performed
    		// if yes:
			if(canCapture(pos)) {
    			// Set isTurnOver to false in Move object
				move = new CheckersMove(pieceSelected, pos, false);
				lastMove = move;
				pieceSelected = pos;
    			// Update GUI view to highlight the selected and show the valid moves
	    		validMoves.add(pieceSelected);
	    		gui.hightlightTile(validMoves);
	    		validMoves.remove(validMoves.size()-1);
    		
    		//if no:
			} else {
    			// Set isTurnOver to true in Move object, set this.isMyTurn to false
				move = new CheckersMove(pieceSelected, pos, true);
	    		isMyTurn = false;				
				pieceSelected = null;
			}
    	}
    	// Send move to the server
//    	client.sendMessage(move);
    		
    	// Check if the piece can become the king
		if(canBecomeKing(pos)) {
			CheckerPiece p = (CheckerPiece) state.getIndex(pos);
			p.toKing();
		}
    	
    	// Check winning condition: no more valid moves for each piece in oppPieces
		if(isGameOver(true)) {
			// TODO: things to do when I win
			
			isMyTurn = false;
		}
    }
    
    public void pieceSelected(BoardIndex pos) {
    	if(!isMyTurn || oppPieces.contains(pos))
    		return;
    	
		if(pieceSelected != null) {
			// Clicked on the same piece as last time
			if(pos.equals(pieceSelected))
				return;
			// Change of selection
			// Clear highlight
			gui.clearHighlight();
			closeCaptureCombo();
		}
		
		pieceSelected = pos;
		
		// Generate valid moves for the selected piece
		generateValidMove(pos);
		
		// Update GUI view to highlight the selected and the valid moves
		validMoves.add(pieceSelected);
		gui.hightlightTile(validMoves);
		validMoves.remove(validMoves.size()-1);
    }
    
    
    private void generateValidMove(BoardIndex pos) {
    	
    	validMoves = new ArrayList<>();

    	int col = pos.getColumnIndex();
    	int row = pos.getRowIndex();
    	boolean isKing = ((CheckerPiece) state.getIndex(pos)).isKing();
    	BoardIndex newPos;
    	
    	for(int i = 0; i < 2 || (isKing && i < 4); ++i) {
    		int x = col + directions[i][0];
    		int y = row + directions[i][1];
    		
    		if(isValidIndex(x, y)) {
        		newPos = new BoardIndex(x, y);
        		
        		// Normal move
        		if(state.getIndex(newPos) == null)
        			validMoves.add(newPos);
        		
        		// Adjacent to opponent's piece
        		else if(oppPieces.contains(newPos)) {
        			x += directions[i][0];
        			y += directions[i][1];
        			if(isValidIndex(x, y)) {
        	    		newPos = new BoardIndex(x, y);
        	    		// Capture move
            			if(state.getIndex(newPos) == null)
            				validMoves.add(newPos);
        			}
        		}
    		}
    	}
    }

	@Override
	public void moveReceived(Move m) {
		CheckersMove move = (CheckersMove)m;
		
		if(!move.getSource().equals(move.getDest())) {
			pieceSelected = move.getSource();
			makeMove(move.getDest());
			pieceSelected = null;
			
			if(isCapture(move.getSource(), move.getDest())) 
				removePieceBetween(move.getDest(), move.getSource());
		}
		
		// Check if the piece can become a king
		if(canBecomeKing(move.getDest())) {
			CheckerPiece piece = (CheckerPiece) state.getIndex(move.getDest());
			piece.toKing();
		}
		
		isMyTurn = move.isTurnOver();
		
		if(isMyTurn) {
			// Check losing condition: no more valid moves for each piece in myPieces
			if(isGameOver(false)) {
				// TODO: things to do when I lose
				
				isMyTurn = false;
			} else
				gui.showMyTurn();
		}
	}

    @Override
    protected void makeMove(BoardIndex pos) {
    	// Update piece list
    	if(myPieces.remove(pieceSelected))
    		myPieces.add(pos);
    	if(oppPieces.remove(pieceSelected))
    		oppPieces.add(pos);
    	// Move piece in state board
    	Piece piece = state.removePiece(pieceSelected);
    	state.putPiece(piece, pos);
    	gui.movePiece(piece, pieceSelected, pos);
    }

    private void closeCaptureCombo() {
		
    	if(lastMove == null)
    		return;
    	
		if(!lastMove.isTurnOver()) {
			// Dummy move, only to indicate that the turn is over.
			CheckersMove move = new CheckersMove(lastMove.getDest(), lastMove.getDest(), true); 
			lastMove = move;
			// Send move to server
			client.sendMessage(move);
		}
    }
    
	private void removePieceBetween(BoardIndex src, BoardIndex dest) {
		BoardIndex index = new BoardIndex( (src.getColumnIndex() + dest.getColumnIndex()) / 2,
											(src.getRowIndex() + dest.getRowIndex()) / 2 );
		state.removePiece(index);
		myPieces.remove(index);
		oppPieces.remove(index);
		gui.removePiece(index);
	}

	
	private boolean isValidIndex(int col, int row) {
		return col >= 0 && col <= 7 && row >= 0 && row <= 7;
	}
	
	private boolean isCapture(BoardIndex src, BoardIndex dest) {
		return Math.abs(src.getColumnIndex() - dest.getColumnIndex()) > 1
				&& Math.abs(src.getRowIndex() - dest.getRowIndex()) > 1;
	}
	
	// CAUTION: only call this after generateValidMove()
	private boolean canCapture(BoardIndex pos) {
		// by checking the validMoves if there exists an index that is 2-tile away
		for(BoardIndex index : validMoves) 
			if(isCapture(pos, index))
				return true;
		return false;
	}
	
	private boolean canBecomeKing(BoardIndex pos) {
		CheckerPiece piece = (CheckerPiece) state.getIndex(pos);
		if(piece.isKing())
			return false;
		boolean hasReachedOther = (myPieces.contains(pos) && pos.getRowIndex() == 0)
								|| (oppPieces.contains(pos) && pos.getRowIndex() == 7);
		return hasReachedOther;
	}
	
	private boolean isGameOver(boolean checkWin) {
		List<BoardIndex> list = checkWin ? oppPieces : myPieces;

		for(BoardIndex index : list) {
			generateValidMove(index);
			if(validMoves.size() > 0)
				return false;
		}
		return true;
	}
	
}
