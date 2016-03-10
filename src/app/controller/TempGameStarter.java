package app.controller;

import Chess.controller.ChessController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Created by jgreene on 3/9/16.
 */
public class TempGameStarter extends Application { // Using TempGameStarter to give an example of how the controllers will be constructed

    private final String background = "BoardServer/background.jpg";

    private Stage myStage;
    private Scene scene;
    Button playChess;
    Button playCheckers;
    Button playTicTacToe;

    private ChessController chessController;

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        myStage = primaryStage;

        scene = drawTitleMenu();

        myStage.setTitle("TempGameStarter");
        myStage.setScene(scene);
        myStage.show();
    }

    private Scene drawTitleMenu() {

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
        Text menuSubTitle = new Text("Please select a game to play.");
        menuSubTitle.setFont(Font.font("Courier Regular", FontWeight.NORMAL, 30));
        menuSubTitle.setFill(Color.WHITE);
        menuTitle.setFont(Font.font("Courier Regular", FontWeight.NORMAL, 35));
        menuSubTitle.setFont(Font.font("Courier Regular", FontWeight.NORMAL, 15));
        menuText.getChildren().addAll(menuTitle, menuSubTitle);

        menuText.setAlignment(Pos.CENTER);

        VBox gameBtn = new VBox(10);

        playChess = new Button("Chess");
        playCheckers = new Button("Checkers");
        playTicTacToe = new Button("TicTacToe");
        playChess.setMaxWidth(Double.MAX_VALUE);
        playCheckers.setMaxWidth(Double.MAX_VALUE);
        playTicTacToe.setMaxWidth(Double.MAX_VALUE);
        gameBtn.getChildren().addAll(playChess,playCheckers,playTicTacToe);

        titles.add(menuText, 2, 0);
        titles.add(gameBtn, 2, 2);

        clientMenu.getChildren().add(titles);

        playChess.setOnAction(e -> ButtonClicked(e));
        playCheckers.setOnAction(e -> ButtonClicked(e));
        playTicTacToe.setOnAction(e -> ButtonClicked(e));

        return new Scene(clientMenu, 800, 600);
    }

    private void ButtonClicked(ActionEvent e) {
        if(e.getSource() == playChess) {
            // Chess
            System.out.println("Chess");
            chessController = new ChessController(null, myStage);

        } else if (e.getSource() == playCheckers) {
            // Checkers
            System.out.println("Checkers");
            //checkersController = new CheckersController(null, myStage);
        } else if (e.getSource() == playTicTacToe) {
            // TicTacToe
            System.out.println("TTT");
            //tttController = new TicTacToeController(null, myStage);
        }
    }
}
