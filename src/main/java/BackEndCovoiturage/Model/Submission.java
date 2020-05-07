package BackEndCovoiturage.Model;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity(name = "submission")
@Table(name = "submission")
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Instant submissionDate;

    @ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User owner;

    private Status status;

    public Submission() {

    }
    public Submission(Instant submissionDate, User owner, Status status) {
        this.submissionDate = submissionDate;
        this.owner = owner;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
}
