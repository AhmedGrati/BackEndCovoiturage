package BackEndCovoiturage.Model;

import javax.persistence.*;
import java.time.Instant;

@Entity(name = "notification")
@Table(name="notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String content;
    private Instant date;
    private boolean isRead;
    private String fullUserName;
    private User receiver;

    Notification(){

    }
    public Notification(User receiver , String fullUserName , String content, Instant date, boolean isRead) {
        this.content = content;
        this.fullUserName = fullUserName;
        this.date = date;
        this.isRead = isRead;

        this.receiver = receiver;
    }

    public String getFullUserName() {
        return fullUserName;
    }

    public void setFullUserName(String fullUserName) {
        this.fullUserName = fullUserName;
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
