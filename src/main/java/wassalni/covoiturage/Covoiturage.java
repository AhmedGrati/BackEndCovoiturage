package wassalni.covoiturage;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class Covoiturage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String destination;
    private String source;
    private Date submitdate;
    private Date tripDate;
    private short freeSeasts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Date getSubmitdate() {
        return submitdate;
    }

    public void setSubmitdate(Date submitdate) {
        this.submitdate = submitdate;
    }

    public Date getTripDate() {
        return tripDate;
    }

    public void setTripDate(Date tripDate) {
        this.tripDate = tripDate;
    }

    public short getFreeSeasts() {
        return freeSeasts;
    }

    public void setFreeSeasts(short freeSeasts) {
        this.freeSeasts = freeSeasts;
    }
}
