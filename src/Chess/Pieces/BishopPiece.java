package Chess.Pieces;


import javafx.scene.image.Image;

/**
 * Created by Luke on 3/5/2016.
 */
public class BishopPiece extends app.model.Piece {

    public BishopPiece(String color){

        if(color.equals("Black")) {
            setImage(new Image("Chess/ImageFiles/Black_bishop.png"));
        } else if (color.equals("White")){
            setImage(new Image("Chess/ImageFiles/White_bishop.png"));
        }
    }
}
