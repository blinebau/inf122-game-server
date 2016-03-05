package GeneralGameBoard;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

import javax.swing.*;

/**
 * Created by jgreene on 3/1/16.
 */
public class GridPaneTesting extends Application implements EventHandler<MouseEvent> {

    Stage window;
    Scene myScene;
    //TilePane myTilePane;
    GridPane myGridPane;
    //Button button;

    //IndexButton[][] buttonArray; // Used to keep a record of the pieces placed on the gridpane and allow game events to alter the board's pieces
    IndexImageView[][] imgViewArray;


    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Testing TilePane");

        /*
        button = new Button("Hello World!");
        StackPane layout = new StackPane();
        layout.getChildren().add(button);
        */

        /*
        TilePane layout = new TilePane(); // Issue - the tiles wrap based on the width - go back to the GridPane
        layout.setHgap(8);
        layout.setPrefColumns(4);
        for (int i = 0; i < 20; i++) {
            layout.getChildren().add(new Button("Hello!"));
        }
        */

        GridPane layout = new GridPane();
        layout.setHgap(5);
        layout.setVgap(5);

        layout.setStyle("-fx-padding: 20px;");


        /*

        // IndexButtons (Note: Refer to previous commit to get this working again) - Used for Initial Testing

        buttonArray = new IndexButton[4][5]; // [col],[row]

        // c - column (x), r - row (y)
        for (int c = 0; c < 4; c++) {
            for (int r = 0; r < 5; r++) {
                IndexButton temp = new IndexButton("Hello", c, r);
                temp.setOnAction(this); // TODO: Events could be dealt with in some other location
                layout.add(temp, c, r);
                buttonArray[c][r] = temp; // Keep a record of the node
            }
        }

        */

        // ImageViews

        imgViewArray = new IndexImageView[4][5]; // [col],[row]

        // c - column (x), r - row (y)
        for (int c = 0; c < 4; c++) {
            for (int r = 0; r < 5; r++) {
                IndexImageView temp = new IndexImageView(new Image("GeneralGameBoard/King_2.png"), c, r);
                temp.addEventHandler(MouseEvent.MOUSE_CLICKED, this); // Note - Since the mouse event is not processed by the ImageView,
                                                                            // it propagates up to the container.
                layout.add(temp, c, r);
                imgViewArray[c][r] = temp; // Keep a record of the node
            }
        }

        myScene = new Scene(layout);

        window.setScene(myScene);
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void handle(MouseEvent event) {
        //System.out.println("Button Clicked");

        System.out.println("Image Clicked");

        imgViewArray[2][2].setImage(new Image("GeneralGameBoard/Pawn_2.png"));
    }

    public class IndexImageView extends ImageView { // Learned that creating gridpane has methods that allow you to identify a node's row and column
                                                    // So, this and IndexButton aren't needed.

        private int columnIndex;
        private int rowIndex;

        public IndexImageView(int columnIndex, int rowIndex) {
            super();
            this.columnIndex = columnIndex;
            this.rowIndex = rowIndex;
        }

        public IndexImageView(Image img, int columnIndex, int rowIndex) {
            super(img);

            this.columnIndex = columnIndex;
            this.rowIndex = rowIndex;
        }

        public String getColRowIndex() {
            return "(" + Integer.toString(columnIndex) + "," + Integer.toString(rowIndex) + ")";
        }

        public BoardIndex getBoardIndex() { return new BoardIndex(columnIndex, rowIndex); }
    }


    public class IndexButton extends Button { // For Initial Testing

        public int col;
        public int row;

        public IndexButton(String title, int colNum, int rowNum) {
            super(title);

            this.col = colNum;
            this.row = rowNum;
        }

        public String getColRowIndex() {
            return "(" + Integer.toString(col) + "," + Integer.toString(row) + ")";
        }
    }
}
