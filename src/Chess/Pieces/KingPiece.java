package Chess.Pieces;

import GeneralGameBoard.BoardObject;
import javafx.scene.image.Image;

/**
 * Created by Luke on 3/5/2016.
 */
public class KingPiece extends app.model.Piece{

    public KingPiece(String color){

        if(color.equals("Black")) {
            setImage(new Image("Chess/ChessPieces/Black_king.png"));
        } else if (color.equals("White")){
            setImage(new Image("Chess/ChessPieces/White_king.png"));
        }
    }

}
