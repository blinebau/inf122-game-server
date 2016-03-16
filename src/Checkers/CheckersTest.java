package Checkers;


/**
 * Created by Roy on 3/15/16.
 */

import Checkers.controller.CheckersController;
import javafx.application.Application;
import javafx.stage.Stage;

public class CheckersTest extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        CheckersController cc = new CheckersController();

        primaryStage.setTitle("Checkers Test");
        primaryStage.setScene(cc.getMyScene());
        primaryStage.show();
    }
}
