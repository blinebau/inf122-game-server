package Chess.CMD.model;

public abstract class ChessPiece {


	private Square[][] board;

	private Square location;

	private Player player;

	private int moves;

	public abstract String getInitial();


	public abstract boolean isValidMove(Square dest);

	public boolean canMoveTo(Square dest) {

		if (location.equals(dest)) return false;
		
		if (dest.getPiece() != null) {

			if (player.getColor() == dest.getPiece().getPlayer().getColor()) {
				
				return false;
			}
		}

		//implemented on a piece by piece basis. 
		if (!isValidMove(dest))  return false;

		Square kingLoc = null;
		
		if (this instanceof King) {
			
			kingLoc = dest;
		}
		else {
			
			kingLoc = getPlayer().getKing().getLocation();
		}
		
		if (getPlayer().getKing().inCheck(kingLoc)) return false;

		return true;
	}

	public boolean clearPathTo(Square dest) {

		if (location.getX() == dest.getX() && location.getY() != dest.getY()) {

			// plus/minus 1 because we don't check the starting or end square
			int curr = Math.min(location.getY(), dest.getY()) + 1;
			int end = Math.max(location.getY(), dest.getY()) - 1;

			while (curr <= end) {

				if (board[curr++][location.getX()].getPiece() != null) return false;
			}
			return true;
		}

		else if (location.getX() != dest.getX() && location.getY() == dest.getY()) {

			int curr = Math.min(location.getX(), dest.getX()) + 1;
			int end = Math.max(location.getX(), dest.getX()) - 1;

			while (curr <= end) {

				if (board[location.getY()][curr++].getPiece() != null) return false;
			}
			return true;
		}

		else if (Math.abs(location.getX() - dest.getX()) == Math.abs(location.getY() - dest.getY())) {

			int dx = (location.getX() < dest.getX()) ? 1 : -1;
			int dy = (location.getY() < dest.getY()) ? 1 : -1;
			int currX  = location.getX() + dx;
			int currY = location.getY() + dy;

			while ((dx == 1 && currX < dest.getX()) || (dx == -1 && currX > dest.getX())){
				if (board[currY][currX].getPiece() != null){
			
					return false;
				}
				currX += dx;
				currY += dy;
			}
			return true;
		}

		return false;
	}

	public Square getLocation() { return location; }

	public void setLocation(Square location) { this.location = location; }

	public Player getPlayer() { return player; }

	public void setPlayer(Player player) { this.player = player; }

	public Square[][] getBoard() { return board; }

	public void incrementMoves() { moves++; }

	public int numberOfMoves() { return moves; }
	
	public void setNumberOfMoves(int moves) { this.moves = moves; }

	public void setBoard(Square[][] board) { this.board = board; }

}


