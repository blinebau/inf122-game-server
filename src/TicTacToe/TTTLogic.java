//import javafx.animation.KeyFrame;
//import javafx.animation.KeyValue;
//import javafx.animation.Timeline;
//import javafx.scene.shape.Line;
//import javafx.util.Duration;
//
///**
// * Created by Sophia on 3/4/2016.
// */
//public class TTTLogic {
//
//    /**
//     * checks if a player has won
//     */
//    public void checkState() {
//        for (TTTPiece piece : pieces) {
//            if (piece.isComplete()) {
//                playable = false;
//                playWinAnimation(piece);
//                break;
//            }
//        }
//    }
//
//    /**
//     * prints out the win animation line
//     * @param combo
//     */
//    private static void playWinAnimation(TTTPiece combo) {
//        Line line = new Line();
//        line.setStartX(combo.getTile(0).getCenterX());
//        line.setStartY(combo.getTile(0).getCenterY());
//        line.setEndX(combo.getTile(0).getCenterX());
//        line.setEndY(combo.getTile(0).getCenterY());
//
//        root.getChildren().add(line);
//
//        Timeline timeline = new Timeline();
//        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1),
//                new KeyValue(line.endXProperty(), combo.getTile(2).getCenterX()),
//                new KeyValue(line.endYProperty(), combo.getTile(2).getCenterY())));
//        timeline.play();
//    }
//}
