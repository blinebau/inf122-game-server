package app.controller;
import java.util.ArrayList;
import java.util.List;
import app.model.BoardIndex;
import app.model.GameState;
import app.model.Move;
import app.view.GameGUI;
import BoardServer.BoardClient;
import javafx.application.Application;

/**
 * Created by Roy on 3/5/16.
 */
public abstract class BoardGameController extends Application {
	
	protected GameState state;
	protected GameGUI gui;
	protected List<BoardIndex> validMoves;
	protected BoardClient client; // needed to remove final keyboard in order to add constructor (constructor is needed to run the GameControllers)

	public BoardGameController() {};

	public BoardGameController(BoardClient c) { 
		client = c; 
		validMoves = new ArrayList<>();
	}

	// Used by the view classes to notify the controller
	public abstract void tileSelected(BoardIndex pos); // Called by GUI component
													   // Used by all of the games to complete a move
												       // - Tic-Tac-Toe (used to immediately make a move)
													   // - Chess / Checkers (used after a piece is currently selected)
	
	public abstract void moveReveived(Move move); // Called by BoardClient when a Move is received from the opponent.
	
	protected abstract void makeMove(BoardIndex pos);
	
	protected abstract boolean validateMove(BoardIndex pos);
	
	protected abstract void updateModel();
	
	protected abstract void updateView();
}
