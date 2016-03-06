package Chess.Pieces;

import javafx.scene.image.Image;

/**
 * Created by Luke on 3/5/2016.
 */
public class RookPiece extends app.model.Piece{

    public RookPiece(String color){

        if(color.equals("Black")) {
            setImage(new Image("Chess/ImageFiles/Black_rook.png"));
        } else if (color.equals("White")){
            setImage(new Image("Chess/ImageFiles/White_castle.png"));
        }
    }
}
