package socket;

import entity.EntityEngine;
import entity.model.Person;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by vandermonde on 10/28/15.
 */
public class ClientHandler implements Runnable {
    private Socket _socket;

    public ClientHandler(Socket socket){
        _socket = socket;
    }

    @Override
    public void run() {
        handleConnection();
        closeConnection();
    }

    private void handleConnection() {
        String dataLine = null;
        PrintWriter out = getPrintWriterFromSocket(_socket);
        BufferedReader in = getBufferedReaderFromSocket(_socket);
        System.out.println("getting lines from client");
        if(in != null && out != null) {

            while ((dataLine = readLine(in)) != null) {
                if (dataLine.equals("done"))
                    break;
                storePersonAndReply(out, dataLine);
            }
            System.out.println("done getting lines");
        }

    }

    private void closeConnection(){
        try {
            _socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readLine(BufferedReader in) {
        String line = null;
        try {
            line = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }

    private BufferedReader getBufferedReaderFromSocket(Socket socket) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return br;
    }

    private PrintWriter getPrintWriterFromSocket(Socket socket) {
        PrintWriter pr = null;
        try {
            pr = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pr;
    }

    private void storePersonAndReply(PrintWriter out, String dataLine) {
        Person person = Protocol.getPersonObject(dataLine);
        if(person != null) {
            EntityEngine.storePerson(person);
            out.println("success");
        }
        else{
            out.println("failure");
        }
    }





}
