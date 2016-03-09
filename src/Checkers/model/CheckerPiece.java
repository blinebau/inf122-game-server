package Checkers.model;

import Checkers.controller.CheckersController;
import app.model.*;


/**
 * Created by Roy on 3/8/16.
 */
public class CheckerPiece extends Piece{
	
    boolean king;
    CheckersController.PieceColor color;	

    public CheckerPiece (CheckersController.PieceColor c) {
        this.king = false;
        this.color = c;
    }
    
    public CheckersController.PieceColor getColor() {
    	return this.color;
    }
    
    public void toKing() {
    	this.king = true;
    }
    
    public boolean isKing() {
    	return king;
    }
}
