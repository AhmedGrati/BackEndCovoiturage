package BackEndCovoiturage.Repository;

import BackEndCovoiturage.Model.Covoiturage;
import BackEndCovoiturage.Model.Submission;
import BackEndCovoiturage.Model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SubmissionRepo extends PagingAndSortingRepository<Submission, Long> {
    boolean existsByOwner(User owner);

    Submission findSubmissionById(long id);

    @Override
    <S extends Submission> S save(S s);

    void deleteSubmissionById(long id);

    Boolean existsByCovoiturageIdAndOwnerId(long covId, long ownerId);

    @Query("SELECT s.covoiturage from submission s where s.owner.id = :user_id and s.status = 1")
    Page<Covoiturage> getCovoituragesOfParticipant(long user_id, Pageable pageable);

    @Query("SELECT s.covoiturage from submission s where s.owner.id = :user_id and s.status = 0")
    Page<Covoiturage> getAllPendingSubmissionsOfUser(long user_id, Pageable pageable);

    @Query("SELECT s from submission s where s.covoiturage.id = :covoiturageId and s.owner.id = :ownerId")
    Submission findSubmissionByCovoiturageAndUserId(long covoiturageId, long ownerId);

//    @Query("delete from submission s where s.covoiturage.id = :covId and s.owner.id = :ownerId")
//    Boolean deleteByCovoiturageIdAndOwnerId(long covId, long ownerId);

    boolean deleteSubmissionByCovoiturageAndOwner(Covoiturage covoiturage, User owner);


    List<Submission> findSubmissionByCovoiturage(Covoiturage covoiturage);

    @Transactional
    @Modifying
    @Query("DELETE  from submission s where s.covoiturage.id = :id")
    int deleteAllByCovoiturageId(long id);


    @Query("SELECT s from submission s where s.covoiturage.id = :covoiturageId")
    List<Submission> findSubmissionByCovoiturageId(long covoiturageId);

    List<Submission> findAll();
}
