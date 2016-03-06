package Chess.Pieces;

import javafx.scene.image.Image;

/**
 * Created by Luke on 3/5/2016.
 */
public class KnightPiece extends app.model.Piece {

    public KnightPiece(String color){

        if(color.equals("Black")) {
            setImage(new Image("Chess/ImageFiles/Black_knight.png"));
        } else if (color.equals("White")){
            setImage(new Image("Chess/ImageFiles/White_rook.png"));
        }
    }
}
