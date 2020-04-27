package BackEndCovoiturage.Service;

import BackEndCovoiturage.Model.User;
import BackEndCovoiturage.Repository.UserRepo;
import org.apache.commons.io.IOUtils;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
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



    public final String storageDirectoryPathOnWindows = "C:\\Users\\Ahmed\\Desktop\\spring\\images";
    public final String storageDirectoryPathOnLinux = "/home/boogiep/wassalni/images";

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


    public String uploadToLocalFileSystem(MultipartFile file, String userId) {
        /* we will extract the file name (with extension) from the given file to store it in our local machine for now
        and later in virtual machine when we'll deploy the project
         */

        /* The Path in which we will store our image . we could change it later
        based on the OS of the virtual machine in which we will deploy the project.
        In my case i'm using windows 10 .
         */

        Path storageDirectory = Paths.get(storageDirectoryPathOnLinux);
        /*
         * we'll do just a simple verification to check if the folder in which we will store our images exists or not
         * */
        if (!Files.exists(storageDirectory)) { // if the folder does not exist
            try {
                Files.createDirectories(storageDirectory); // we create the directory in the given storage directory path
            } catch (Exception e) {
                e.printStackTrace();// print the exception
            }
        }

        Path destination = Paths.get(storageDirectory.toString(), userId + ".jpg");
        File out = new File(destination.toUri());

        // todo proper compression to webp , limit image size
        try {
            ImageIO.write(ImageIO.read(file.getInputStream()), "jpg", out);
            System.out.println("copied to " + destination.toUri());
        } catch (IOException e) {
            e.printStackTrace();
        }
        // the response will be the download URL of the image
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("api/user/images/getImage/")
                .path(userId + ".jpg")
                .toUriString();
        // return the download image url as a response entity
        return fileDownloadUri;
    }

    public  byte[] getImageWithMediaType(String imageName) throws IOException {
        Path destination = Paths.get(storageDirectoryPathOnLinux, imageName);// retrieve the image by its name
        if(Files.exists(destination)){
            return IOUtils.toByteArray(destination.toUri());
        }
        return null;
    }

}


