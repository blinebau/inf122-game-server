//import javafx.geometry.Pos;
//import javafx.scene.input.MouseButton;
//import javafx.scene.layout.StackPane;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Rectangle;
//import javafx.scene.text.Font;
//import javafx.scene.text.Text;
//
///**
// * Created by Sophia on 3/4/2016.
// */
//public class TTTEvent extends StackPane{
//
//    private boolean playable = true;
//    private boolean turnX = true;
//    private Text text = new Text();
//
//    /**
//     * sets up an event, if a place on the board is clicked, it'll determine if it's a playable location and which "piece" to put down
//     */
//    public TTTEvent() {
//        Rectangle border = new Rectangle(200, 200);
//        border.setFill(null);
//        border.setStroke(Color.BLACK);
//
//        text.setFont(Font.font(72));
//
//        setAlignment(Pos.CENTER);
//        getChildren().addAll(border, text);
//
//        setOnMouseClicked(event -> {
//            if (!playable)
//                return;
//
//            if (event.getButton() == MouseButton.PRIMARY) {
//                if (!turnX)
//                    return;
//
//                drawX();
//                turnX = false;
//                checkState();
//            } else if (event.getButton() == MouseButton.SECONDARY) {
//                if (turnX)
//                    return;
//
//                drawO();
//                turnX = true;
//                checkState();
//            }
//        });
//    }
//
//    /**
//     * sets playable
//     * @param newValue
//     */
//    public void setPlayable(boolean newValue) {
//        playable = newValue;
//    }
//
//    public double getCenterX() {
//        return getTranslateX() + 100;
//    }
//
//    public double getCenterY() {
//        return getTranslateY() + 100;
//    }
//
//    /**
//     * returns text
//     * @return
//     */
//    public String getValue() {
//        return text.getText();
//    }
//
//    /**
//     * draws an X on piece
//     */
//    private void drawX() {
//        text.setText("X");
//    }
//
//    /**
//     * draws an O on piece
//     */
//    private void drawO() {
//        text.setText("O");
//    }
//
//}
