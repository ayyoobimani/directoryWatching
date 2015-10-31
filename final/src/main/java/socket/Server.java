package socket;

import entity.EntityEngine;
import entity.model.Person;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by vandermonde on 10/25/15.
 */
public class Server {
    private static int PORTNUMBER = 55555;
    private int NUMBEROFTHREDS = 8;
    private ExecutorService executor;

    public void initiateServer() {
        executor = Executors.newFixedThreadPool(NUMBEROFTHREDS);
        ServerSocket server =  createServer();
        if(server != null)
            listen(server);
    }

    private ServerSocket createServer() {
        ServerSocket ser = null;

        try {
            ser = new ServerSocket(PORTNUMBER);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ser;
    }

    private void listen(ServerSocket server) {
        System.out.println("waiting for new connection");
        while(true) {
           Socket socket = listenforNewConnection(server);
           if(socket != null)
                handleConnection(socket);
        }
    }

    private Socket listenforNewConnection(ServerSocket server) {
        Socket socket = null;
        try {
            socket = server.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return socket;
    }


    private void handleConnection(Socket socket) {
        ClientHandler cl = new ClientHandler(socket);
        executor.execute(cl);
    }

}
