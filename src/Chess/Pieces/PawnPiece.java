package Chess.Pieces;

import javafx.scene.image.Image;

/**
 * Created by Luke on 3/5/2016.
 */
public class PawnPiece extends app.model.Piece {

    public PawnPiece(String color){

        if(color.equals("Black")) {
            setImage(new Image("Chess/ImageFiles/Black_pawn.png"));
        } else if (color.equals("White")){
            setImage(new Image("Chess/ImageFiles/White_pawn.png"));
        }
    }
}
