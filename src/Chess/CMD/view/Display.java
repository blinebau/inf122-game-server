package Chess.CMD.view;

import Chess.CMD.model.ChessPiece;
import Chess.CMD.model.PlayerColor;
import Chess.CMD.model.Square;
import Chess.CMD.controller.Game;

//initial thoughts -- after performing game logic associated with a move in Game, this class would display the results of this logic 
public class Display {

	public static void showBoard(Game game) {

		Square[][] board = game.getBoard();
		int i, j;
		for (i = 0; i < 8; i++) {
			for (j = 0; j < 8; j++) {
				
				ChessPiece piece = board[i][j].getPiece();

				if (piece == null) {
					
					//even row
					if (i % 2 == 0) {
						
						//even column
						if (j % 2 == 0) {
							
							System.out.print("   ");
							continue;
						}
						//odd column
						else {
							System.out.print("## ");
							continue;
						}
					}
					//odd row
					else {
						
						//even column
						if (j % 2 == 0) {
							
							System.out.print("## ");
							continue;
						}
						//odd column
						else {
						
							System.out.print("   ");
							continue;
						}
					}
				}
				
				//occupied square
				
				String initials = piece.getPlayer().getColor() == PlayerColor.BLACK ? "b" : "w";
				initials += piece.getInitial();
				
				System.out.print(initials + " ");
				
			}
			System.out.println(j - i);
		}
		
		System.out.println("a  b  c  d  e  f  g  h  ");
		System.out.println();
				
		/*
		 * Desired Output
		 * 
		 * bR bN bB bQ bK bB bN bR  8 
		 * wP wP wP wP wP wP wP wP  7
		 *    ##    ##    ##    ##  4
		 * ##    ##    ##    ##     5
		 *    ##    ##    ##    ##  4
		 * ##    ##    ##    ##     3
		 * bP bP bP bP bP bP bP bP  2
		 * bR bN bB bQ bK bB bN bR  1
		 * a   b  c  d  e  f  g  h
		 * 
		 */
	}

}
