package BackEndCovoiturage.Controller;

import BackEndCovoiturage.Configuration.Security.UserPrincipalDetailService;
import BackEndCovoiturage.Model.ObjectResponse;
import BackEndCovoiturage.Model.User;
import BackEndCovoiturage.Service.UserService;
import com.sun.istack.Nullable;
import jdk.jshell.Snippet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/user")
@CrossOrigin
public class UserController {


    @Autowired
    private  UserService userService;

    @Autowired
    private UserPrincipalDetailService userPrincipalDetailService;



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



    @PostMapping(value = "/register")
    public ResponseEntity<?> saveUser(@RequestBody User user) throws Exception {
        return ResponseEntity.ok(userPrincipalDetailService.save(user));
    }


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


    @PostMapping("upload")
    public ResponseEntity<User> signUp(
            @RequestPart User user,
            @Nullable @RequestParam(required = false) MultipartFile file) {


        // image will have userId as a name
        // todo: maybe add image entity  with date and order and other info

        //ResponseEntity<User> responseEntity = this.userPrincipalDetailService.save(user);

        /*if (responseEntity.getBody() == null) {
            return responseEntity;
        }

        user = responseEntity.getBody();*/

        if (file != null) {
            user.setHasUrl(true);
        }
        if(this.userPrincipalDetailService.save(user).getBody() != null){
            this.userService.uploadToLocalFileSystem(file,user.getId()+ "");
            return new ResponseEntity<User>(user , HttpStatus.OK);
        }
        return new ResponseEntity<User>((User) null, HttpStatus.CONFLICT);
    }

    @GetMapping(value="images/getImage/{fileName:.+}",
            produces = {MediaType.IMAGE_JPEG_VALUE,MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_PNG_VALUE}
    )
    public  @ResponseBody byte[] downloadImage(@PathVariable String fileName) throws IOException {
        //todo if user does not have an image return default picture
        return this.userService.getImageWithMediaType(fileName);
    }
}
