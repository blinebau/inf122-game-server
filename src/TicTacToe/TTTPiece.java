package TicTacToe;

import app.model.Piece;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by Sophia on 3/12/2016.
 */
public class TTTPiece extends Piece {

    /**
     * Constructor
     */
    public TTTPiece() {
        this.setImage(null);
    }

    public TTTPiece(Image image) {
        this.setImage(image);
    }

}
