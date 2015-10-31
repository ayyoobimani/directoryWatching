package client;

import webservice.ws.PersonCreator;
import webservice.ws.PersonCreatorImplService;

import javax.xml.namespace.QName;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by vandermonde on 10/28/15.
 */
public class WebServiceClient extends Client {



    @Override
    public void consumeFile() {
        PersonCreatorImplService pcis = new PersonCreatorImplService();
        PersonCreator personCreator = pcis.getPersonCreatorImplPort();
        BufferedReader bf = getFileReader(path, name);
        sendFileLines(bf, personCreator);
    }




    private void sendFileLines(BufferedReader bf, PersonCreator personCreator) {
        System.out.println("started sending lines");
        String line = null;
        while( (line = readLine(bf)) != null){
            String result = personCreator.createPerson(line);
            if(!result.equals("success"))
                System.out.println("server response for this data: " + line + "was: " + result);
        }
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
