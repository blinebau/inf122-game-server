package app.view;

import app.controller.BoardGameController;
import app.model.Piece;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * Created by Roy on 3/5/16.
 */
public abstract class GameGUI extends Group implements CustomEventHandler{
    protected BoardGameController controller;
    protected BoardGameGridPane board; // will be used with whatever else the developer adds to the Game's GUI
    protected Piece[][] game2DArray;

    public GameGUI() {}

    public GameGUI(BoardGameController controller, Piece[][] game2DArray) {
        this.controller = controller;
        this.game2DArray = game2DArray;
    }

    public BoardGameGridPane getBoard() {
        return board;
    }

    @Override
    public void handle(Event event) { // Determine what type of event was fired - depending on which one is called, call one of the other handle methods

        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {

            handleMouseEvent(((MouseEvent) event));

        } else if (event.getEventType() == ActionEvent.ACTION) {

            handleActionEvent(((ActionEvent) event));
        }
    }

    // Identify the object that was clicked and notify the controller if it is a tile (Rectangle)
    // - Games that select pieces should override this (ex. Look at ChessGUI)
    public void handleMouseEvent(MouseEvent event) {
        if (event.getSource() instanceof Rectangle) {
            Shape clickedTile = (Rectangle) event.getSource();
            controller.tileSelected(board.getBoardIndex(clickedTile));
        }
    }

    public abstract void handleActionEvent(ActionEvent event);
}
