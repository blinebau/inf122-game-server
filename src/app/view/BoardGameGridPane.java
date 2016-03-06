package app.view;

import app.model.BoardIndex;
import app.model.Piece;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
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
    private boolean isCheckered;
    private Color tilePrimaryColor;
    private Color tileSecondaryColor;
    private ImageView[][] imgViewArray; // Used to keep a record of the pieces placed on the gridpane and allow game events to alter the board's pieces

    public BoardGameGridPane(int cols, int rows, double hgap, double vgap, double tileSize,
                             Color tilePrimaryColor,Color tileSecondaryColor, EventHandler<Event> eventClass) {
        super();

        this.columns = cols;
        this.rows = rows;
        this.tileSize = tileSize;
        this.tilePrimaryColor = tilePrimaryColor;
        this.tileSecondaryColor = tileSecondaryColor;

        if (tileSecondaryColor == null) {
            isCheckered = false;
        } else {
            isCheckered = true;
        }

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

    private void initTiles(EventHandler<Event> eventClass) { // Add the board's tiles and empty imageViews to represent the pieces / board objects
                                                                 // and record the imageviews so that they can be altered easily
        int order = 0;


        // c - column (x), r - row (y)
        for (int c = 0; c < columns; c++) {
            for (int r = 0; r < rows; r++) {
                // Tiles
                Rectangle rec = new Rectangle();
                rec.setWidth(tileSize);
                rec.setHeight(tileSize);
                rec.setStrokeWidth(0);

                // Fill the tiles differently based on if the board is checkered or constant
                if (!isCheckered) {
                    rec.setFill(tilePrimaryColor);
                } else {
                    // Switch between the primary color for each row
                    if (order == 0) {
                        rec.setFill(tilePrimaryColor);
                        order = 1;
                    } else {
                        rec.setFill(tileSecondaryColor);
                        order = 0;
                    }
                }

                rec.addEventHandler(MouseEvent.MOUSE_CLICKED, eventClass);
                add(rec, c, r);

                // Empty ImageViews for Pieces and other board objects
                ImageView temp = new ImageView();
                temp.addEventHandler(MouseEvent.MOUSE_CLICKED, eventClass);
                add(temp, c, r);
                imgViewArray[c][r] = temp;
            }
            // Change the order for the next column
            order = 1 - order;
        }
    }

    public BoardIndex getBoardIndex(Node element) {
        return new BoardIndex(getColumnIndex(element), getRowIndex(element));
    }

    public void addPieceToTile(BoardIndex index, Piece piece) {  // Set the corresponding imageview's image to the boardObject's image
        imgViewArray[index.getColumnIndex()][index.getRowIndex()].setImage(piece.getImage());
    }

    public void resetTile(BoardIndex index) { // Sets the corresponding imageview's image to null (default state)
        imgViewArray[index.getColumnIndex()][index.getRowIndex()].setImage(null);
    }

    public void setPieceStartingLayout(Piece[][] piece2DArray) {   // The images of the array's boardObjects will be used to set the image of their
                                                                                // corresponding imageviews
        for (int c = 0; c < columns; c++) {
            for (int r = 0; r < rows; r++) {
                if (piece2DArray[c][r] instanceof Piece) {
                    addPieceToTile(new BoardIndex(c,r), piece2DArray[c][r]);
                }
            }
        }
    }
}
