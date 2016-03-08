package TicTacToe;

import BoardServer.BoardClient;
import app.model.BoardIndex;
import javafx.geometry.Pos;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import app.model.Move;

/**
 * Created by Sophia on 3/4/2016.
 */
public class IndTile extends StackPane {
    private Text text = new Text();
    private String shape;
    private int row;
    private int col;
    private BoardClient client;

    /**
     * Creates an individual tile with dimensions
     */
    public IndTile(int row, int col, String playerNum, BoardClient client) {
        this.row = row;
        this.col = col;
        this.client = client;

        if(playerNum.equals("Player 1"))
            this.shape = "X";
        else
            this.shape = "O";

        Rectangle border = new Rectangle(200, 200);
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
            if (event.getButton() == MouseButton.PRIMARY && client.myTurn) {
                drawMyShape();
                Move move = new Move(new BoardIndex(col, row));
                client.sendMessage(move);
            }
        });

    }

    /**
     * Returns text in tile
     * @return Text text
     */
    public String getValue() {
        return text.getText();
    }

    /**
     * Draws my shape
     */
    public void drawMyShape() {
        if(shape.equals("X")) {
            drawX();
        } else {
            drawO();
        }
        client.myTurn = !client.myTurn;
    }

    /**
     * Draws opponent's shape
     */
    public void drawOpponent() {
        if(shape.equals("X")) {
            drawO();
        } else {
            drawX();
        }
        client.myTurn = !client.myTurn;
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

 /*   public void draw(boolean myMove)
    {
        if(shape.equals("X"))
            drawX();
        else
            drawO();
    }*/

    public void setShape(String shape)
    {
        this.shape = shape;
    }

    public double getCenterX() {
        return getTranslateX() + 100;
    }

    public double getCenterY() {
        return getTranslateY() + 100;
    }
}

