package BackEndCovoiturage.Model;

import BackEndCovoiturage.Repository.VilleRepo;
import BackEndCovoiturage.Service.UserService;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.javafaker.Faker;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity(name = "covoiturage")
@Table(name="covoiturage")
public class Covoiturage {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Instant dateDepart;
    private int nbrPlaceDispo;
    private int price;
    private String description;
    private boolean isFumer;

    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User owner;

    @ManyToOne(targetEntity = Gouvernorat.class)
    @JoinColumn(name = "gouv_dep_id", referencedColumnName = "id")
    private Gouvernorat gouvernoratDepart;

    @ManyToOne(targetEntity = Gouvernorat.class)
    @JoinColumn(name = "gouv_arr_id", referencedColumnName = "id")
    private Gouvernorat gouvernoratArrive;

    @ManyToOne(targetEntity = Ville.class)
    @JoinColumn(name = "ville_dep_id", referencedColumnName = "id")
    private Ville villeDepart;

    @ManyToOne(targetEntity = Ville.class)
    @JoinColumn(name = "ville_arr_id", referencedColumnName = "id")
    private Ville villeArrivee;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(targetEntity = Covoiturage.class , cascade = CascadeType.ALL)
    @JoinColumn(name = "submission_id", referencedColumnName = "id")
    private List<Submission> submissions;

    public Covoiturage() {
    }

    public Covoiturage(Instant dateDepart, int nbrPlaceDispo, int price, User owner,String description, boolean isFumer, Gouvernorat gouvernoratDepart, Gouvernorat gouvernoratArrive, Ville villeDepart, Ville villeArrivee) {
        this.dateDepart = dateDepart;
        this.nbrPlaceDispo = nbrPlaceDispo;
        this.price = price;
        this.description = description;
        this.isFumer = isFumer;
        this.gouvernoratDepart = gouvernoratDepart;
        this.gouvernoratArrive = gouvernoratArrive;
        this.villeDepart = villeDepart;
        this.villeArrivee = villeArrivee;
    }

    public static Covoiturage rand(UserService userService, VilleRepo villeRepo) {
        Covoiturage c = new Covoiturage();
        ArrayList<Ville> v = new ArrayList<>();
        villeRepo.findAll().forEach(v::add);
        Collections.shuffle(v);

        c.setVilleDepart(v.get(0));
        c.setVilleArrivee(v.get(1));

        c.setGouvernoratDepart(v.get(0).getGouvernorats());
        c.setGouvernoratArrive(v.get(1).getGouvernorats());

        List<User> users = userService.findAllUsers();
        Collections.shuffle(users);
        c.setOwner(users.get(0));
        c.setFumer(f.bool().bool());
        c.setPrice((int) f.number().randomNumber(2, true));
        c.setNbrPlaceDispo(f.number().numberBetween(1, 5));
        c.dateDepart = Instant.now();

        return c;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Covoiturage{" +
                "id=" + id +
                ", datedepart=" + dateDepart +
                ", nbrPlaceDispo=" + nbrPlaceDispo +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", isFumer=" + isFumer +
                ", gouvernoratDepart=" + gouvernoratDepart +
                ", gouvernoratArrive=" + gouvernoratArrive +
                ", villeDepart=" + villeDepart +
                ", villeArrivee=" + villeArrivee +
                '}';
    }

    public Instant getDateDepart() {
        return dateDepart;
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

    public void setDateDepart(Instant datedepart) {
        this.dateDepart = datedepart;
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


    public static Faker f = new Faker();

    public void setPrice(int price) {
        this.price = price;
    }

    public static class DTO {
        public Instant datedepart;
        public int nbrPlaceDispo;
        public int price;
        public String description;
        public boolean isFumer;
        public int ownerId;
        public String villeDepart;
        public String villeArrive;


        // todo gouverna,t ville


        public Covoiturage toCovoiturage(UserService userService, VilleRepo villeRepo) {
            Covoiturage c = new Covoiturage();
            Ville vd = villeRepo.findVilleByName(villeDepart);
            Ville va = villeRepo.findVilleByName(villeArrive);


            c.setDateDepart(datedepart);
            c.setNbrPlaceDispo(nbrPlaceDispo);
            c.setPrice(price);
            c.setDescription(description);
            c.setFumer(isFumer);
            c.setOwner(userService.findUserById(ownerId));

            c.setVilleDepart(vd);
            c.setVilleArrivee(va);

            c.setGouvernoratDepart(vd.getGouvernorats());
            c.setGouvernoratArrive(va.getGouvernorats());

            return c;
        }
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getOwner() {
        return owner;
    }




}
