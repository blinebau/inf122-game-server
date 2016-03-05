package Chess.Pieces;

import GeneralGameBoard.BoardObject;
import javafx.scene.image.Image;

/**
 * Created by Luke on 3/5/2016.
 */
public class KnightPiece extends BoardObject {

    public KnightPiece(String color){

        if(color.equals("Black")) {
            setImage(new Image("Chess/ChessPieces/Black_horse.png"));
        } else if (color.equals("White")){
            setImage(new Image("Chess/ChessPieces/White_horse.png"));
        }
    }
}
