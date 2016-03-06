package Chess.Pieces;

import javafx.scene.image.Image;

/**
 * Created by Luke on 3/5/2016.
 */
public class QueenPiece extends app.model.Piece {

    public QueenPiece(String color){

        if(color.equals("Black")) {
            setImage(new Image("Chess/ImageFiles/Black_queen.png"));
        } else if (color.equals("White")){
            setImage(new Image("Chess/ImageFiles/White_queen.png"));
        }
    }
}
