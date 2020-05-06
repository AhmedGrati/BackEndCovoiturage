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

}
