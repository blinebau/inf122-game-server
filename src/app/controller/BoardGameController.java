package app.controller;

import java.util.List;
import GeneralGameBoard.BoardIndex;
import app.model.GameState;
import app.view.GameGUI;
import BoardServer.BoardClient;

/**
 * Created by Roy on 3/5/16.
 */
public abstract class BoardGameController {
	
	private GameState state;
	private GameGUI gui;
	private List<BoardIndex> validMoves;
	private final BoardClient client;
	
	public BoardGameController(BoardClient c) {
		client = c;
	}
	
	protected abstract void makeMove(BoardIndex pos);
	
	protected abstract boolean validateMove(BoardIndex pos);
	
	protected abstract void updateModel();
	
	protected abstract void updateView();
}
