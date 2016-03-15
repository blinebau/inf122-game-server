package Chess.model;

public class Queen extends ChessPiece {

	public String getInitial() { return "Q"; }

	public boolean isValidMove(Square dest) {

		if ((getLocation().getX() == dest.getX()) ||
				getLocation().getY() == dest.getY() ||
				Math.abs(getLocation().getX() - dest.getX()) == Math.abs(getLocation().getY() - dest.getY())) {
			return this.clearPathTo(dest);
		}

		return false;
	}

}
