package BackEndCovoiturage.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.javafaker.Faker;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static BackEndCovoiturage.Model.Gender.female;
import static BackEndCovoiturage.Model.Gender.male;

@Entity(name = "user")
@Table(name = "user")
//@JsonIgnoreProperties(value = { "password" })// because it's not secure to return the password of the user so we ignore it and we don't return it
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
    private Instant inscriptionDate;
    private Instant lastDateEnetered;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private String roles;
    private String authorities;
    private Gender gender;
    private boolean hasUrl;
    private String imageUrl;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String resetToken;
    /*@OneToMany(targetEntity = Covoiturage.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private List<Covoiturage> submittedCovoiturages;// liste des covoiturages dont le user est le "owner"*/


    /*@OneToMany(targetEntity = Covoiturage.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private List<Covoiturage> participatedCovoiturage;// liste des covoiturages dont le user est le "participation"*/

    public User() {
    }


    public User(String firstName, String lastName, int age, double avis, String email, String password, String roles, String authorities,
                String localisation, String numTel, String status, Instant inscriptionDate, Instant lastDateEnetered, Gender gender , boolean hasUrl , String imageUrl) {
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
        this.password = password;
        this.authorities = authorities;
        this.roles = roles;
        this.hasUrl = hasUrl;
        this.imageUrl = imageUrl;
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

    public boolean isHasUrl() {
        return hasUrl;
    }

    public void setHasUrl(boolean hasUrl) {
        this.hasUrl = hasUrl;
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

    public static User getRandom(PasswordEncoder passwordEncoder) {
        User u = new User();
        u.firstName = f.name().firstName();
        u.lastName = f.name().lastName();
        u.age = f.number().numberBetween(20, 60);
        u.avis = f.number().numberBetween(0, 100);
        u.email = u.firstName.trim() + "@gmail.com";
        u.numTel = f.phoneNumber().cellPhone();
        u.status = f.bool().bool() ? "Online" : "Offline";
        u.inscriptionDate = Instant.now();
        u.lastDateEnetered = Instant.now();
        u.gender = f.bool().bool() ? male : female;
        u.password = passwordEncoder.encode("default");
        u.hasUrl = false;
        return u;
    }

    public Instant getInscriptionDate() {
        return inscriptionDate;
    }

    public void setInscriptionDate(Instant inscriptionDate) {
        this.inscriptionDate = inscriptionDate;
    }

    public Instant getLastDateEnetered() {
        return lastDateEnetered;
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

    private static Faker f = new Faker();

    public void setLastDateEnetered(Instant lastDateEnetered) {
        this.lastDateEnetered = lastDateEnetered;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles(){
        return roles;
    }
    public String getAuthorities(){
        return authorities;
    }

    public List<String> getAllRoles(){
        if(roles != null){
            return Arrays.asList(roles.split(","));
        }else{
            return new ArrayList<>();
        }
    }


    public List<String> getAllAuthorities(){
        if(authorities != null){
            return Arrays.asList(authorities.split(","));
        }else{
            return new ArrayList<>();
        }
    }

    /*public List<Covoiturage> getSubmittedCovoiturages() {
        return submittedCovoiturages;
    }*/

    /*public List<Covoiturage> getParticipatedCovoiturage() {
        return participatedCovoiturage;
    }*/

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }
}
