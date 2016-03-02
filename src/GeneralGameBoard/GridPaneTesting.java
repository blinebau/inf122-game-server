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

        // i - column (x), j - row (y)
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                IndexButton temp = new IndexButton("Hello", i, j); // TODO: Do something similar with the imageview class that I will be creating
                temp.setOnAction(this);
                layout.add(temp, i, j);
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
