package BackEndCovoiturage.Repository;

import BackEndCovoiturage.Model.User;


import org.springframework.data.repository.PagingAndSortingRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

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


}
