///**
// * Created by Sophia on 3/4/2016.
// */
//public class TTTPiece {
//    private TTTEvent[] tiles;
//
//    /**
//     * sets up tiles
//     * @param tiles
//     */
//    public TTTPiece(TTTEvent... tiles) {
//        this.tiles = tiles;
//    }
//
//    /**
//     * checks to see if the tile has an X or O drawn on it already
//     * @return
//     */
//    public boolean isComplete() {
//        if (tiles[0].getValue().isEmpty())
//            return false;
//
//        return tiles[0].getValue().equals(tiles[1].getValue())
//                && tiles[0].getValue().equals(tiles[2].getValue());
//    }
//
//    /**
//     * returns the tile
//     * @param i
//     * @return
//     */
//    public TTTEvent getTile(int i) {
//        return tiles[i];
//    }
//
//}
