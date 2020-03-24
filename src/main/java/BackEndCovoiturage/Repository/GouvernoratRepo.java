package BackEndCovoiturage.Repository;

import BackEndCovoiturage.Model.Gouvernorat;
import org.springframework.data.repository.CrudRepository;

public interface GouvernoratRepo extends CrudRepository<Gouvernorat , Long> {

    Gouvernorat findGouvernoratByName(String nameOfGouvernorat);
}
