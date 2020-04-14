package BackEndCovoiturage.Controller;

import BackEndCovoiturage.Configuration.Security.UserPrincipalDetailService;
import BackEndCovoiturage.Model.ObjectResponse;
import BackEndCovoiturage.Model.User;
import BackEndCovoiturage.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;


import org.springframework.mail.MailException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/user")
@CrossOrigin
public class UserController {

    /*private AuthenticationManager authenticationManager;
    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager;
    }*/
    @Autowired
    private  UserService userService;

    @Autowired
    private UserPrincipalDetailService userPrincipalDetailService;



    /*@PostMapping(path = "postUser")
    public ResponseEntity<User> addUser(@RequestBody @Valid @NonNull User user){
       if(this.userService.saveUser(user)!=null){
           return new ResponseEntity<>(user, HttpStatus.OK);

       }else{
           return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
       }
    }*/

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
        for (int i = 0; i < 30; i++) {
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

    /*@PostMapping
    public ResponseEntity<?> createAuthentiticationToken(@RequestBody User authentiticationRequest) throws Exception {
        final Authentication auth = authenticate(authentiticationRequest.getEmail(), authentiticationRequest.getPassword());
        SecurityContextHolder.getContext().setAuthentication(auth);
        return ResponseEntity.ok(new (Jw.generateToken(auth)));
    }*/

    @PostMapping(value = "/register")
    public ResponseEntity<?> saveUser(@RequestBody User user) throws Exception {
        return ResponseEntity.ok(userPrincipalDetailService.save(user));
    }

    /*private Authentication authenticate(String username, String password) throws Exception {
        try {
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }*/

    @GetMapping("emailExists")
    public boolean emailExists(@RequestParam(defaultValue = "defaultValue@gmail.com") String email) {
        return this.userService.emailExists(email);
    }

    @GetMapping("sendEmail")
    public @ResponseBody
    ObjectResponse sendingEmail(@RequestParam(defaultValue = "defaultValue@gmail.com") String email){
        ObjectResponse objectResponse = new ObjectResponse();
        try {

            if(this.userService.sendEmail(email)){
                objectResponse.setResponseMessage("ok");
            }else{
                objectResponse.setResponseError("not ok");
            }
        }catch (MailException e){
            e.printStackTrace();//print the exception
            objectResponse.setResponseError("not ok");
        }
        return objectResponse;
    }

    @PostMapping("resetPassword")
    public @ResponseBody ObjectResponse resetPassword(@RequestParam(defaultValue = "0") long id
                                                    , @RequestParam(defaultValue = "0000") String password){
        ObjectResponse objectResponse = new ObjectResponse();
        if(this.userService.resetPassword(id,password)){
            objectResponse.setResponseMessage("ok");
        }else{
            objectResponse.setResponseError("user does not exist");
        }
        return objectResponse;
    }
}
