package BackEndCovoiturage.Repository;

import BackEndCovoiturage.Model.Covoiturage;
import BackEndCovoiturage.Model.Gouvernorat;
import BackEndCovoiturage.Model.Ville;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface CovoiturageRepo extends PagingAndSortingRepository<Covoiturage , Long> {

    List<Covoiturage> findAll();

    Covoiturage getCovoiturageById(long id);


    void deleteCovoiturageById(long id);


    @Override
    <S extends Covoiturage> S save(S s);


    @Query(value = "SELECT  * from covoiturage",
            nativeQuery = true)
    Page<Covoiturage> findAll(Boolean allowOld, Pageable pageable);

    @Query(value = "SELECT COUNT (id) from covoiturage")
    int getCovoiturageNumber();


    Page<Covoiturage> findAllByGouvernoratDepartAndGouvernoratArrive(Gouvernorat gouvernoratDepart, Gouvernorat gouvernoratArrive, Pageable pageable);


    Page<Covoiturage> findAllByVilleDepartAndVilleArrivee(Ville villeDepart, Ville villeArrivee, Pageable pageable);



    @Query(value = "SELECT c from covoiturage  c where  " +
            "(c.gouvernoratDepart.name = :govDepart or :govDepart = 'all') and " +
            "(c.gouvernoratArrive.name = :govArrive or :govArrive = 'all') and" +
            "(c.dateDepart >= :date ) and " +
            "(c.price >= :min) and " +
            "c.price <= :max and " +
            "(c.nbrPlaceDispo >= :place) and " +
            "(c.isFumer = :fumer)")
    Page<Covoiturage> main(String govDepart,
                           String govArrive,
                           int min,
                           int max,
                           Instant date,
                           int place,
                           boolean fumer
            , Pageable pageable);


}
