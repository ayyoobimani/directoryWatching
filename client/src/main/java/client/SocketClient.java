package client;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by vandermonde on 10/25/15.
 */
public class SocketClient extends Client{

    private static int PONT_NUMBER = 55555;


    @Override
    public void consumeFile() {
        BufferedReader bf = getFileReader(path, name);
        Socket client = createSocket();
        if(client != null && bf != null) {
            PrintWriter out = getPrintWriterFromSocket(client);
            BufferedReader in = getBufferReaderFromSocket(client);

            if(out != null && in != null)
                sendFileLines(bf, out, in);
            closeConnection(client);
        }
    }



    private Socket createSocket(){
        Socket client = null;
        try {
            client = new Socket("localhost", PONT_NUMBER);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return client;
    }

    private PrintWriter getPrintWriterFromSocket(Socket socket){
        PrintWriter out = null;
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out;
    }

    private BufferedReader getBufferReaderFromSocket(Socket socket){
        BufferedReader in = null;
        try {
            in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return in;
    }


    private void closeConnection(Socket client) {
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendFileLines(BufferedReader bf, PrintWriter out, BufferedReader in) {
        System.out.println("started sending lines");
        String line = null;
        while( (line = readLine(bf)) != null){
            out.println(line);
            String response = readLine(in);
            if(response != null) {
                if (response.equals("failure"))
                    System.out.println("fialure message from server when trying to send: " + line);
            }
            else {
                System.out.println("could not get any answer from server, quiting");
                return;
            }
        }
        System.out.println("done sending lines");
    }

    private String readLine(BufferedReader bf) {
        String line = null;
        try {
            line = bf.readLine();
        } catch (IOException e) {
            System.out.println("could not read line from ");
            e.printStackTrace();
        }
        return line;
    }

}
