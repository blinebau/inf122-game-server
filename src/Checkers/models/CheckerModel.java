package Checkers.models;

/**
 * Created by Roy on 3/5/16.
 */
public class CheckerModel {
    int id;
    Player white;
    Player black;
    String turnColor;



    // Get game ID and color of the current player
    public CheckerModel (int id, String turnColor) {
        white = new Player();
        black = new Player();

        this.turnColor = turnColor;
    }
}
