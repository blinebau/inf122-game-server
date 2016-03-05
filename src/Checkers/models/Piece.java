package Checkers.models;

/**
 * Created by Roy on 3/5/16.
 */
public class Piece {
    int x;
    int y;
    boolean king;
    String color;

    public Piece (int x, int y, boolean king, String color) {
        this.x = x;
        this.y = y;
        this.king = king;
        this.color = color;
    }

}

