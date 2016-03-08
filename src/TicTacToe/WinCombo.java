package TicTacToe;

/**
 * Created by Sophia on 3/7/2016.
 */
public class WinCombo {
    private IndTile[] tiles;

    public WinCombo(IndTile... tiles) {
        this.tiles = tiles;
    }

    public boolean isComplete() {
        if (tiles[0].getValue().isEmpty())
            return false;

        return tiles[0].getValue().equals(tiles[1].getValue())
                && tiles[0].getValue().equals(tiles[2].getValue());
    }

    public IndTile getTile(int i) {
        return tiles[i];
    }
}
