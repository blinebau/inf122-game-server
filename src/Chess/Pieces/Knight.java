package Chess.Pieces;

import Chess.Logic.Board;
import Chess.Logic.Square;

public class Knight extends Piece{

	public Knight(String colorIn) {
		super(colorIn, "knight");
		
		if(color == "white"){
			symbol = "wKn";
		}
		else{
			symbol = "bKn";
		}
	}

	public boolean checkMove(int[] moveFromReq, int[] moveToReq, String plyColor, boolean testKing) {
		
		int moveFromX = moveFromReq[0];
		int moveFromY = moveFromReq[1];
		int moveToX = moveToReq[0];
		int moveToY = moveToReq[1];
		
		Square toSquare = Board.board[moveToY][moveToX];
		
		if(!testKing){
			if(toSquare.getType() == "king"){
				return false; //can't move to take a king
			}
		}
		
		boolean locationPass = false; //default false
		
		for(int displaceX = -2; displaceX <= 2; displaceX++){
		
			if(displaceX != 0){
				if(moveToX == moveFromX + displaceX){
					
					if(Math.abs(displaceX) == 1){ //if the x displace was 1 the y displace must be 2
						for(int displaceY = -2; displaceY <= 2; displaceY += 4){
							if(moveToY == moveFromY + displaceY){
								locationPass = true;
							}
						}
					}
					else{ //x displace is 2 so y displace is 1
						for(int displaceY = -1; displaceY <= 1; displaceY += 2){
							if(moveToY == moveFromY + displaceY){
								locationPass = true;
							}
						}
					}
				}
			}
		}
		if(locationPass){ //if the location was not within a knights move rules
			
			if((toSquare.getType() == "blank") || (toSquare.getColor() != plyColor)){
				return true;
			}
		}
		
		return false;
	}
}
