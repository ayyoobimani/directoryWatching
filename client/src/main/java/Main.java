import client.*;
import file.DirectoryWatcher;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

/**
 * Created by vandermonde on 10/25/15.
 */
public class Main {

    public static void main(String[] args){
        Client socketClient = new SocketClient();
        Client webServiceClient = new WebServiceClient();
        Path path = FileSystems.getDefault().getPath(System.getProperty("user.home"), "Desktop");;
        try {
            //change this to use WebService client!!
            new DirectoryWatcher(path, webServiceClient);
            //new DirectoryWatcher(path, socketClient);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
