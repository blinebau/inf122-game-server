package app.model;

import javafx.scene.image.Image;

/**
 * 
 * @author Jiahao Chen
 *
 */

public abstract class Piece {
	/*
	 *  Super class of all game pieces.
	 *  Should be extended by different game pieces.
	 *  Should be used as programming-to-interface.
	 */
    private Image img;

    public Image getImage() {
        return img;
    }

    public void setImage(Image img) {
        this.img = img;
    }
}
