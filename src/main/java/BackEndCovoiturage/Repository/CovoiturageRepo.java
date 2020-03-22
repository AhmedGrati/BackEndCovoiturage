package BackEndCovoiturage.Repository;

import BackEndCovoiturage.Model.Covoiturage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CovoiturageRepo extends CrudRepository<Covoiturage , Long> {

    List<Covoiturage> findAll();

    Covoiturage getCovoiturageById(long id);


    void deleteCovoiturageById(long id);

    @Override
    <S extends Covoiturage> S save(S s);


}
