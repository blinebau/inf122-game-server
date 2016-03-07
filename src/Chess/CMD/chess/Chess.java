package Chess.CMD.chess;

import Chess.CMD.view.CmdView;
import Chess.CMD.controller.Game;


public class Chess {

	public static void main(String[] args) {

		Game game = new Game();
		
		CmdView cmdView = new CmdView(game);
		
		cmdView.startGame();
		
	}

}
