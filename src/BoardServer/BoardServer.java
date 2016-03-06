package BoardServer;

/**
 * Created by Bryan on 3/1/2016.
 */

import app.model.Move;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Bryan on 3/1/2016.
 */
public class BoardServer {

    //connection unique id
    private static int connection_id;

    //list of current connections
    private ArrayList<ClientThread> clientThreads;

    //possible server gui
    private ServerGUI serverGUI;

    //port listened to be server for connections
    private int port;

    //server running?
    private boolean running;

    public BoardServer(int port)
    {
        this(port, null);
    }

    public BoardServer(int port, ServerGUI serverGUI)
    {
        this.port = port;
        this.serverGUI = serverGUI;
        clientThreads = new ArrayList<>();
    }

    public void start()
    {
        //server is running
        running = true;

        try {
            //create server-side socket
            ServerSocket serverSocket = new ServerSocket(port);

            while (running) {
                echo("Waiting for connections...");
                Socket socket = serverSocket.accept();
                if(!running)//asked to stop on this iteration
                    break;
                ClientThread clientThread = new ClientThread(socket); //create the thread
                clientThreads.add(clientThread); //save it to array list
                clientThread.start();
            }
            try { //after closing server
                serverSocket.close();
                for(ClientThread thread : clientThreads)
                {
                    try{
                        thread.obj_in.close();
                        thread.obj_out.close();
                        thread.socket.close();
                    }catch (Exception e) {
                        e.printStackTrace();
                        System.exit(1);
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
                System.exit(1);
            }
        }catch(Exception e){
            e.printStackTrace();
            System.exit(1);
        }
    }

    public boolean isRunning()
    {
        return running;
    }

    public void stop()
    {
        running = false;
        try {
            new Socket("localhost", port);
        }catch (IOException e){
            //end
        }
    }

    public void echo(String message)
    {
        System.out.println(message);
    }

    public void remove(int connection_id)
    {
        for(ClientThread thread : clientThreads)
        {
            if(thread.id == connection_id) {
                clientThreads.remove(thread);
                return;
            }
        }
    }

    public static void main(String [] arg)
    {
        int portNumber = 4242;
        BoardServer server = new BoardServer(portNumber);
        server.start();
    }

    class ClientThread extends Thread {

        //socket to listen and write
        Socket socket;
        ObjectInputStream obj_in;
        ObjectOutputStream obj_out;

        //unique id for this client thread, easier to send messages and disconnect
        int id;

        //user of the client
        String userName;

        //Move made by the user
        Move move;

        public ClientThread(Socket socket)
        {
            //unique id
            id = ++connection_id;
            //socket client connected to
            this.socket = socket;

            System.out.println("Thread attempting to create Object Streams...");

            try
            {
                //Output created first
                obj_out = new ObjectOutputStream(socket.getOutputStream());
                obj_in = new ObjectInputStream(socket.getInputStream());
                //read a user name
                userName = (String)obj_in.readObject();
                echo(userName + " just connected...");

            }catch(Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        }

        public void run()
        {
            //running indefinitely
            boolean running = true;
            while(running)
            {
                try {
                        Object obj = obj_in.readObject();
                        if(obj instanceof Move) {
                            Move move = (Move) obj;
                            for(ClientThread thread : clientThreads)
                                if(thread.id != id)
                                    thread.obj_out.writeObject(move);
                        }
                        else if(obj instanceof String)
                        {
                            String message = (String) obj;
                            echo(message);
                            if(message.equals("TicTacToe"))
                            {
                                if(id == 1) {
                                    obj_out.writeObject("Player 1");
                                    obj_out.flush();
                                }
                                else
                                {
                                    clientThreads.get(id - 1).obj_out.writeObject("Player 2");
                                }
                            }
                        }
                }catch (Exception e) {
                    e.printStackTrace();
                    System.exit(1);
                }
            }
            //remove self form array list when no long running
            remove(connection_id);
            //close everything
            close();
        }

        //close everything
        private void close()
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
            }catch (Exception e){}
        }

        private void sendMove(String message)//boolean in the future to display global dialog
        {
            //if client is still connected send message
            if(!socket.isConnected()) {
                close();
                // return false;
            }
            try {
                //write message to stream
                for(ClientThread thread : clientThreads)
                {
                    //write to all other active threads (ClientGUIs)
                    if(thread.id != id)
                        thread.obj_out.writeObject(message);
                }
                // obj_out.writeObject(message);
            }catch (Exception e){
                e.printStackTrace();
                System.exit(1);
            }
            //return true;
        }

    }
}

