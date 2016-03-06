package app.view;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * Created by jgreene on 3/5/16.
 */
public interface CustomEventHandler extends EventHandler<Event> {
    @Override
    // Determine what type of event was fired - depending on which one is called, call one of the other handle methods
    public void handle(Event event);

    // Identify the object that was clicked and fire a PieceClickEvent for ImageViews that are selected
    // Note: The developer can overide this to extend this method if they want (in GAMEGUI)
    public void handleMouseEvent(MouseEvent event);

    // Will be implemented by the developer (depends on their GUI)
    public void handleActionEvent(ActionEvent event);
}
