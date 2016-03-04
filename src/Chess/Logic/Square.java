package Chess.Logic;

public abstract class Square {
	
	//-1 indicates that piece is off the board
	protected String symbol;
	public String color; //black white or null for blanks
	public String type;
	
	public Square(String typeIn){
		type = typeIn;
	}
	
	String getSymbol(){
		return symbol;
	}
	public String getColor(){
		return color;
	}
	public String getType(){
		return type;
	}
	
	public abstract boolean checkMove(int[] moveFromReq, int[] moveToReq, String plyColor, boolean testKing);
}
