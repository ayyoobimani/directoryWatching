package entity.model;

/**
 * Created by vandermonde on 10/22/15.
 */
import javax.persistence.*;


@Entity
@Table(name="person")
public class Person {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="phone_number")
    private String phoneNumber;

    public Person(){
        id = null;
    }

    public Person(String name, String phoneNumber){
        id = null;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {

        this.id = id;
    }

    public String getName()
    {
        return name;
    }
    public void setName(String name) {

        this.name = name;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
}