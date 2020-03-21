package BackEndCovoiturage.Controller;

import BackEndCovoiturage.Model.User;
import BackEndCovoiturage.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "covoiturage/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "postUser")
    public ResponseEntity<User> addUser(@RequestBody @Valid @NonNull User user){
       if(this.userService.saveUser(user)!=null){
           return new ResponseEntity<User>(user , HttpStatus.OK);

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
        System.out.println("hetha l id "+id);
        return this.userService.findUserById(id);
    }

    @DeleteMapping(path="deleteUserById/{id}")
    @Transactional
    //transactional creates a proxy that contains this method ,
    // spring wrap this method in the proxy .without transactional delete won't work
    public void deleteUser(@PathVariable("id") long id){
        this.userService.deleteUserById(id);
    }
}
