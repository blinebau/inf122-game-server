package GeneralGameBoard;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

/**
 * Created by jgreene on 3/1/16.
 */
public class GridPaneTesting extends Application implements EventHandler<ActionEvent> {

    Stage window;
    Scene myScene;
    //TilePane myTilePane;
    GridPane myGridPane;
    //Button button;

    IndexButton[][] buttonArray; // Used to keep a record of the pieces placed on the gridpane and allow game events to alter the board's pieces

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

        buttonArray = new IndexButton[4][5];

        // c - column (x), r - row (y)
        for (int c = 0; c < 4; c++) {
            for (int r = 0; r < 5; r++) {
                IndexButton temp = new IndexButton("Hello", c, r); // TODO: Do something similar with the imageview class that I will be creating
                temp.setOnAction(this); // TODO: Events could be dealt with in some other location
                layout.add(temp, c, r);
                buttonArray[c][r] = temp; // Keep a record of the node
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
    public void handle(ActionEvent event) {
        //System.out.println("Button Clicked");
        System.out.println(((IndexButton) event.getSource()).getRowColIndex());

        buttonArray[2][2].setText("What?");
    }


    public class IndexButton extends Button {

        public int col;
        public int row;

        public IndexButton(String title, int colNum, int rowNum) {
            super(title);

            this.col = colNum;
            this.row = rowNum;
        }

        public String getRowColIndex() {
            return "(" + Integer.toString(col) + "," + Integer.toString(row) + ")";
        }
    }
}
