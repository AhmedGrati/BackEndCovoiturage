package BackEndCovoiturage.Service;

import BackEndCovoiturage.Model.User;
import BackEndCovoiturage.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public User findUserById(long id){
        return (this.userRepo.findUserById(id));
    }

    public List<User> findAllUsers(){
        return (this.userRepo.findAll());
    }

    public User saveUser(User user){
        return (this.userRepo.save(user));
    }

    public void deleteUserById(long id){
        System.out.println("id : "+id);
        this.userRepo.deleteUserById(id);
    }
}
