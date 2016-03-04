package Chess.Pieces;

import Chess.Logic.Board;
import Chess.Logic.Square;

public class Bishop extends Piece{

	public Bishop(String colorIn) {
		super(colorIn, "bishop");
		
		if(color == "white"){
			symbol = "wBi";
		}
		else{
			symbol = "bBi";
		}
	}

	public boolean checkMove(int[] moveFromReq, int[] moveToReq, String plyColor, boolean testKing) {
		
		int moveFromX = moveFromReq[0];
		int moveFromY = moveFromReq[1];
		int moveToX = moveToReq[0];
		int moveToY = moveToReq[1];
		
		Square toSquare = Board.board[moveToY][moveToX];
		
		int moveDistance = Math.abs(moveToX - moveFromX);
		
		if(!testKing){
			if(toSquare.getType() == "king"){
				return false; //can't move to take a king
			}
		}
		
		String direction; //direction the bishop will take
		
		if(moveToX > moveFromX){
			if(moveToY < moveFromY){
				direction = "topRite";
			}
			else{
				direction = "botRite";
			}
		}
		else{
			if(moveToY < moveFromY){
				direction = "topLeft";
			}
			else{
				direction = "botLeft";
			}
		}
		
		
		Square testSquare; //square that will be tested for pieces
		
		//for loop iterating through the diagonal path of the bishop
		for(int diagMoveAway = 1; diagMoveAway <= moveDistance; diagMoveAway++){
			
			if(direction == "topRite"){
				testSquare = Board.board[moveFromY - diagMoveAway][moveFromX + diagMoveAway];
			}
			else if(direction == "botRite"){
				testSquare = Board.board[moveFromY + diagMoveAway][moveFromX + diagMoveAway];
			}
			else if(direction == "topLeft"){
				testSquare = Board.board[moveFromY - diagMoveAway][moveFromX - diagMoveAway];
			}
			else{ //botLeft
				testSquare = Board.board[moveFromY + diagMoveAway][moveFromX - diagMoveAway];
			}
			
			if((testSquare.getType() != "blank") && (diagMoveAway != moveDistance)){
				return false;
			}
			else if((diagMoveAway == moveDistance) && ((testSquare.getColor() != plyColor) || (testSquare.getType() == "blank"))){
				return true;
			}
		}
		return false; //default return value
	}
}
