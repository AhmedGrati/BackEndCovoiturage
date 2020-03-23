package BackEndCovoiturage.Model;

import com.github.javafaker.Faker;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import static BackEndCovoiturage.Model.Gender.female;
import static BackEndCovoiturage.Model.Gender.male;

@Entity(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    private String firstName;
    private String lastName;

    private int age;
    private double avis;
    private String email;
    private String localisation;
    private String numTel;
    private String status;
    private Date inscriptionDate;
    private Date lastDateEnetered;
    private Gender gender;


    @OneToMany(targetEntity = Covoiturage.class , cascade = CascadeType.ALL)
    @JoinColumn(name = "id" , referencedColumnName = "id")
    private List<Covoiturage> covoiturage;

    public User(){}



    public User (String firstName, String lastName, int age, double avis, String email, String localisation, String numTel, String status, Date inscriptionDate, Date lastDateEnetered, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.avis = avis;
        this.email = email;
        this.localisation = localisation;
        this.numTel = numTel;
        this.status = status;
        this.inscriptionDate = inscriptionDate;
        this.lastDateEnetered = lastDateEnetered;
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getAvis() {
        return avis;
    }

    public void setAvis(double avis) {
        this.avis = avis;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getInscriptionDate() {
        return inscriptionDate;
    }

    public void setInscriptionDate(Date inscriptionDate) {
        this.inscriptionDate = inscriptionDate;
    }

    public Date getLastDateEnetered() {
        return lastDateEnetered;
    }

    public void setLastDateEnetered(Date lastDateEnetered) {
        this.lastDateEnetered = lastDateEnetered;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public long getId(){
        return id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", avis=" + avis +
                ", email='" + email + '\'' +
                ", localisation='" + localisation + '\'' +
                ", numTel='" + numTel + '\'' +
                ", status='" + status + '\'' +
                ", inscriptionDate=" + inscriptionDate +
                ", lastDateEnetered=" + lastDateEnetered +
                ", gender=" + gender +
                '}';
    }

    private static  Faker f = new Faker() ;

    public static User getRandom(){

        return new User(f.name().firstName() ,
                f.name().lastName() ,
                f.number().numberBetween(10 , 70) ,
                f.number().randomDouble(3, 0, 20),
                f.name().name() + "@gmail.com",
                f.address().city(), f.phoneNumber().phoneNumber(),
                f.bool().bool() ?  "online " : "offline ",
                f.date().birthday() ,
                f.date().birthday(),
                f.bool().bool() ?  male : female
        );
    }
}
