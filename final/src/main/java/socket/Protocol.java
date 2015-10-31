package socket;

import entity.model.Person;

/**
 * Created by vandermonde on 10/25/15.
 */
public class Protocol {
    public static Person getPersonObject(String input){
        Person person = null;
        String[] inStrings = input.split(",");
        if(inStrings.length == 2){
            person = new Person();
            person.setName(inStrings[0].trim());
            person.setPhoneNumber(inStrings[1].trim());
        }
        return person;
    }
}
