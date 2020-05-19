package BackEndCovoiturage.Controller;

import BackEndCovoiturage.Configuration.Security.UserPrincipalDetailService;
import BackEndCovoiturage.Model.ObjectResponse;
import BackEndCovoiturage.Model.User;
import BackEndCovoiturage.Repository.UserRepo;
import BackEndCovoiturage.Service.UserService;

import org.apache.commons.io.IOUtils;
import org.aspectj.lang.annotation.Before;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assumptions.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class UserControllerTest {



    MockMvc mockMvc;

    @Mock
    UserService userService;


    @InjectMocks
    UserController userController;

    @BeforeEach
    public void setUp(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }


    @Test
    public void findAllUsersTest() throws Exception {
        when(userService.findAllUsers()).thenReturn(Stream.of(
                new User() , new User() , new User()
        ).collect(Collectors.toList()));
        mockMvc.perform(get("/api/user/allUsers"))
                    .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$",hasSize(3)));

        Mockito.verify(userService).findAllUsers();
    }

    @Test
    public void getUserByIdTest() throws Exception{
        User user = new User();

        when (userService.findUserById(user.getId())).thenReturn(new User());

        mockMvc.perform(get("/api/user/getUserById/"+user.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));


        Mockito.verify(userService).findUserById(user.getId());
    }

    @Test
    public void getUserByEmail() throws Exception {
        String email = "ahmedgrati@gmail.com";
        when(userService.emailExists(email)).thenReturn(true);
        mockMvc.perform(get("/api/user/emailExists?email="+email))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        Mockito.verify(userService).emailExists(email);
    }

    @Test
    public void sendEmailPositiveTest() throws Exception {
        String receiverMail = "ahmedgrati1999@gmail.com";
        when(userService.sendEmail(receiverMail)).thenReturn(true);

        mockMvc.perform(get("/api/user/sendEmail?email="+receiverMail))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.responseMessage", Matchers.notNullValue()));// when the returned result from the service is true it means that we sent the emai so the response image is not null

        verify(userService).sendEmail(receiverMail);
    }

    @Test
    public void sendEmailNegativeTest() throws Exception {
        String receiverMail = "ahmedgrati1999@gmail.com";
        when(userService.sendEmail(receiverMail)).thenReturn(false);// when the returned result from the service is false it means that we didn't send the emai so the response image is null

        mockMvc.perform(get("/api/user/sendEmail?email="+receiverMail))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.responseMessage", Matchers.blankOrNullString()));

        verify(userService).sendEmail(receiverMail);
    }

    @Test
    public void downloadImagePositiveTest() throws Exception {

        String imageName = "0.jpg";
        String storageDirectoryPath = "C:\\Users\\Ahmed\\Desktop\\spring\\images";
        when(userService.getImageWithMediaType(imageName)).thenReturn(
            IOUtils.toByteArray(Paths.get(storageDirectoryPath+"\\"+imageName).toUri()));
        mockMvc.perform(get("/api/user/images/getImage/"+imageName))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.IMAGE_JPEG));

        verify(userService).getImageWithMediaType(imageName);


    }

    @Test
    public void downloadImageNegativeTest() throws Exception {

        String imageName = "jheyehehe.png";
        String storageDirectoryPath = "C:\\Users\\Ahmed\\Desktop\\spring\\image";
        when(userService.getImageWithMediaType(imageName)).thenReturn(null);
        mockMvc.perform(get("/api/user/images/getImage/"+imageName))
                .andExpect(status().isOk());

        verify(userService).getImageWithMediaType(imageName);
    }

    @Test
    public void registerUserByIdNegativeTest() throws Exception{
        String json = "{\n" +
                "  \"email\" : \"ahmed@gmail.com\",\n" +
                "  \"password\": \"ahmed\",\n" +
                "  \"gender\" : \"male\"\n" +
                "}";

        byte[] content = Files.readAllBytes(Paths.get("C:\\Users\\Ahmed\\Desktop\\spring\\images\\0.jpg"));
        when(userService.uploadToLocalFileSystem( new MockMultipartFile("0.jpg","0.jpg","image/jpeg",content)  , new User()))
                .thenReturn("localhost:90/api/user/images/getImage/wad.png");

        mockMvc.perform(post("/api/user/upload?file=0.jpg")
                .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());// because the file form data is missing in the body request


        verifyNoInteractions(userService);// because when the file is null we don't call the userService so there's no interactions with the userService
    }



}
