package GeneralGameBoard;

import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

/**
 * Created by jgreene on 3/1/16.
 */
public class BoardGameGridPane extends GridPane{

    private int columns, rows;
    private double tileSize;
    private Color tileColor;
    private ImageView[][] imgViewArray; // Used to keep a record of the pieces placed on the gridpane and allow game events to alter the board's pieces

    public BoardGameGridPane(int cols, int rows, double hgap, double vgap, double tileSize, Color tileColor, EventHandler<MouseEvent> eventClass) {
        super();

        this.columns = cols;
        this.rows = rows;
        this.tileSize = tileSize;
        this.tileColor = tileColor; // TODO: Will need to be extended to handle checkered boards

        // Set the amount of space in between the rows and columns
        setHgap(hgap);
        setVgap(vgap);

        imgViewArray = new ImageView[cols][rows];

        // Set the constraints for the rows and cols
        for (int c = 0; c < cols; c++) {
            getColumnConstraints().add(new ColumnConstraints(tileSize));
        }

        for (int r = 0; r < rows; r++) {
            getRowConstraints().add(new RowConstraints(tileSize));
        }

        initTiles(eventClass);
    }

    private void initTiles(EventHandler<MouseEvent> eventClass) { // Add the board's tiles and empty imageViews to represent the pieces / board objects
                                                                 // and record the imageviews so that they can be altered easily
        // c - column (x), r - row (y)
        for (int c = 0; c < columns; c++) {
            for (int r = 0; r < rows; r++) {
                // Tiles
                Rectangle rec = new Rectangle();
                rec.setWidth(tileSize);
                rec.setHeight(tileSize);
                rec.setFill(tileColor);
                rec.setStrokeWidth(0);
                rec.addEventHandler(MouseEvent.MOUSE_CLICKED, eventClass);
                add(rec, c, r);

                // Empty ImageViews for Pieces and other board objects
                ImageView temp = new ImageView();
                temp.addEventHandler(MouseEvent.MOUSE_CLICKED, eventClass);
                add(temp, c, r);
                imgViewArray[c][r] = temp;
            }
        }
    }

    public void addBoardObjectToTile(BoardIndex index, BoardObject boardObject) {  // Set the corresponding imageview's image to the boardObject's image
        imgViewArray[index.getColumnIndex()][index.getRowIndex()].setImage(boardObject.getImage());
    }

    public void resetTile(BoardIndex index) { // Sets the corresponding imageview's image to null (default state)
        imgViewArray[index.getColumnIndex()][index.getRowIndex()].setImage(null);
    }

    public void setBoardObjectStartingLayout(BoardObject[][] object2DArray) {   // The images of the array's boardObjects will be used to set the image of their
                                                                                // corresponding imageviews
        for (int c = 0; c < columns; c++) {
            for (int r = 0; r < rows; r++) {
                if (object2DArray[c][r] instanceof BoardObject) {
                    addBoardObjectToTile(new BoardIndex(c,r), object2DArray[c][r]);
                }
            }
        }
    }
}
