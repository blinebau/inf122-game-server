package GeneralGameBoard;

import javafx.scene.image.Image;

/**
 * Created by jgreene on 3/5/16.
 */
public abstract class BoardObject {
    private Image img;

    public BoardObject() {}

    public Image getImage() {
        return img;
    }

    public void setImage(Image img) {
        this.img = img;
    }
}
