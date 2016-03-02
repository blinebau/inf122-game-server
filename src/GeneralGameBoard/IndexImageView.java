package GeneralGameBoard;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by jgreene on 3/2/16.
 */
public class IndexImageView extends ImageView {

    private int col;
    private int row;

    public IndexImageView(Image img, int colNum, int rowNum) {
        super(img);

        this.col = colNum;
        this.row = rowNum;
    }

    public String getColRowIndex() {
        return "(" + Integer.toString(col) + "," + Integer.toString(row) + ")";
    }
}

