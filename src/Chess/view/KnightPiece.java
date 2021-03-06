package Chess.view;

import javafx.scene.image.Image;

/**
 * Created by Luke on 3/5/2016.
 * Knight image piece for chess
 */
public class KnightPiece extends app.model.Piece {

    public KnightPiece(String color){

        if(color.equals("Black")) {
            setImage(new Image("Chess/view/ImageFiles/Black_knight.png"));
        } else if (color.equals("White")){
            setImage(new Image("Chess/view/ImageFiles/White_rook.png"));
        }
    }
}
