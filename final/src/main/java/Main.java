import socket.Server;
import webservice.ws.PersonCreatorImpl;

import javax.xml.ws.Endpoint;

/**
 * Created by vandermonde on 10/22/15.
 */


public class Main {

    public static void main(String[] args){
        System.out.println("application started");
        Endpoint.publish("http://localhost:12345/ws/person", new PersonCreatorImpl());

        Server server = new Server();
        server.initiateServer();
    }


}
