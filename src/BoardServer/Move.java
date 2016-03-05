package BoardServer;

import java.io.Serializable;

/**
 * Created by Bryan on 3/1/2016.
 */
public class Move implements Serializable{
    String moveMessage = "King me";

    public String getMoveMessage()
    {
        return moveMessage;
    }
}
