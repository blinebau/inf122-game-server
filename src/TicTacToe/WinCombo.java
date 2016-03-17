package TicTacToe;

import app.model.Piece;

/**
 * Created by Sophia on 3/7/2016.
 */
public class WinCombo {
//    private IndTile[] tiles;
//
//    public WinCombo(IndTile... tiles) {
//        this.tiles = tiles;
//    }
//
//    public boolean isComplete() {
//        if (tiles[0].getValue().isEmpty())
//            return false;
//
//        return tiles[0].getValue().equals(tiles[1].getValue())
//                && tiles[0].getValue().equals(tiles[2].getValue());
//    }
//
//    public IndTile getTile(int i) {
//        return tiles[i];
//    }

    private TTTPiece[] tiles;

    public WinCombo(TTTPiece... tiles) {
        this.tiles = tiles;
    }

    public boolean isComplete() {

        if (tiles[0].getShape().isEmpty()) {
            return false;
        }
        boolean temp = tiles[0].getShape().equals(tiles[1].getShape()) &&
                tiles[0].getShape().equals(tiles[2].getShape());

        System.out.println(temp);
        return temp;
    }

    public TTTPiece getTile(int i) {
        return tiles[i];
    }
}
