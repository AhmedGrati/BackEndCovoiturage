package BackEndCovoiturage.Repository;

import BackEndCovoiturage.Model.User;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface UserRepo extends PagingAndSortingRepository<User, Long> {
    //return the user by his id
    User findUserById(long id);

    //return all users
    List<User> findAll();

    //save user
    @Override
    <S extends User> S save(S s);

    //deleteUserBYId
    void deleteUserById(long id);

    Page<User> findAll(Pageable pageable);

    User findUserByFirstName(String firstName);

    User findUserByEmail(String email);

    boolean existsByEmail(String email);

    User findByResetToken(String resetToken);

}
