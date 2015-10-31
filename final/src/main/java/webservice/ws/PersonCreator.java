package webservice.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Created by vandermonde on 10/25/15.
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface PersonCreator {

    @WebMethod String createPerson(String line);
}
