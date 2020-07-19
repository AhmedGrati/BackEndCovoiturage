package BackEndCovoiturage.Model;

import BackEndCovoiturage.Repository.CovoiturageRepo;
import BackEndCovoiturage.Repository.SubmissionRepo;
import BackEndCovoiturage.Repository.UserRepo;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Collections;
import java.util.List;

@Entity(name = "submission")
@Table(name = "submission")
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull(message = "submission date should not be null")
    private Instant submissionDate;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    @Valid
    private User owner;

    @ManyToOne(targetEntity = Covoiturage.class)
    @JoinColumn(name = "covoiturage_id", referencedColumnName = "id")
    @Valid
    private Covoiturage covoiturage;

    @NotNull(message = "status should not be null")
    private Status status;

    public Submission() {

    }
    public Submission(Instant submissionDate, User owner, Status status , Covoiturage covoiturage) {
        this.submissionDate = submissionDate;
        this.owner = owner;
        this.status = status;
        this.covoiturage = covoiturage;
    }

    public long getId() {
        return id;
    }


    public Instant getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Instant submissionDate) {
        this.submissionDate = submissionDate;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Covoiturage getCovoiturage() {
        return covoiturage;
    }

    public void setCovoiturage(Covoiturage covoiturage) {
        this.covoiturage = covoiturage;
    }

    @Override
    public String toString() {
        return "Submission{" +
                "id=" + id +
                ", submissionDate=" + submissionDate +
                ", owner=" + owner +
                ", covoiturage=" + covoiturage +
                ", status=" + status +
                '}';
    }


    public static void rand(UserRepo userRepo, CovoiturageRepo covoiturageRepo, SubmissionRepo submissionRepo) {
        Submission s = new Submission();

        List<User> users = userRepo.findAll();
        Collections.shuffle(users);

        List<Covoiturage> covoiturages = covoiturageRepo.findAll();
        Collections.shuffle(covoiturages);

        s.covoiturage = covoiturages.get(0);
        s.owner = users.get(0);
        s.status = Status.pending;
        submissionRepo.save(s);
    }
}
