package Chess.Pieces;

import GeneralGameBoard.BoardObject;
import javafx.scene.image.Image;

/**
 * Created by Luke on 3/5/2016.
 */
public class BishopPiece extends BoardObject {

    public BishopPiece(String color){

        if(color.equals("Black")) {
            setImage(new Image("Chess/ChessPieces/Black_bishop.png"));
        } else if (color.equals("White")){
            setImage(new Image("Chess/ChessPieces/White_bishop.png"));
        }
    }
}