package Chess.Pieces;

import Chess.Logic.Board;
import Chess.Logic.Square;

public class Pawn extends Piece{

	public Pawn(String colorIn) {
		super(colorIn, "pawn");
		
		if(color == "white"){
			symbol = "wPa";
		}
		else{
			symbol = "bPa";
		}
	}
	
	public boolean checkMove(int[] moveFromReq, int[] moveToReq, String plyColor, boolean testKing) {
		
		int moveFromX = moveFromReq[0];
		int moveFromY = moveFromReq[1];
		int moveToX = moveToReq[0];
		int moveToY = moveToReq[1];
		
		int moveForwardTwo; //vars that increment the moves | change based on direction
		int moveForwardOne;
		int pawnRowOnPlySide; //row that the certain players pawns are on
		
		Square toSquare = Board.board[moveToY][moveToX];
		
		if(!testKing){
			if(toSquare.getType() == "king"){
				return false; //can't move to take a king
			}
		}
		
		if(plyColor == "white"){ //for white pieces direction changes
			moveForwardTwo = -2;
			moveForwardOne = -1;
			pawnRowOnPlySide = 6;
		}
		else{ //black
			moveForwardTwo = 2;
			moveForwardOne = 1;
			pawnRowOnPlySide = 1;
		}
			
		if(moveToY == moveFromY + moveForwardOne){
			
			//move to take a piece that is of a different color to the diagonally
			if((moveToX == moveFromX - 1) || (moveToX == moveFromX + 1)){
				if((toSquare.getType() != "blank") && (toSquare.getColor() != plyColor)){
					return true; 
				}
			}	
			//straight move forward 1 and move is to blank space
			else if((moveToX == moveFromX) && (toSquare.getType() == "blank")){ 
				return true;
			}
		}
		//move forward 2 straight and is to blank space
		else if((moveToY == moveFromY + moveForwardTwo) && (moveToX == moveFromX) && (toSquare.getType() == "blank")){ 
			if(moveFromY == pawnRowOnPlySide){ //if pawn moves from the starting row
				return true;
			}
		}
		
		return false; //only get here if other possiblities fail
	}	
}
