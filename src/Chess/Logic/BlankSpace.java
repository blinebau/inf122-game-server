package Chess.Logic;

public class BlankSpace extends Square{
	
	public BlankSpace() {
		super("blank");
		symbol = "   ";
		color = null; //a blank square has no color
		
	}

	public void move(int[] moveToLoc) {
	}

	public boolean checkMove(int[] moveFromReq, int[] moveToReq, String plyColor, boolean testKing) { //unused
		return false;
	}

}
