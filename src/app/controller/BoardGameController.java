package app.controller;

import java.util.List;
import app.model.BoardIndex;
import app.model.GameState;
import app.view.GameGUI;
import BoardServer.BoardClient;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Created by Roy on 3/5/16.
 */
public abstract class BoardGameController {
	
	protected GameState state;
	protected GameGUI gui;
	protected List<BoardIndex> validMoves;
	protected BoardClient client; // needed to remove final keyboard in order to add constructor (constructor is needed to run the GameControllers)
	protected Stage stage;

	public BoardGameController() {};

	public BoardGameController(BoardClient c, Stage s) {
		client = c;
		stage = s;
	}

	protected abstract void setUpModelAndView();

	// Used by the view classes to notify the controller
	public abstract void tileSelected(BoardIndex pos); // Used by all of the games to complete a move
												       // - Tic-Tac-Toe (used to immediately make a move)
													   // - Chess / Checkers (used after a piece is currently selected)
	protected abstract void makeMove(BoardIndex pos);
	
	protected abstract boolean validateMove(BoardIndex pos);
	
	protected abstract void updateModel();
	
	protected abstract void updateView();
}
