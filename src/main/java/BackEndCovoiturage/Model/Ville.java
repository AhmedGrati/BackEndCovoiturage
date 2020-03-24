package BackEndCovoiturage.Model;

import com.sun.istack.Nullable;

import javax.persistence.*;

@Entity(name = "ville")
public class Ville {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Nullable
    private String name;


    @OneToOne(targetEntity = Gouvernorat.class , cascade = CascadeType.ALL)
    @JoinColumn(name = "gouv_id",referencedColumnName = "id")
    private Gouvernorat gouvernorat;


    public Ville(){

    }

    public Ville(String name, Gouvernorat gouvernorat) {
        this.name = name;
        this.gouvernorat = gouvernorat;
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

    public Gouvernorat getGouvernorats() {
        return gouvernorat;
    }

    public void setGouvernorats(Gouvernorat gouvernorats) {
        this.gouvernorat = gouvernorats;
    }
}
