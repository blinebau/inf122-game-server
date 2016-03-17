package TicTacToe;

import app.model.Piece;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
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

    public void setShape(String s) {
        shape = s;
    }

    public String getShape() {
        return shape;
    }

}
