package BackEndCovoiturage.Service;

import BackEndCovoiturage.Model.User;
import BackEndCovoiturage.Repository.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    public UserRepo userRepo;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Environment environment;

    private PasswordEncoder passwordEncoder;

    private final String myUrl = "https://localhost:4200/home";

    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    public User findUserById(long id){
        return (this.userRepo.findUserById(id));
    }

    public List<User> findAllUsers(){
        return (this.userRepo.findAll());
    }

    public void deleteUserById(long id){
        System.out.println("id : "+id);
        this.userRepo.deleteUserById(id);
    }

    public List<User> findAllUsers(int pageNo , int pageSize , String sortBy){
        Pageable pageable = PageRequest.of(pageNo , pageSize , Sort.by(sortBy));
        Page<User> pageResult = userRepo.findAll(pageable);
        if(pageResult.hasContent()){
            System.out.println("content : "+pageResult.getContent());
            return pageResult.getContent();
        }else{
            return new ArrayList<User>();
        }
    }

    public boolean emailExists(String email) {
        return this.userRepo.existsByEmail(email);
    }

    public boolean sendEmail(String email) throws MailException {
        User user = this.userRepo.findUserByEmail(email);
        if(user != null){
            String senderEmail = environment.getProperty("spring.mail.username");

            String receiverEmail = user.getEmail();

            //filling the message data : sender , receiver , subject and text
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(receiverEmail);
            simpleMailMessage.setFrom(senderEmail);
            simpleMailMessage.setSubject("Bienvenu Cher client .");
            String uuid = UUID.randomUUID().toString();
            String url = myUrl+"?id="+user.getId()+"&token="+uuid;
            System.out.println(url);
            simpleMailMessage.setText("Bienvenu chez wassalni . On Vous aide à chercher des covoiturages sécurisée et comfortable . Réinitialisez votre mot de passe ici : "+url);


            //sending the message via email
            this.javaMailSender.send(simpleMailMessage);
            return true;//this indicates that the email was sent successfully
        } else {
            return false;//this indicates that an error occurred while sending the mail
        }
    }

    // reseting password
    public boolean resetPassword(long id , String newPassword) {
        User user = this.userRepo.findUserById(id);
        if(user != null) {
            user.setPassword(this.passwordEncoder.encode(newPassword));
            this.userRepo.save(user);
            return true;// return true if the user exists
        }else{
            return false;//return false if the user doesn't exists
        }
    }

}
