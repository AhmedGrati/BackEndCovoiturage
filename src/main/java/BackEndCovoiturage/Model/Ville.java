package BackEndCovoiturage.Model;

import com.sun.istack.Nullable;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Entity(name = "ville")
@Table(name = "ville")
public class Ville {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty(message = "name should not be empty")
    private String name;


    @OneToOne(targetEntity = Gouvernorat.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "gouv_id", referencedColumnName = "id")
    @Valid
    private Gouvernorat gouvernorat;


    public Ville() {

    }

    @Override
    public String toString() {
        return "Ville{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gouvernorat=" + gouvernorat +
                '}';
    }

    public Ville(String name, Gouvernorat gouvernorat) {
        this.name = name;
        this.gouvernorat = gouvernorat;
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

    public Gouvernorat getGouvernorats() {
        return gouvernorat;
    }

    public void setGouvernorats(Gouvernorat gouvernorats) {
        this.gouvernorat = gouvernorats;
    }
}
