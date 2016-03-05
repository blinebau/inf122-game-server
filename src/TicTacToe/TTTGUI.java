//import javafx.application.Application;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.layout.Pane;
//import javafx.stage.Stage;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by Sophia on 3/4/2016.
// */
//public class TTTGUI extends Application {
//
//    private List<TTTPiece> pieces = new ArrayList<>();
//    private TTTEvent[][] board = new TTTEvent[3][3];
//
//    private Pane root = new Pane();
//
//    /**
//     * returns pieces
//     * @return
//     */
//    public List<TTTPiece> getPieces() {
//        return pieces;
//    }
//
//    /**
//     * returns root
//     * @return
//     */
//    public Pane getRoot() {
//        return root;
//    }
//
//    /**
//     * specifies dimensions of board
//     * @return
//     */
//    private Parent createContent() {
//        root.setPrefSize(600, 600);
//
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                TTTEvent tile = new TTTEvent();
//                tile.setTranslateX(j * 200);
//                tile.setTranslateY(i * 200);
//
//                root.getChildren().add(tile);
//
//                board[j][i] = tile;
//            }
//        }
//
//        // horizontal
//        for (int y = 0; y < 3; y++) {
//            pieces.add(new TTTPiece(board[0][y], board[1][y], board[2][y]));
//        }
//
//        // vertical
//        for (int x = 0; x < 3; x++) {
//            pieces.add(new TTTPiece(board[x][0], board[x][1], board[x][2]));
//        }
//
//        // diagonals
//        pieces.add(new TTTPiece(board[0][0], board[1][1], board[2][2]));
//        pieces.add(new TTTPiece(board[2][0], board[1][1], board[0][2]));
//
//        return root;
//    }
//
//    /**
//     * starts up scene (board's gui)
//     * @param primaryStage
//     * @throws Exception
//     */
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        primaryStage.setScene(new Scene(createContent()));
//        primaryStage.show();
//    }
//
//    /**
//     * runs tic-tac-toe
//     * @param args
//     */
//    public static void main(String[] args) {
//        launch(args);
//    }
//}
