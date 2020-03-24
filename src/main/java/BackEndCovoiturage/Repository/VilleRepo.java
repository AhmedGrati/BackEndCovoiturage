package BackEndCovoiturage.Repository;

import BackEndCovoiturage.Model.Ville;
import org.springframework.data.repository.CrudRepository;

public interface VilleRepo extends CrudRepository<Ville , Long> {

    Ville findVilleByName(String name);
}
