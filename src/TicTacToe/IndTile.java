package TicTacToe;

import javafx.geometry.Pos;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Created by Sophia on 3/4/2016.
 */
public class IndTile extends StackPane {
    private Text text = new Text();

    /**
     * Creates an individual tile with dimensions
     */
    public IndTile() {
        Rectangle border = new Rectangle(200,200);
        border.setFill(null);
        border.setStroke(Color.BLACK);
        border.setStrokeWidth(5.0);

        text.setFont(Font.font(64));

        setAlignment(Pos.CENTER);
        getChildren().addAll(border, text);

        /**
         * On the event of a mouse clicking on that tile, draws X or O
         */
        setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                drawX();
            } else if (event.getButton() == MouseButton.SECONDARY) {
                drawO();
            }
        });

    }

    /**
     * Draws an X
     */
    private void drawX() {
        text.setText("X");
        text.setStroke(Color.CADETBLUE);
        text.setFill(Color.CADETBLUE);
    }

    /**
     * Draws an O
     */
    private void drawO() {
        text.setText("O");
        text.setStroke(Color.LIGHTGREEN);
        text.setFill(Color.LIGHTGREEN);
    }

}
