package BackEndCovoiturage.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Gouvernorat")
public class Gouvernorat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @OneToMany(targetEntity = Ville.class , cascade = CascadeType.ALL)
    @JoinColumn(name = "ville_id" , referencedColumnName = "id")
    private List<Ville> villes;

    Gouvernorat(){

    }

    public Gouvernorat(String name, ArrayList<Ville> villes) {
        this.name = name;
        this.villes = villes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ville> getVilles() {
        return villes;
    }

    public void setVilles(List<Ville> villes) {
        this.villes = villes;
    }
}
