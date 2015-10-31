package webservice.ws;

import entity.EntityEngine;
import entity.model.Person;
import socket.Protocol;

import javax.jws.WebService;

/**
 * Created by vandermonde on 10/25/15.
 */
@WebService(endpointInterface = "webservice.ws.PersonCreator")
public class PersonCreatorImpl implements PersonCreator {
    @Override
    public String createPerson(String line) {
        Person person = Protocol.getPersonObject(line);
        if(person != null) {
            EntityEngine.storePerson(person);
            return "success";
        }
        else{
            return "failure";
        }
    }
}
