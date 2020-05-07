package BackEndCovoiturage.Repository;

import BackEndCovoiturage.Model.Submission;
import BackEndCovoiturage.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmissionRepo extends JpaRepository<Submission, Long> {
    boolean existsByOwner(User owner);

    Submission findSubmissionById(long id);

    @Override
    <S extends Submission> S save(S s);

    void deleteSubmissionById(long id);
}
