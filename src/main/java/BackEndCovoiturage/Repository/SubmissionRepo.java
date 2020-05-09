package BackEndCovoiturage.Repository;

import BackEndCovoiturage.Model.Covoiturage;
import BackEndCovoiturage.Model.Submission;
import BackEndCovoiturage.Model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmissionRepo extends PagingAndSortingRepository<Submission, Long> {
    boolean existsByOwner(User owner);

    Submission findSubmissionById(long id);

    @Override
    <S extends Submission> S save(S s);

    void deleteSubmissionById(long id);

    @Query("SELECT s.covoiturage from submission s where s.owner.id = :user_id")
    Page<Covoiturage> getCovoituragesOfOwner(long user_id , Pageable pageable);

    Submission findSubmissionByCovoiturage(Covoiturage covoiturage);


}
