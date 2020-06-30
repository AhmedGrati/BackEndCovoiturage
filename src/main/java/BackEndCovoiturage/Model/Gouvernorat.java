package BackEndCovoiturage.Model;

import com.sun.istack.Nullable;

import javax.persistence.*;
import java.util.ArrayList;

@Entity(name = "gouvernorat")
@Table(name = "gouvernorat")
public class Gouvernorat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Override
    public String toString() {
        return "Gouvernorat{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Nullable
    private String name;


    public Gouvernorat() {

    }

    public Gouvernorat(String name, ArrayList<Ville> villes) {
        this.name = name;
    }

    public long getId() {
        return id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
