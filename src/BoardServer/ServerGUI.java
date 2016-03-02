package BoardServer;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * Created by Bryan on 3/1/2016.
 */
public class ServerGUI extends Application {
    //future gui for server goes here
    //server would contains a display of connected user
    //start and stop button

    public void start(Stage stage)
    {
        drawServerEntry(stage);
    }

    public void drawServerEntry(Stage stage)
    {
        stage.setTitle("Board Game Server");

        BorderPane serverPane = new BorderPane();

        //Horizontal box with port and start button nodes
        GridPane serverGridPane = new GridPane();
        //gridPane.setGridLinesVisible(true);
        serverGridPane.setHgap(5);
        serverGridPane.setVgap(10);
        serverGridPane.setAlignment(Pos.CENTER);

        serverGridPane.setPadding(new Insets(20, 10, 20, 20));
        //port number and start button

        HBox startBox = new HBox(10);
        Label portLabel = new Label("Port #:");
        TextField portTextField = new TextField();
        portTextField.setPrefWidth(100);
        Button startBtn = new Button("Start");

        Pane bottomBorder = new Pane();
        serverGridPane.add(portLabel, 0, 0);
        serverGridPane.add(portTextField, 1, 0);
        serverGridPane.add(startBtn, 2, 0);
        serverGridPane.add(bottomBorder, 0, 1);


        serverPane.setTop(serverGridPane);
        Scene serverGUI = new Scene(serverPane, 500, 500);
        stage.setScene(serverGUI);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
