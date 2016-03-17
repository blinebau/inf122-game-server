package app.controller;
import java.util.List;
import app.model.BoardIndex;
import app.model.GameState;
import app.model.Move;
import BoardServer.BoardClient;

import app.model.Piece;
import javafx.scene.Scene;

/**
 * Created by Roy on 3/5/16.
 */
public abstract class BoardGameController {
	
	protected GameState state;

	protected List<BoardIndex> validMoves;
	protected BoardClient client; // needed to remove final keyboard in order to add constructor (constructor is needed to run the GameControllers)
    protected Scene myScene;


    public Scene getMyScene() {
        return myScene;
    }

	public BoardGameController() {};

	public BoardGameController(BoardClient c) { 
		client = c; 
	}

	protected abstract void initGuiAndScene(Piece[][] game2DArray); // The game's gui should be initialized here and be used to init myScene
																	// Other initialization modifications to the constructed gui object can be added here

	// Used by the view classes to notify the controller
	public abstract void tileSelected(BoardIndex pos); // Called by GUI component
													   // Used by all of the games to complete a move
												       // - Tic-Tac-Toe (used to immediately make a move)
													   // - Chess / Checkers (used after a piece is currently selected)
	
	public abstract void moveReceived(Move move); // Called by BoardClient when a Move is received from the opponent.
	
	protected abstract void makeMove(BoardIndex pos); // Should include game logic for when a move should be made.
	  												  // When a move is ready to be sent, it should be sent to the other
													  // player using client’s sendMessage(Move) method.


}
