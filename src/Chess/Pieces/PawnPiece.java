package Chess.Pieces;

import GeneralGameBoard.BoardObject;
import javafx.scene.image.Image;

/**
 * Created by Luke on 3/5/2016.
 */
public class PawnPiece extends BoardObject {

    public PawnPiece(String color){

        if(color.equals("Black")) {
            setImage(new Image("Chess/ChessPieces/Black_pawn.png"));
        } else if (color.equals("White")){
            setImage(new Image("Chess/ChessPieces/White_pawn.png"));
        }
    }
}
