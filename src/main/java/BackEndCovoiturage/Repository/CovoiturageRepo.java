package BackEndCovoiturage.Repository;

import BackEndCovoiturage.Model.Covoiturage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CovoiturageRepo extends PagingAndSortingRepository<Covoiturage , Long> {

    List<Covoiturage> findAll();

    Covoiturage getCovoiturageById(long id);


    void deleteCovoiturageById(long id);

    @Override
    <S extends Covoiturage> S save(S s);


    @Query(value = "SELECT  * from covoiturage where (datedepart > current_date) or (?1) ",
            nativeQuery = true)
    Page<Covoiturage> findAll(Boolean allowOld, Pageable pageable);

    @Query("SELECT COUNT (id) from covoiturage")
    int getCovoiturageNumber();


}
