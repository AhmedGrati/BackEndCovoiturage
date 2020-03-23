package BackEndCovoiturage.Controller;

import BackEndCovoiturage.Model.User;
import BackEndCovoiturage.Service.UserService;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static BackEndCovoiturage.Model.Gender.female;
import static BackEndCovoiturage.Model.Gender.male;

@RestController
@RequestMapping(value = "api/user")
public class UserController {


    @Autowired
    private  UserService userService;

    @PostMapping(path = "postUser")
    public ResponseEntity<User> addUser(@RequestBody @Valid @NonNull User user){
       if(this.userService.saveUser(user)!=null){
           return new ResponseEntity<>(user, HttpStatus.OK);

       }else{
           return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
       }
    }

    @GetMapping(path = "allUsers")
    public List<User> getAllUsers(){
        return this.userService.findAllUsers();
    }

    @GetMapping(path = "getUserById/{id}")
    public User getUserById(@PathVariable("id") long id){
        return this.userService.findUserById(id);
    }

    @DeleteMapping(path="deleteUserById/{id}")
    @Transactional
    //transactional creates a proxy that contains this method ,
    // spring wrap this method in the proxy .without transactional delete won't work
    public void deleteUser(@PathVariable("id") long id){
        this.userService.deleteUserById(id);
    }

    @PostMapping("rand")
    public Iterable<User> addRandom() {
        ArrayList<User>  userArrayList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            userArrayList.add(User.getRandom());
        }

        return userService.userRepo.saveAll(userArrayList);

    }

    @DeleteMapping("purge")
    public void purge(){
         userService.userRepo.deleteAll();
    }


    @GetMapping("getPagedUsers")
    public List<User> getPagedUsers(@RequestParam(defaultValue = "0") int pageNo ,
                                    @RequestParam(defaultValue = "6") int pageSize,
                                    @RequestParam(defaultValue = "firstName") String sortBy) {

        return (this.userService.findAllUsers(pageNo , pageSize , sortBy));
    }

}
