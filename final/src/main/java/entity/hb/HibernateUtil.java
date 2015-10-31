package entity.hb;

/**
 * Created by vandermonde on 10/22/15.
 */
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory ;
    static {
        Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        sessionFactory = configuration.buildSessionFactory(builder.build());
    }
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    String tablecreation = "CREATE TABLE person( " +
            "id MEDIUMINT NOT NULL AUTO_INCREMENT," +
            "name CHAR(30) NOT NULL," +
            "phone_number CHAR(15)" +
            "PRIMARY KEY (id) )" +
            "character set utf8;";
}