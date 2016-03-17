package TicTacToe;

import app.model.Piece;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * TTTPiece: Extends Piece, adds a shape attribute
 * Created by Sophia on 3/12/2016.
 */
public class TTTPiece extends Piece {

    private String shape;
    /**
     * Constructor that takes in no arguments
     */
    public TTTPiece() {
        this.setImage(null);
        this.shape = "";
    }

    /**
     * Constructor that takes in an Image
     * @param image
     */
    public TTTPiece(Image image) {
        this.setImage(image);
    }

    /**
     * setShape(): sets attribute shape
     * @param s
     */
    public void setShape(String s) {
        shape = s;
    }

    /**
     * getShape(): returns attribute shape
     * @return
     */
    public String getShape() {
        return shape;
    }

}
