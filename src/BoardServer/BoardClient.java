package BoardServer;

/**
 * Created by Bryan on 3/1/2016.
 */

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

import Chess.controller.ChessController;
import Chess.model.Player;
import javafx.collections.ObservableList;
import app.controller.BoardGameController;
import app.model.Move;
import javafx.application.Platform;
import javafx.concurrent.Task;

/**
 * Created by Bryan on 3/1/2016.
 */
public class BoardClient {

    //Object streams;
    private ObjectInputStream obj_in;   //read objects from socket
    private ObjectOutputStream obj_out; //write objects to socket
    private Socket socket;

    //Client gui
    private ClientGUI clientGUI = null;
    private BoardGameController boardGameController;

    //Server, port and username
    private String server, username;
    private Boolean hostStatus;
    private int port;

    //Hosted games
    private ObservableList<String> hostClients;

    public BoardClient(String server, int port, ObservableList<String> hostClients, ClientGUI clientGUI)
    {
        this(server, port, "Anonynmous", hostClients, clientGUI);
    }

    //GUI constructor
    public BoardClient(String server, int port, String username, ObservableList<String> hostClients, ClientGUI clientGUI) {
        this.server = server;
        this.port = port;
        this.username = username;
        this.hostClients = hostClients;
        this.clientGUI = clientGUI;
        hostStatus = false;
    }

    public ClientGUI getClientGUI()
    {
        return clientGUI;
    }

    public BoardGameController getBoardGameController() {
        return boardGameController;
    }

    public void setBoardGameController(BoardGameController boardGameController) {
        this.boardGameController = boardGameController;
    }



    public void setUsername(String name) {
        username = name;
    }

    public String getUsername() {
        return username;
    }

    public void setHostStatus(boolean host)
    {
        hostStatus = host;
    }

    public boolean getHostStatus()
    {
        return hostStatus;
    }

    //start the client dialog
    public boolean start() {
        //attempt connecting to server using socket
        try {
            socket = new Socket(server, port);
        } catch (Exception e) {
            e.printStackTrace();
            //display failure message
            echo("Client failed to connect to server");
            return false;
        }

        //displays success message
        String message = "Client successfully connected to server: " + socket.getInetAddress() +
                ":" + socket.getPort();
        echo(message);

        //attempt to create data streams
        try {
            obj_in = new ObjectInputStream(socket.getInputStream());
            obj_out = new ObjectOutputStream(socket.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
            echo("Failed to create data streams, Exception was thrown");
            return false;
        }

        new listenToServer().start();

        //create thread that listens to server

/*        try {
            //send the
            obj_out.writeObject(username);
        }catch (Exception e){
            e.printStackTrace();
        }*/
        //connection and stream successfully created
        return true;
    }

    public void echo(String message) {
        System.out.println(message);
    }

    public void sendMessage(String message) {
        Task worker = new Task() {
            protected Object call() throws Exception {
                try
                {
                    obj_out.writeObject(message);
                    obj_out.flush();
                }

                catch(Exception e)
                {
                    echo("Failed to write message to server");
                }
                return null;
            }

            protected void succeeded() {

            }

            protected void failed() {
                getException().printStackTrace();
            }
        };
        new Thread(worker).start();
    }

    public void sendMessage(Move move) {
        Task worker = new Task() {
            protected Object call() throws Exception {
                try
                {
                    obj_out.writeObject(move);
                    obj_out.flush();
                }

                catch(
                        Exception e
                        )

                {
                    echo("Failed to write move to server");
                }
                return null;
            }

            protected void succeeded() {

            }

            protected void failed() {
                getException().printStackTrace();
            }
        };
        new Thread(worker).start();
    }

    public void disconnect() {
        //close all connections
        try {
            if (obj_out != null) obj_out.close();
        } catch (Exception e) {
        }
        try {
            if (obj_in != null) obj_in.close();
        } catch (Exception e) {
        }
        try {
            if (socket != null) socket.close();
        } catch (SocketException e) {
            System.out.println("Socket disconnected, client has exited.");
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void main(String[] args) {
/*        int portNumber = 4242;
        String serverAddress = "localhost";
        String userName = "Anonymous";
        BoardClient client = new BoardClient(serverAddress, portNumber, userName);

        if(!client.start())
            return;*/

    }

    //waits for messages and then acts on the message (Move eventually)
    class listenToServer extends Thread {
        public void run() {
            while (true) {
                try {
                    Object obj = obj_in.readObject();
                    Platform.runLater(() -> handleServerMessage(obj));
                } catch (Exception e) {
                    echo("Connection to Server severed...");
                    break;
                }
            }
        }

        public void handleServerMessage(Object serverMessage)
        {
            if (serverMessage instanceof Move) {
                Move move = (Move) serverMessage;
                boardGameController.moveReceived(move);
            } else if (serverMessage instanceof String) {
                String message = (String) serverMessage;
                String[] tokens = message.split(";");
                switch (tokens[0])
                {
                    case "NEW_HOST":
                        hostClients.add(tokens[1] + " : " + tokens[2]);
                        break;
                    case "CANCEL_HOST":
                        hostClients.remove(hostClients.get(hostClients.indexOf(tokens[1])));
                        break;
                    case "CHESS":
                        boardGameController = new ChessController(BoardClient.this);
                        clientGUI.getStage().setScene(boardGameController.getMyScene());
                        String playerStatus = hostStatus ? "Player 1" : "Player 2";
                        clientGUI.getStage().setTitle(username + " : " + playerStatus);
                }
            }
        }
    }
}
