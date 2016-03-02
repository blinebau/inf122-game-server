package BoardServer;

/**
 * Created by Bryan on 3/1/2016.
 */

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

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

    //Server, port and username
    private String server, username;
    private int port;

    //console constructor
    public BoardClient(String server, int port, ClientGUI clientGUI)
    {
        this(server, port, "Anonymous", null);
    }

    //GUI constructor
    public BoardClient(String server, int port, String username, ClientGUI clientGUI)
    {
        this.server = server;
        this.port = port;
        //this.username = username;
        this.clientGUI = clientGUI;
    }

    public void setUsername(String name)
    {
        username = name;
    }

    //start the client dialog
    public boolean start()
    {
        //attempt connecting to server using socket
        try {
            socket = new Socket(server, port);
        }catch (Exception e){
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
        try{
            obj_in = new ObjectInputStream(socket.getInputStream());
            obj_out = new ObjectOutputStream(socket.getOutputStream());
        }catch (Exception e){
            e.printStackTrace();
            echo("Failed to create data streams, Exception was thrown");
            return false;
        }

        //create thread that listens to server
        new ListenToServer().start();

/*        try {
            //send the
            obj_out.writeObject(username);
        }catch (Exception e){
            e.printStackTrace();
        }*/
        //connection and stream successfully created
        return true;
    }

    public void echo(String message)
    {
        System.out.println(message);
    }

    public void sendMessage(String message)
    {
        try {
            obj_out.writeObject(message);
        }catch (Exception e){
            echo("Failed to write message to server");
        }
    }

    public void sendMove(Move move)
    {
        try {
            obj_out.writeObject(move);
        }catch (Exception e){
            echo("Failed to write move to server...");

        }
    }

    public void disconnect()
    {
        //close all connections
        try{
            if(obj_out != null) obj_out.close();
        }catch (Exception e){}
        try{
            if(obj_in != null) obj_in.close();
        }catch (Exception e){}
        try{
            if(socket != null) socket.close();
        }catch (SocketException e){
            System.out.println("Socket disconnected, client has exited.");
            System.exit(1);
        }catch (IOException e){
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void main(String [] args)
    {
/*        int portNumber = 4242;
        String serverAddress = "localhost";
        String userName = "Anonymous";
        BoardClient client = new BoardClient(serverAddress, portNumber, userName);

        if(!client.start())
            return;*/

    }

    //waits for messages and then acts on the message (Move eventually)
    class ListenToServer extends Thread
    {
        public void run()
        {
            while (true)
            {
                try {
                    String message = (String) obj_in.readObject();
                    if(clientGUI == null)//if gui exists
                    {
                        System.out.println(message);
                    }
                    else
                    {
                        //do something to gui
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    echo("Server closed this connection...");
                    break;
                }
            }
        }
    }
}

