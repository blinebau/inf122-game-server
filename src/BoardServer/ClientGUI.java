package BoardServer;

import Chess.controller.ChessController;
import TicTacToe.TTGUI;
import TicTacToe.TTTController;
import app.view.GameGUI;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;

/**
 * Created by Bryan on 2/28/2016.
 */
public class ClientGUI extends Application {

    private Stage stage;
    private Scene entryScene;
    private Scene titleScene;

    private BoardClient userClient;
    private String defHost;
    private int defPort;

    private final String background = "BoardServer/background.jpg";

    public ClientGUI()
    {
        defHost = "localhost";
        defPort = 4242;
        userClient = new BoardClient(defHost, defPort, this);
    }

    public Stage getStage()
    {
        return stage;
    }

/*    public ClientGUI(String hostName, int port) {
        defHost = hostName;
        defPort = port;
        userClient = new BoardClient("localhost", defPort, self);
    }*/

    public void start(Stage stage)
    {
        this.stage = stage;
        entryScene = drawClientEntry();
        stage.setScene(entryScene);
        stage.show();
    }

    public Scene drawClientEntry() {
        //Create client window
        stage.setTitle("Board Game Application");
        stage.getIcons().add(new Image("BoardServer/chess_logo.png"));

        // Group clientPortal = new Group();
        StackPane clientPortal = new StackPane();
        Canvas cClient = new Canvas(800, 600);
        clientPortal.getChildren().add(cClient);
        GraphicsContext gClient = cClient.getGraphicsContext2D();
        gClient.drawImage(new Image(background), 0, 0);

        Group formGroup = new Group();

        //Create form nodes
        //Create form title
        Text formTitle = new Text("Welcome to Board Game Server!");
        formTitle.setFont(Font.font("Courier Regular", FontWeight.NORMAL, 40));
        formTitle.setId("formTitle");

        formGroup.getChildren().add(formTitle);

        GridPane formGrid = new GridPane();
        formGrid.setVgap(10);
        formGrid.setHgap(10);
        formGrid.setPadding(new Insets(25));

        //Create entry label
        Label playerName = new Label("Enter your Player Name: ");
        playerName.setTextFill(Color.WHITE);
        playerName.setId("playerName");
        TextField playerNameTextField = new TextField();
        formGrid.add(playerName, 0, 0);
        formGrid.add(playerNameTextField, 1, 0);

        HBox formBtn = new HBox(10);

        //Create form button
        Button signIn = new Button("Sign in");
        Button clearForm = new Button("Clear");
        formBtn.getChildren().addAll(signIn,clearForm);
        formGrid.add(formBtn, 1, 1);

        formGroup.getChildren().add(formGrid);
        clientPortal.getChildren().add(formGroup);


        Scene portalScene = new Scene(clientPortal, 800, 600);

        signIn.setOnAction(event -> {
                if (playerNameTextField.getText() != null && !playerNameTextField.getText().isEmpty()) {
                    String userName = playerNameTextField.getText();
                    userClient.setUsername(userName);
                    if(!userClient.start())
                        return;
                    userClient.sendMessage(userName);
                    stage.setScene(drawTitleMenu());
                }
            });

        clearForm.setOnAction(event -> playerNameTextField.clear());

        return portalScene;
    }

    public Scene drawTitleMenu() {

        Scene scene;

        StackPane clientMenu = new StackPane();
        Canvas cClient = new Canvas(800, 600);
        clientMenu.getChildren().add(cClient);
        GraphicsContext gClient = cClient.getGraphicsContext2D();
        gClient.drawImage(new Image(background), 0, 0);

        GridPane titles = new GridPane();
        titles.setAlignment(Pos.CENTER);
        titles.setVgap(10);
        titles.setHgap(10);
        titles.setPadding(new Insets(25, 25, 25, 15));

        VBox menuText = new VBox(5);

        Text menuTitle = new Text("Board Game Server");
        menuTitle.setFont(Font.font("Courier Regular", FontWeight.NORMAL, 40));
        menuTitle.setStroke(Color.BLACK);
        menuTitle.setFill(Color.WHITE);
        //menuTitle.setId("menuTitle");
        Text menuSubTitle = new Text("Please select a game to play.");
        menuSubTitle.setFont(Font.font("Courier Regular", FontWeight.NORMAL, 30));
        menuSubTitle.setFill(Color.WHITE);
        //menuSubTitle.setId("menuSubTitle");
        menuTitle.setFont(Font.font("Courier Regular", FontWeight.NORMAL, 35));
        menuSubTitle.setFont(Font.font("Courier Regular", FontWeight.NORMAL, 15));
        menuText.getChildren().addAll(menuTitle, menuSubTitle);

        menuText.setAlignment(Pos.CENTER);

        VBox gameBtn = new VBox(10);
        //gameBtn.setAlignment(Pos.BOTTOM_CENTER);

        Button playChess = new Button("Chess");
        Button playCheckers = new Button("Checkers");
        Button playTicTacToe = new Button("TicTacToe");
        Button quitBtn = new Button("Quit to Desktop");
        playChess.setMaxWidth(Double.MAX_VALUE);
        playCheckers.setMaxWidth(Double.MAX_VALUE);
        playTicTacToe.setMaxWidth(Double.MAX_VALUE);
        quitBtn.setMaxWidth(Double.MAX_VALUE);
        gameBtn.getChildren().addAll(playChess,playCheckers,playTicTacToe, quitBtn);

        titles.add(menuText, 2, 0);
        titles.add(gameBtn, 2, 2);

        clientMenu.getChildren().add(titles);


        playChess.setOnAction(event -> playChess());

/*        playCheckers.setOnAction((ActionEvent event) -> {
                Move move = new Move();
                userClient.sendMove(move);
        });*/

        playTicTacToe.setOnAction(event -> playTicTacToe());

        quitBtn.setOnAction((ActionEvent event) -> {
            userClient.disconnect();
            Platform.exit();
        });

        return new Scene(clientMenu, 800, 600);
    }

    public void playTicTacToe()
    {
        String message = "TicTacToe";
        Task worker = new Task() {
            protected Object call() {
                userClient.sendMessage(message);
                try {
                    Thread.sleep(500);
                }catch (Exception e){
                    e.printStackTrace();
                }
                System.out.println("test");
                return null;
            }
        };

        worker.setOnSucceeded(e -> {
            TTTController controller = new TTTController(userClient);
            userClient.setBoardGameController(controller);
            stage.setScene(controller.getGameGUI().getScene());
            stage.setTitle(message + " - " + userClient.getPlayerStatus() + ": " + userClient.getUsername());
        });

        new Thread(worker).start();
    }

    public void playChess(){
        String message = "Chess";
        Task worker = new Task() {
            protected Object call() {
                userClient.sendMessage(message);
                try {
                    Thread.sleep(500);
                }catch (Exception e){
                    e.printStackTrace();
                }
                System.out.println("test");
                return null;
            }
        };

        worker.setOnSucceeded(e -> {
            ChessController chessController = new ChessController(userClient.getPlayerStatus(),
                    userClient);
            userClient.setBoardGameController(chessController);
            stage.setScene(userClient.getBoardGameController().getMyScene());
            stage.setTitle(message + " - " + userClient.getPlayerStatus() + ": " + userClient.getUsername());
        });

        new Thread(worker).start();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
