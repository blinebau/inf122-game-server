package BoardServer;

import TicTacToe.TTTController;
import app.controller.BoardGameController;


import Checkers.controller.CheckersController;
import Chess.controller.ChessController;

/**
 * Created by jgreene on 3/16/16.
 */
public class GameControllerFactory {

    public static BoardGameController createGameController(BoardClient client, String gameName) {
        
        if (gameName.equals("TicTacToe")) {
            return new TTTController(client);
        } else if (gameName.equals("Chess")) {
            return new ChessController(client);
        } else if (gameName.equals("Checkers")) {
            return new CheckersController(client);
        } else
            return null;
    }
}
