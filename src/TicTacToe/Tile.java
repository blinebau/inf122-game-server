import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cassiejeansonne on 2/27/16.
 */

public class Tile extends StackPane {
    private Text text = new Text();
    private boolean playable = true;
    private boolean turnX = true;
    private List<TicTacToeLogic> logics = new ArrayList<>();

    private Pane root = new Pane();

    public Tile() {
        Rectangle border = new Rectangle(200, 200);
        border.setFill(null);
        border.setStroke(Color.BLACK);

        text.setFont(Font.font(72));

        setAlignment(Pos.CENTER);
        getChildren().addAll(border, text);

        setOnMouseClicked(event -> {
            if (!playable)
                return;

            if (event.getButton() == MouseButton.PRIMARY) {
                if (!turnX)
                    return;

                drawX();
                turnX = false;
                checkState();
            }
            else if (event.getButton() == MouseButton.SECONDARY) {
                if (turnX)
                    return;

                drawO();
                turnX = true;
                checkState();
            }
        });
    }

    public double getCenterX() {
        return getTranslateX() + 100;
    }

    public double getCenterY() {
        return getTranslateY() + 100;
    }

    public String getValue() {
        return text.getText();
    }

    private void drawX() {
        text.setText("X");
    }

    private void drawO() {
        text.setText("O");
    }

    private void checkState() {
        for (TicTacToeLogic logic : logics) {
            if (logic.isComplete()) {
                playable = false;
                playWinAnimation(logic);
                break;
            }
        }
    }

    private void playWinAnimation(TicTacToeLogic logic) {
        Line line = new Line();
        line.setStartX(logic.getTile(0).getCenterX());
        line.setStartY(logic.getTile(0).getCenterY());
        line.setEndX(logic.getTile(0).getCenterX());
        line.setEndY(logic.getTile(0).getCenterY());

        root.getChildren().add(line);

        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1),
                new KeyValue(line.endXProperty(), logic.getTile(2).getCenterX()),
                new KeyValue(line.endYProperty(), logic.getTile(2).getCenterY())));
        timeline.play();
    }
}