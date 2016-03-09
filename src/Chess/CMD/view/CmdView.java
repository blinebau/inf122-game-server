package Chess.CMD.view;

import java.util.Scanner;

import Chess.CMD.model.PlayerColor;
import Chess.CMD.controller.Game;

public class CmdView {

	private Game game;
	public boolean drawEntered;

	public CmdView(Game game) { this.game = game; }

	public void startGame() {

		Display.showBoard(game);
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);

		while (true) 
		{
			String player = game.getCurrentPlayer().getColor() == PlayerColor.WHITE ? "White" : "Black";
			System.out.print(player + "'s turn: ");
			String input = sc.nextLine().trim();
			parseInput(input);
			//if(drawEntered == false)
			Display.showBoard(game);
			if (game.blackInCheck() || game.whiteInCheck()) System.out.println("Check.");

		}
	}


	public void parseInput(String args)
	{


		String[] tokens = args.split("\\s+");


		if (tokens.length == 0) return;


		/* If the user wants to draw, they have
		 * to append "draw?" to the end of a regular move, so like "e2 e4 draw?". then if
		 * the other user wants to draw as well, they respond with "draws". 
		 */

		if(args.contains("draw?") && args.length()==11)
		{
			this.drawEntered = true;

		}
		if (args.compareTo("draw") == 0 && this.drawEntered) { 

			System.exit(0);
		}
		if (tokens.length > 3 || (tokens.length == 1 && tokens[0].compareTo("resign") != 0)) {
			//A valid move looks either looks like "fileRank fileRank [draw?/N]",  or "resign". so we can rule any input out
			//that isn't at least 3 strings as invalid. 
			System.out.println("Invalid input");
			return;
		}


		if(tokens[0].compareTo("resign") == 0)
		{

			String winner = (game.getCurrentPlayer().getColor() == PlayerColor.WHITE) ? "Black" : "White"; 
			String loser = (winner.compareTo("White") == 0) ? "Black" : "White";

			System.out.println(loser + " resigned. " + winner + " wins!");
			System.exit(0);
		}

		String source = tokens[0];
		String dest = tokens[1];
		String promotion = null;

		if (tokens.length == 3) {

			promotion = tokens[2].trim();
			if (promotion.compareTo("Q") != 0
					&& promotion.compareTo("B") != 0
					&& promotion.compareTo("N") != 0
					&& promotion.compareTo("R") != 0)
			{
				System.out.println("Invalid input");
				return;
			}
		}

		if (source.length() != 2 || dest.length() != 2) {

			System.out.println("Invalid input.");
			return;
		}

		int asciiRank1 = (int) tokens[0].toLowerCase().charAt(0);
        int file1 = Character.getNumericValue(tokens[0].charAt(1));
		int asciiRank2 = (int) tokens[1].toLowerCase().charAt(0);
		int file2 = Character.getNumericValue(tokens[1].charAt(1));


		if ((asciiRank1 < (int) 'a' || asciiRank1 > (int) 'h') 
				|| (asciiRank2 < (int)'a' || asciiRank2 > (int)'h')
				|| (file1 < 1 || file1 > 8)
				|| (file2 < 1 || file2 > 8))
		{
			System.out.println("Invalid Input");
			return;
		}


		if (!game.move(source, dest, promotion)) {

			System.out.println("Illegal move, try again.");
			return;
		}


	}

}


