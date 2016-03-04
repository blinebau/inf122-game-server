package Chess.Pieces;

import Chess.Logic.Board;
import Chess.Logic.Square;

public class Rook extends Piece{

	public Rook(String colorIn) {
		super(colorIn, "rook");
		
		if(color == "white"){
			symbol = "wRo";
		}
		else{
			symbol = "bRo";
		}
	}

	public boolean checkMove(int[] moveFromReq, int[] moveToReq, String plyColor, boolean testKing) {
		
		int moveFromX = moveFromReq[0];
		int moveFromY = moveFromReq[1];
		int moveToX = moveToReq[0];
		int moveToY = moveToReq[1];
		
		Square toSquare = Board.board[moveToY][moveToX];
		
		String direction;
		
		if(!testKing){
			if(toSquare.getType() == "king"){
				return false; //can't move to take a king
			}
		}
		
		if(moveToY == moveFromY){
			if(moveToX > moveFromX){
				direction = "rite";
			}
			else{
				direction = "left";
			}
		}
		
		else if(moveToX == moveFromX){
			if(moveToY > moveFromY){
				direction = "bot";
			}
			else{
				direction = "top";
			}
		}
		else{
			return false;
		}
		
		Square testSquare;
		
		if((direction == "rite") || (direction == "left")){
			int displaceMax = Math.abs(moveToX - moveFromX); //displacement max depending on what the move to values are
		
			for(int displace = 1; displace <= displaceMax; displace++){ //looping through squares on the rooks path
				if(direction == "rite"){
					testSquare = Board.board[moveFromY][moveFromX + displace];
					
					if((testSquare.getType() != "blank") && (displace != displaceMax)){
						return false;
					}
					else if((displace == displaceMax) && ((testSquare.getType() == "blank") || (testSquare.getColor() != plyColor))){
						return true;
					}
				}
				else{
					testSquare = Board.board[moveFromY][moveFromX - displace];
					
					if((testSquare.getType() != "blank") && (displace != displaceMax)){
						return false;
					}
					else if((displace == displaceMax) && ((testSquare.getType() == "blank") || (testSquare.getColor() != plyColor))){
						return true;
					}
				}
			}
		}
		else{ // direction : top or bot
			int displaceMax = Math.abs(moveToY - moveFromY); //displacement max depending on what the move to values are
				
			for(int displace = 1; displace <= displaceMax; displace++){ //looping through squares on the rooks path	
				
				if(direction == "top"){
					testSquare = Board.board[moveFromY - displace][moveFromX];
					
					if((testSquare.getType() != "blank") && (displace != displaceMax)){
						return false;
					}
					else if((displace == displaceMax) && ((testSquare.getType() == "blank") || (testSquare.getColor() != plyColor))){
						return true;
					}
				}
				else{
					testSquare = Board.board[moveFromY + displace][moveFromX];
					
					if((testSquare.getType() != "blank") && (displace != displaceMax)){
						return false;
					}
					else if((displace == displaceMax) && ((testSquare.getType() == "blank") || (testSquare.getColor() != plyColor))){
						return true;
					}
				}
			}
		}
		return false;
	}
}
