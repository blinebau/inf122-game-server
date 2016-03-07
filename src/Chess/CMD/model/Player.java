package Chess.CMD.model;

import java.util.ArrayList;

public class Player {

	private ArrayList<ChessPiece> pieces;

	private Player opponent;

	private King king;

	private PlayerColor color;

	public Player(PlayerColor color) {
		this.color = color;	
		pieces = new ArrayList<ChessPiece>(16);
	}

	public PlayerColor getColor() { return color; }

	public Player getOpponent() { return opponent; }

	public void addPiece(ChessPiece piece) { pieces.add(piece); } 

	public ArrayList<ChessPiece> getPieces() { return pieces; }

	public void setOpponent(Player opponent) { this.opponent = opponent; }

	public void setKing(King king) { this.king = king; }

	public King getKing() { return king; }

}
