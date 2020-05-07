package BackEndCovoiturage.Repository;

import BackEndCovoiturage.Model.*;
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
            "(c.dateDepart >= :dateDepart ) and " +
            "(c.price >= :min) and " +
            "c.price <= :max and " +
            "(c.nbrPlaceDispo >= :place) and " +
            "(c.isFumer = :fumer or :fumer = true )")
    Page<Covoiturage> findCovoituragesByMultipleParameters(String govDepart,
                                                           String govArrive,
                                                           int min,
                                                           int max,
                                                           Instant dateDepart,
                                                           int place,
                                                           boolean fumer
            , Pageable pageable);

    @Query(value = "SELECT COUNT(c.id) from covoiturage c where c.owner.id = :id")
    int sumOfCovoituragesByUser(long id);

    void deleteCovoituragesByOwnerId(long userId); // in case the user wants to delete all his covoiturages

    @Query(value = "SELECT c from covoiturage c where c.gouvernoratDepart = :depart " +
            "and c.gouvernoratArrive = :arriv and c.id <> :id order by function('RAND') ")
    List<Covoiturage> findCovoituragesByGouvArriveeAndGouvDepart(Gouvernorat depart, Gouvernorat arriv, Long id, Pageable pageable); // return 6 random covoiturages where it matches with villeDepart and villeArrive

    @Query(value = "SELECT c FROM covoiturage c where c.owner.id = :owner_id")
    List<Covoiturage> getAllCovoituragesOfOwner(long owner_id);

    /*@Query("select c from covoiturage c where :participant in (c.submissions.owner)")
    List<Covoiturage> getCovoiturageByParticipant(User participant);*/


}
