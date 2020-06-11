package BackEndCovoiturage.Repository;

import BackEndCovoiturage.Model.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepo extends JpaRepository<Notification, Long> {

    @Override
    <S extends Notification> S save(S s);

    Notification findById(long id);

    @Query(value = "SELECT n from notification n where n.receiver.id = :receiverId")
    Page<Notification> findAllByReceiver(long receiverId, Pageable pageable);


    boolean existsById(Long id);
}
