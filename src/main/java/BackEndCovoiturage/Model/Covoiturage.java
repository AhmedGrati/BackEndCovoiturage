package BackEndCovoiturage.Model;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "covoiturage")

public class Covoiturage {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Date datedepart;
    private int nbrPlaceDispo;
    private double price;
    private String description;
    private boolean isFumer;

    @OneToOne(targetEntity = User.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User owner;

    @ManyToOne(targetEntity = Gouvernorat.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "gouv_dep_id", referencedColumnName = "id")
    private Gouvernorat gouvernoratDepart;

    @ManyToOne(targetEntity = Gouvernorat.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "gouv_arr_id", referencedColumnName = "id")
    private Gouvernorat gouvernoratArrive;

    @ManyToOne(targetEntity = Ville.class , cascade = CascadeType.ALL)
    @JoinColumn(name = "ville_dep_id" , referencedColumnName = "id")
    private Ville villeDepart;

    @ManyToOne(targetEntity = Ville.class , cascade = CascadeType.ALL)
    @JoinColumn(name = "ville_arr_id" , referencedColumnName = "id")
    private Ville villeArrivee;

    public Covoiturage(){ }

    public Covoiturage(Date datedepart, int nbrPlaceDispo, double price, String description, boolean isFumer, User owner, Gouvernorat gouvernoratDepart, Gouvernorat gouvernoratArrive, Ville villeDepart, Ville villeArrivee) {
        this.datedepart = datedepart;
        this.nbrPlaceDispo = nbrPlaceDispo;
        this.price = price;
        this.description = description;
        this.isFumer = isFumer;
        this.owner = owner;
        this.gouvernoratDepart = gouvernoratDepart;
        this.gouvernoratArrive = gouvernoratArrive;
        this.villeDepart = villeDepart;
        this.villeArrivee = villeArrivee;
    }

    @Override
    public String toString() {
        return "Covoiturage{" +
                "id=" + id +
                ", datedepart=" + datedepart +
                ", nbrPlaceDispo=" + nbrPlaceDispo +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", isFumer=" + isFumer +
                ", owner=" + owner +
                ", gouvernoratDepart=" + gouvernoratDepart +
                ", gouvernoratArrive=" + gouvernoratArrive +
                ", villeDepart=" + villeDepart +
                ", villeArrivee=" + villeArrivee +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDatedepart() {
        return datedepart;
    }

    public void setDatedepart(Date datedepart) {
        this.datedepart = datedepart;
    }

    public int getNbrPlaceDispo() {
        return nbrPlaceDispo;
    }

    public void setNbrPlaceDispo(int nbrPlaceDispo) {
        this.nbrPlaceDispo = nbrPlaceDispo;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFumer() {
        return isFumer;
    }

    public void setFumer(boolean fumer) {
        isFumer = fumer;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Gouvernorat getGouvernoratDepart() {
        return gouvernoratDepart;
    }

    public void setGouvernoratDepart(Gouvernorat gouvernoratDepart) {
        this.gouvernoratDepart = gouvernoratDepart;
    }

    public Gouvernorat getGouvernoratArrive() {
        return gouvernoratArrive;
    }

    public void setGouvernoratArrive(Gouvernorat gouvernoratArrive) {
        this.gouvernoratArrive = gouvernoratArrive;
    }

    public Ville getVilleDepart() {
        return villeDepart;
    }

    public void setVilleDepart(Ville villeDepart) {
        this.villeDepart = villeDepart;
    }

    public Ville getVilleArrivee() {
        return villeArrivee;
    }

    public void setVilleArrivee(Ville villeArrivee) {
        this.villeArrivee = villeArrivee;
    }


    public static class DTO {
        public Date datedepart;
        public int nbrPlaceDispo;
        public double price;
        public String description;
        public boolean isFumer;
        public int ownerId;

        // todo gouverna,t ville


        public Covoiturage toCovoiturage() {
            Covoiturage c = new Covoiturage();
            c.setDatedepart(datedepart);
            c.setNbrPlaceDispo(nbrPlaceDispo);
            c.setPrice(price);
            c.setDescription(description);
            c.setFumer(isFumer);
            return c;
        }
    }

}
