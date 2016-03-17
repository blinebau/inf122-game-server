package BoardServer;

import javafx.application.Application;
import javafx.stage.StageStyle;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
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

    //Primary JavaFX Stage: Window of the Application
    private Stage stage;

    //Beginning Scene of the Application, Login screen the user first sees
    private Scene entryScene;

    //User's Client
    private BoardClient userClient;

    //Hosting Server's identifying name
    private String defHost;

    //Local port in which to connect to Server
    private int defPort;

    //Tabular view of currently hosted games in the Game Lobby
    private ListView<String> lobby = new ListView<>();

    //Collection holdings the values of hosted games: Host's username and the game's name
    private ObservableList<String> hostClients = FXCollections.observableArrayList();

    //Height of each cell in the ListView
    private final int ROW_HEIGHT = 48;

    //Background Image of the Application Window
    private final String background = "BoardServer/background.jpg";

    //Default Constructor
    public ClientGUI()
    {
        defHost = "localhost";
        defPort = 4242;
        userClient = new BoardClient(defHost, defPort, hostClients, this);
    }

    //Accessor of Stage attribute
    public Stage getStage()
    {
        return stage;
    }

    //Overriding start(Stage stage) method of JavaFx.Application, starting point of Application thread
    public void start(Stage stage)
    {
        this.stage = stage;
        entryScene = drawClientEntry();
        stage.setScene(entryScene);
        stage.initStyle(StageStyle.DECORATED);
        stage.setOnCloseRequest(e -> System.exit(0));
        stage.show();

    }

    //Produces the Scene representing the entry point of the program: Login screen
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

        //Outer root of the form nodes
        Group formGroup = new Group();

        //Create form title
        Text formTitle = new Text("Welcome to Board Game Server!");
        formTitle.setFont(Font.font("Courier Regular", FontWeight.NORMAL, 40));
        formTitle.setId("formTitle");

        //Add title to root
        formGroup.getChildren().add(formTitle);

        //Create form layouts
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

        //Add nodes to root -> root to layout StackPane
        formGroup.getChildren().add(formGrid);
        clientPortal.getChildren().add(formGroup);

        //Initialize Scene to StackPane Parent node
        Scene portalScene = new Scene(clientPortal, 800, 600);

        //EventHandler for Sign in button interaction
        //Sign user into BoardServer under a entered username
        signIn.setOnAction(e -> {
                if (playerNameTextField.getText() != null && !playerNameTextField.getText().isEmpty()) {
                    String userName = playerNameTextField.getText();
                    userClient.setUsername(userName);
                    if(!userClient.start())
                        return;
                    userClient.sendMessage(userName);
                    stage.setScene(drawTitleMenu());
                }
            });

        //EventHandler for Clear Form button interaction
        //Clear form of current entry value
        clearForm.setOnAction(e -> playerNameTextField.clear());

        //Return generated Scene
        return portalScene;
    }

    //Produces the Scene representing the main lobby of the program: Title menu
    //User will host and join games from this screen returning to it after a game is completed
    public Scene drawTitleMenu() {

        //Clears the Game Lobby of hosted games after a game is completed
        hostClients.clear();

        //Parent layout of the Scene
        StackPane clientMenu = new StackPane();
        //Canvas layout used to draw a background image
        Canvas cClient = new Canvas(800, 600);
        clientMenu.getChildren().add(cClient);
        GraphicsContext gClient = cClient.getGraphicsContext2D();
        gClient.drawImage(new Image(background), 0, 0);

        //Node used to hold text titles for the Title Menu options
        GridPane titles = new GridPane();
        titles.setAlignment(Pos.CENTER_RIGHT);
        titles.setVgap(10);
        titles.setHgap(10);
        titles.setPadding(new Insets(5, 18, 25, 0));

        //CSS Style of the title Node
        titles.setStyle("-fx-background-color: white;-fx-background-radius: 3.0;-fx-border-color: silver;" +
                "-fx-border-style: solid;-fx-border-width: 5.0");

        VBox menuText = new VBox(5);

        //Main title of the menu
        Text menuTitle = new Text("Board Game Server");
        menuTitle.setFont(Font.font("Courier Regular", FontWeight.NORMAL, 40));
        menuTitle.setStroke(Color.BLACK);
        menuTitle.setFill(Color.BLACK);

        //Sub title of the menu
        Text menuSubTitle = new Text("Please select a game to play.");
        menuSubTitle.setFont(Font.font("Courier Regular", FontWeight.NORMAL, 30));
        menuSubTitle.setFill(Color.BLACK);
        //menuSubTitle.setId("menuSubTitle");
        menuTitle.setFont(Font.font("Courier Regular", FontWeight.NORMAL, 35));
        menuSubTitle.setFont(Font.font("Courier Regular", FontWeight.NORMAL, 15));
        menuText.getChildren().addAll(menuTitle, menuSubTitle);

        menuText.setAlignment(Pos.CENTER);

        VBox gameBtn = new VBox(10);

        //Game buttons for hosting a particular game
        Button playChess = new Button("Chess");
        Button playCheckers = new Button("Checkers");
        Button playTicTacToe = new Button("Tic-Tac-Toe");
        //Closes client and disconnects from server
        Button quitBtn = new Button("Quit to Desktop");
        playChess.setMaxWidth(Double.MAX_VALUE);
        playCheckers.setMaxWidth(Double.MAX_VALUE);
        playTicTacToe.setMaxWidth(Double.MAX_VALUE);
        quitBtn.setMaxWidth(Double.MAX_VALUE);
        gameBtn.getChildren().addAll(playChess,playCheckers,playTicTacToe, quitBtn);

        titles.add(menuText, 2, 0);
        titles.add(gameBtn, 2, 2);

        //Placeholder text indicating an empty listview
        lobby.setPlaceholder(new Label("No Currently Hosted Games"));
        lobby.setItems(hostClients);
        lobby.setPrefHeight(4 * ROW_HEIGHT);

        //Factory for creating each Cell and it's properties
        lobby.setCellFactory(param -> new HostCell());

        //Node for Game Lobby list and Border
        GridPane lobbyGrid = new GridPane();
        lobbyGrid.setAlignment(Pos.CENTER_LEFT);
        lobbyGrid.setPadding(new Insets(25, 25, 25, 15));
        lobbyGrid.getStyleClass().add("lobbyGrid");

        //
        lobbyGrid.setStyle("-fx-background-color: white;-fx-background-radius: 3.0;-fx-border-color: silver;" +
                "-fx-border-style: solid;-fx-border-width: 5.0");

        HBox labelBox = new HBox(20);
        labelBox.setStyle("-fx-border-color: silver;-fx-border-style: solid;-fx-border-width: 2.0;" +
                "-fx-background-color: silver;");

        Label lobbyLabel = new Label("Game Lobby");
        lobbyLabel.setPadding(new Insets(0, 0, 0, 5));
        lobbyLabel.setTextFill(Color.BLACK);

  /*      Button testAddBtn = new Button("Add a Cell");
        testAddBtn.setOnAction(e -> {
            userClient.setHostStatus(true);
            hostClients.add(userClient.getUsername());
            userClient.sendMessage("NEW_HOST");
        });
*/
        labelBox.getChildren().add(lobbyLabel);
//        labelBox.getChildren().add(testAddBtn);

        lobbyGrid.add(labelBox, 0, 0);
        lobbyGrid.add(lobby, 0, 1);

        Pane frontPane = new Pane();
        titles.setLayoutX(400);
        titles.setLayoutY(120);
        lobbyGrid.setLayoutY(120);
        lobbyGrid.setLayoutX(75);

        frontPane.getChildren().addAll(titles, lobbyGrid);
        clientMenu.getChildren().add(frontPane);


        playChess.setOnAction(event -> playChess());

        playCheckers.setOnAction(event -> playCheckers());
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
        String message = "NEW_HOST;" + userClient.getUsername() + ";Tic-Tac-Toe";
        userClient.setHostStatus(true);
        hostClients.add(userClient.getUsername() + " : Tic-Tac-Toe");
        Platform.runLater(() -> userClient.sendMessage(message) );
        /*Task worker = new Task() {
            protected Object call() {
                userClient.sendMessage(message);
                try {
                    Thread.sleep(200);
                }catch (Exception e){
                    e.printStackTrace();
                }
                return null;
            }
        };

        worker.setOnSucceeded(e -> {
*//*            TTTController controller = new TTTController(userClient);
            userClient.setBoardGameController(controller);
            stage.setScene(userClient.getBoardGameController().getMyScene()*//**//*controller.getGameGUI().getScene()*//**//*);
            stage.setTitle(message + " - " + userClient.getPlayerStatus() + ": " + userClient.getUsername());*//*
        });

        new Thread(worker).start();*/
    }

    public void playChess(){
        String message = "NEW_HOST;" + userClient.getUsername() + ";Chess";
        userClient.setHostStatus(true);
        hostClients.add(userClient.getUsername() + " : Chess");
        Platform.runLater(() -> userClient.sendMessage(message) );
        /*String message = "Chess";
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
            ChessController chessController = new ChessController(userClient);
            userClient.setBoardGameController(chessController);
            stage.setScene(userClient.getBoardGameController().getMyScene());
            stage.setTitle(message + " - " + userClient.getPlayerStatus() + ": " + userClient.getUsername());
        });

        new Thread(worker).start();*/
    }

    public void playCheckers() {
        String message = "NEW_HOST;" + userClient.getUsername() + ";Checkers";
        userClient.setHostStatus(true);
        hostClients.add(userClient.getUsername() + " : Checkers");
        Platform.runLater(() -> userClient.sendMessage(message) );
/*        String message = "Checkers";
        Task worker = new Task() {
            protected Object call() {
                userClient.sendMessage(message);
                try {
                    Thread.sleep(500);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("Test");
                return null;
            }
        };

        worker.setOnSucceeded(e -> {
            CheckersController controller = new CheckersController(userClient);
            userClient.setBoardGameController(controller);
            stage.setScene(userClient.getBoardGameController().getMyScene()*//*controller.getGameGUI().getScene()*//*);
            stage.setTitle(message + " - " + userClient.getPlayerStatus() + ": " + userClient.getUsername());
        });

        new Thread(worker).start();*/
    }

    class HostCell extends ListCell<String> {
        HBox cell = new HBox();
        Label label = new Label("empty");
        Pane pane = new Pane();
        Button lobbyBtn = new Button("empty");
        String lastCell;

        public HostCell()
        {
            super();
            cell.getChildren().addAll(label, pane, lobbyBtn);
            HBox.setHgrow(pane, Priority.ALWAYS);
            lobbyBtn.setOnAction(e -> {
                if(lobbyBtn.getText().equals("Join Game"))
                {
                    userClient.sendMessage("JOIN_HOST;" + lastCell);
                }
                else if(lobbyBtn.getText().equals("Cancel Game"))
                {
                    userClient.sendMessage("CANCEL_HOST;" + lastCell);
                    hostClients.remove(hostClients.get(hostClients.indexOf(lastCell)));
                }
            });
        }

        public void updateItem(String gameInfo, boolean empty)
        {
            super.updateItem(gameInfo, empty);
            setText(null);
            if(empty)
            {
                lastCell = null;
                setGraphic(null);
            }
            else
            {
                String[] tokens = gameInfo.split(" : ");
                String hostName = tokens[0];
                if(userClient.getHostStatus() && hostName.equals(userClient.getUsername()))
                    lobbyBtn.setText("Cancel Game");
                else
                    lobbyBtn.setText("Join Game");
                lastCell = gameInfo;
                label.setText(gameInfo);
                setGraphic(cell);
            }
        }
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
