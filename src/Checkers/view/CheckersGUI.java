package Checkers.view;
import java.util.List;

import BoardServer.BoardClient;
import Checkers.controller.CheckersController;
import Checkers.model.CheckerPiece;
import app.controller.BoardGameController;
import app.model.BoardIndex;
import app.model.Piece;
import app.view.*;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;


/**
 * Created by Roy on 3/8/16.
 */
public class CheckersGUI extends GameGUI {

    BorderPane border;

	public CheckersGUI(BoardGameController controller, Piece[][] game2DArray) {
		super(controller, game2DArray);
		board = new BoardGameGridPane(game2DArray.length, game2DArray[0].length, 2, 2, 50, Color.BISQUE, Color.CHOCOLATE, this);
		board.setPieceStartingLayout(game2DArray);

        border = new BorderPane();
//        Group gameStatusGrp = new Group();
        border.setCenter(board);
        this.getChildren().add(border);
	}
	
	public void hightlightTile(List<BoardIndex> pos) {
		board.highlightAssociatedTiles(pos);
	}
	
	public void clearHighlight() {
		board.resetHighlightedTiles();
	}

	public void movePiece(Piece piece, BoardIndex src, BoardIndex dest) {
        board.resetTile(src);
        board.addPieceToTile(dest, piece);
	}
	
	public void removePiece(BoardIndex pos) {
		board.resetTile(pos);
	}
	
	// Called by controller when receiving a finish move from opponent
	public void showMyTurn(boolean myTurn) {
        String msg;
        if (myTurn) {
            msg = "Your turn";
        }
        else {
            msg = "Opponent's turn";
        }

        Text bottom = new Text(msg);
        bottom.setFont(Font.font("Arial", FontWeight.BOLD, 45));
        bottom.setTextAlignment(TextAlignment.CENTER);
        border.setBottom(bottom);
        BorderPane.setAlignment(bottom, Pos.CENTER);

	}
	
	public void updateImgForPiece(BoardIndex pos, CheckerPiece piece) {
		board.addPieceToTile(pos, piece);
	}

    public void showGameOverScreen(boolean winner, BoardClient client){
        String msg;
        if (winner) {
            msg = "You win!";
        }
        else {
            msg = "You lose!";
        }

        Stage stage = (Stage) getScene().getWindow();
        Window owner = getScene().getWindow();
        Alert gameConfirm = new Alert(Alert.AlertType.CONFIRMATION, "");
        gameConfirm.initOwner(owner);
        gameConfirm.initStyle(StageStyle.DECORATED);
        gameConfirm.getDialogPane().setHeaderText(msg);
        gameConfirm.setTitle("Game over");
        gameConfirm.getDialogPane().setContentText("Select 'Ok' to return to the Game Lobby");
        gameConfirm.getDialogPane().getButtonTypes().remove(ButtonType.CANCEL);
        gameConfirm.showAndWait().ifPresent(result -> stage.setScene(client.getClientGUI().drawTitleMenu()));
    }

    @Override
    public void handleMouseEvent(MouseEvent event) {
        if (event.getSource() instanceof Rectangle) {
            System.out.println("Tile Selected");
            Shape tile = (Rectangle) event.getSource();
            controller.tileSelected(board.getBoardIndex(tile));
        }
        else if (event.getSource() instanceof ImageView) {
            System.out.println("Piece Selected");
            ImageView clickedImgView = (ImageView) event.getSource();
            ((CheckersController) controller).pieceSelected(board.getBoardIndex(clickedImgView));
        }

    }

    @Override
    public void handleActionEvent(ActionEvent event) {
    }

}
