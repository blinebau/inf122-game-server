package Checkers.model;

import Checkers.controller.CheckersController;
import app.model.*;
import javafx.scene.image.Image;


/**
 * Created by Roy on 3/8/16.
 */
public class CheckerPiece extends Piece{
	
    boolean king;
    CheckersController.PieceColor color;	

    public CheckerPiece (CheckersController.PieceColor c) {
        this.king = false;
        this.color = c;

        if (color == CheckersController.PieceColor.BLACK) {
            setImage(new Image("Checkers/img/black_checkers.png"));
        }
        else {
            setImage(new Image("Checkers/img/white_checkers.png"));
        }
    }
    
    public CheckersController.PieceColor getColor() {
    	return this.color;
    }
    
    public void toKing() {

    	this.king = true;
        if (color == CheckersController.PieceColor.BLACK) {
            setImage(new Image("Checkers/img/black_king.png"));
        }
        else {
            setImage(new Image("Checkers/img/white_king.png"));
        }

    }
    
    public boolean isKing() {
    	return king;
    }
}
