package entity;

import entity.hb.HibernateUtil;
import entity.model.Person;
import org.hibernate.Session;

/**
 * Created by vandermonde on 10/25/15.
 */
public class EntityEngine {

    public static void storePerson(Person person){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(person);
        session.getTransaction().commit();
        session.close();
    }
}
