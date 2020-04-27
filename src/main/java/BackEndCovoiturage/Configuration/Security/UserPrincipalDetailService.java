package BackEndCovoiturage.Configuration.Security;

import BackEndCovoiturage.Model.User;
import BackEndCovoiturage.Repository.UserRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class UserPrincipalDetailService implements UserDetailsService {
    private UserRepo userRepo;

    private PasswordEncoder passwordEncoder;

    UserPrincipalDetailService(UserRepo userRepo){

        this.userRepo = userRepo;
    }
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepo.findUserByEmail(s);
        UserPrincipal userPrincipal = new UserPrincipal(user);
        return userPrincipal;
    }

    public ResponseEntity<User> save(User user) {
        if (userRepo.findUserByEmail(user.getEmail()) == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setInscriptionDate(Instant.now());
            userRepo.save(user);
            return ResponseEntity.ok(user);
        }
        System.out.println("repeated email : " + user.getEmail());
        //    return ResponseEntity.badRequest().body(ResponseEntity.status(500) , null);
        return new ResponseEntity<>((User) null, HttpStatus.CONFLICT);
    }


    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
