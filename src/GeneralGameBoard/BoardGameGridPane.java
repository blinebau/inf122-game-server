package GeneralGameBoard;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 * Created by jgreene on 3/1/16.
 */
public class BoardGameGridPane extends GridPane{

    private int columns, rows;
    private IndexImageView[][] imgViewArray; // Used to keep a record of the pieces placed on the gridpane and allow game events to alter the board's pieces

    public BoardGameGridPane(int cols, int rows, double hgap, double vgap, EventHandler<MouseEvent> eventClass) {
        super();

        this.columns = cols;
        this.rows = rows;

        // Set the amount of space in between the rows and columns
        setHgap(hgap);
        setVgap(vgap);

        imgViewArray = new IndexImageView[cols][rows];

        initBaseBoard(eventClass);
    }

    public void initBaseBoard(EventHandler<MouseEvent> eventClass) { // Initialize and record the nodes on the gridpane
        // c - column (x), r - row (y)
        for (int c = 0; c < columns; c++) {
            for (int r = 0; r < rows; r++) {
                IndexImageView temp = new IndexImageView(new Image("GeneralGameBoard/tile_example.png"), c, r);
                temp.addEventHandler(MouseEvent.MOUSE_CLICKED, eventClass); // Note - Since the mouse event is not processed by the ImageView,
                                                                            // it propagates up to the container.
                add(temp, c, r);
                imgViewArray[c][r] = temp;
            }
        }
    }

    public void replaceTileWithImage(int col, int row, Image img) { // temporary
        imgViewArray[col][row].setImage(img);
    }
}
