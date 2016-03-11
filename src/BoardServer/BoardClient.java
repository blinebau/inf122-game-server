package BoardServer;

/**
 * Created by Bryan on 3/1/2016.
 */

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import TicTacToe.TTGUI;
import TicTacToe.WinCombo;
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
    private TTGUI gameGUI;
    //Server, port and username
    private String server, username;
    private String playerStatus;
    public boolean myTurn;
    private int port;

    //console constructor
    public BoardClient(String server, int port, ClientGUI clientGUI) {
        this(server, port, "Anonymous", clientGUI);
    }

    //GUI constructor
    public BoardClient(String server, int port, String username, ClientGUI clientGUI) {
        this.server = server;
        this.port = port;
        this.username = username;
        this.clientGUI = clientGUI;
    }

    public ClientGUI getClientGUI()
    {
        return clientGUI;
    }

    public String getPlayerStatus() {
        return playerStatus;
    }

    public void setGameGUI(TTGUI gui) {
        gameGUI = gui;
    }

    public TTGUI getGameGUI() {
        return gameGUI;
    }

    public void setUsername(String name) {
        username = name;
    }

    public String getUsername() {
        return username;
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
                gameGUI.updateBoard(move);
            } else if (serverMessage instanceof String) {
                String message = (String) serverMessage;
//                if (message.compareTo("Player 1") == 0 || message.compareTo("Player 2") == 0) {
//                    System.out.println(message);
//                    playerStatus = message;
//                    return;
//                }
                myTurn = message.equals("Player 1");
                System.out.println(message);
                playerStatus = message;
            }
        }
    }
}


