package BackEndCovoiturage.IntegrationTests;
import BackEndCovoiturage.BackEndWasalniApplication;
import BackEndCovoiturage.Model.User;
import BackEndCovoiturage.Service.UserService;

import BackEndCovoiturage.Controller.UserController;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.print.attribute.standard.Media;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = BackEndWasalniApplication.class
)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class UserControllerIntegrationTest {

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

        MvcResult mvcResult = mockMvc.perform(get("/api/user/allUsers"))
                .andExpect(status().isOk()).andReturn();

        assertEquals(MediaType.APPLICATION_JSON_VALUE , mvcResult.getResponse().getContentType());

    }

    @Test
    public void getUserByIdTest() throws Exception{

        long id = -1;

        MvcResult mvcResult = mockMvc.perform(get("/api/user/getUserById/"+id))
                .andExpect(status().isOk()).andReturn();


        assertEquals("",mvcResult.getResponse().getContentAsString());
        }

    @Test
    public void getUserByEmail() throws Exception {
        String email = "noexistantemail@gmail.com";

        MvcResult mvcResult = mockMvc.perform(get("/api/user/emailExists?email="+email))
                .andExpect(status().isOk()).andReturn();
        assertEquals(MediaType.APPLICATION_JSON_VALUE,mvcResult.getResponse().getContentType());
    }

    @Test
    public void sendEmailPositiveTest() throws Exception {
        String receiverMail = "noexistantemail@gmail.com";

        MvcResult mvcResult = mockMvc.perform(get("/api/user/sendEmail?email="+receiverMail))
                .andExpect(status().is(409)).andReturn();

        assertEquals(MediaType.APPLICATION_JSON_VALUE,mvcResult.getResponse().getContentType());
    }

    @Test
    public void sendEmailNegativeTest() throws Exception {
        String receiverMail = "ahmedgrati1999@gmail.com";

        MvcResult mvcResult = mockMvc.perform(get("/api/user/sendEmail?email="+receiverMail))
                .andReturn();

        assertEquals(MediaType.APPLICATION_JSON_VALUE,mvcResult.getResponse().getContentType());
    }

//    @Test
//    public void downloadImagePositiveTest() throws Exception {
//
//        String imageName = "0.jpg";
//        String storageDirectoryPath = "/home/ubuntu/linux";
//        when(userService.getImageWithMediaType(imageName)).thenReturn(
//            IOUtils.toByteArray(Paths.get(storageDirectoryPath+"\\"+imageName).toUri()));
//        mockMvc.perform(get("/api/user/images/getImage/"+imageName))
//            .andExpect(status().isOk())
//            .andExpect(content().contentType(MediaType.IMAGE_JPEG));
//
//        verify(userService).getImageWithMediaType(imageName);
//
//
//    }
//
//    @Test
//    public void downloadImageNegativeTest() throws Exception {
//
//        String imageName = "jheyehehe.png";
//        String storageDirectoryPath = "/home/ubuntu/images";
//        when(userService.getImageWithMediaType(imageName)).thenReturn(null);
//        mockMvc.perform(get("/api/user/images/getImage/"+imageName))
//                .andExpect(status().isOk());
//
//        verify(userService).getImageWithMediaType(imageName);
//    }
//
//    @Test
//    public void registerUserByIdNegativeTest() throws Exception{
//        String json = "{\n" +
//                "  \"email\" : \"ahmed@gmail.com\",\n" +
//                "  \"password\": \"ahmed\",\n" +
//                "  \"gender\" : \"male\"\n" +
//                "}";
//
//        byte[] content = Files.readAllBytes(Paths.get("/home/ubuntu/images/0.jpg"));
//        when(userService.uploadToLocalFileSystem( new MockMultipartFile("0.jpg","0.jpg","image/jpeg",content)  , new User()))
//                .thenReturn("localhost:90/api/user/images/getImage/wad.png");
//
//        mockMvc.perform(post("/api/user/upload?file=0.jpg")
//                .content(json).contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isBadRequest());// because the file form data is missing in the body request
//
//
//        verifyNoInteractions(userService);// because when the file is null we don't call the userService so there's no interactions with the userService
//    }
//

}
