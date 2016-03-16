package BoardServer;

import Checkers.controller.CheckersController;
import Chess.controller.ChessController;
import TicTacToe.OriginalTicTacToe;
import app.controller.BoardGameController;

/**
 * Created by jgreene on 3/16/16.
 */
public class GameControllerFactory {

    public static BoardGameController createGameController(BoardClient client, String gameName) {
        
        if (gameName.equals("TicTacToe")) {
            return new TicTacToeController(client);
        } else if (gameName.equals("Chess")) {
            return new ChessController(client);
        } else if (gameName.equals("Checkers")) {
            return new CheckersController(client);
        } else
            return null;
    }
}
