package TicTacToe;

/**
 * WinCombo: class which checks if a combo is a winning combo
 * Created by Sophia on 3/7/2016.
 */
public class WinCombo {

    private TTTPiece[] tiles;

    /**
     * Constructor that takes in TTTPieces
     * @param tiles
     */
    public WinCombo(TTTPiece... tiles) {
        this.tiles = tiles;
    }

    /**
     * isComplete(): checks if a winning combo has been completed
     * @return
     */
    public boolean isComplete() {

        if (tiles[0].getShape().isEmpty()) {
            return false;
        }

        return tiles[0].getShape().equals(tiles[1].getShape()) &&
                tiles[0].getShape().equals(tiles[2].getShape());
    }

    /**
     * getTile(): returns a Piece at that index
     * @param i
     * @return
     */
    public TTTPiece getTile(int i) {
        return tiles[i];
    }
}
