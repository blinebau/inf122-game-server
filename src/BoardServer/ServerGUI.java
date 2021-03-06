package BoardServer;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.Serializable;
import java.security.Key;

/**
 * Created by Bryan on 3/1/2016.
 */
public class ServerGUI extends Application {

    private BoardServer server;
    private TextArea eventLog;

    public void start(Stage stage)
    {
        drawServerEntry(stage);

    }

    public void drawServerEntry(Stage stage)
    {
        stage.setTitle("Board Game Server");
        stage.getIcons().add(new Image("BoardServer/chess_logo.png"));

        BorderPane serverPane = new BorderPane();

        VBox topBox = new VBox(5);

        HBox startBox = new HBox(10);
        Label portLabel = new Label("Port #:");
        TextField portTextField = new TextField();
        portTextField.setPrefWidth(100);
        Button startBtn = new Button("Start");
        Button stopBtn = new Button("Stop");

        startBox.setPadding(new Insets(10,0,0,120));
        startBox.getChildren().addAll(portLabel, portTextField, startBtn, stopBtn);

        Label logLabel = new Label("Server log");
        logLabel.setPadding(new Insets(0, 0, 0, 0));
        topBox.getChildren().addAll(startBox, logLabel);

        eventLog = new TextArea();
        eventLog.addEventFilter(KeyEvent.ANY, (KeyEvent event) -> {
            boolean consume = true;
            if(consume)
            {
                event.consume();
            }
            if(event.getCode().isKeypadKey())
            {
                if(event.getEventType() == KeyEvent.KEY_PRESSED)
                {
                    consume = false;
                }
                else if(event.getEventType() == KeyEvent.KEY_RELEASED)
                {
                    consume = true;
                }
            }
        });
        serverPane.setCenter(eventLog);

        serverPane.setTop(topBox);
        Scene serverGUI = new Scene(serverPane, 500, 500);
        stage.setScene(serverGUI);
        stage.show();

        startBtn.setOnAction((ActionEvent event) -> {
            if(portTextField.getText() != null && !portTextField.getText().isEmpty()) {
                int portNum = Integer.parseInt(portTextField.getText());
                server = new BoardServer(portNum, this);
                new ServerThread().start();
            }
        });

        stopBtn.setOnAction((ActionEvent event) -> {
            if (server.isRunning()) {
                eventLog.clear();
                server.stop();
                server = null;
            }
        });

        stage.setOnCloseRequest(e -> System.exit(0));

    }

    public TextArea getEventLog()
    {
        return eventLog;
    }

    class ServerThread extends Thread{

        public void run()
        {
            //start server
            server.start();
            //server failed/stopped
            server = null;
        }
    }


    public static void main(String[] args)
    {
        launch(args);
    }
}
