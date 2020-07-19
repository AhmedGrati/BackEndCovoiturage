package BackEndCovoiturage.Model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Entity(name = "notification")
@Table(name="notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty(message = "the content of the notification should not be empty")
    private String content;
    @NotNull(message = "the date of the notification should not be null")
    private Instant date;
    private boolean isRead;
    @NotEmpty(message = "the username should not be empty")
    private String fullUserName;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @Valid
    private User receiver;



    Notification(){

    }
    public Notification(User receiver,String fullUserName , String content, Instant date, boolean isRead) {
        this.content = content;
        this.date = date;
        this.isRead = isRead;
        this.fullUserName = fullUserName;
        this.receiver = receiver;
    }

    public void setFullUserName(String fullUserName) {
        this.fullUserName = fullUserName;
    }

    public String getFullUserName() {
        return fullUserName;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public long getId() {
        return id;
    }

}
