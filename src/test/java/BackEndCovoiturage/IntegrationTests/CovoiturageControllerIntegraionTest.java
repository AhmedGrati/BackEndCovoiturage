//package BackEndCovoiturage.IntegrationTests;
//import BackEndCovoiturage.BackEndWasalniApplication;
//import BackEndCovoiturage.Model.Covoiturage;
//import BackEndCovoiturage.Service.CovoiturageService;
//import BackEndCovoiturage.Controller.CovoiturageController;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.TestPropertySource;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.time.Instant;
//import java.util.HashMap;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@SpringBootTest(
//        classes = BackEndWasalniApplication.class
//)
//@AutoConfigureMockMvc
//@TestPropertySource(locations = "classpath:application-test.properties")
//public class CovoiturageControllerIntegraionTest {
//
//    MockMvc mockMvc;
//
//    @Mock
//    CovoiturageService covoiturageService;
//
//    @InjectMocks
//    CovoiturageController covoiturageController;
//
//    @BeforeEach
//    void setUp() {
//        mockMvc = MockMvcBuilders.standaloneSetup(covoiturageController).build();
//    }
//
//    // todo  bygouvDepartArrive , villeDepartArrivee
//
//    @Test
//    public void findCovoiturageByIdPositiveTest() throws Exception {
//        long id = -1;
//        MvcResult mvcResult = mockMvc.perform(get("/api/covoiturage/"+id))
//                .andExpect(status().isOk())
//                .andReturn();
//        assertEquals("",mvcResult.getResponse().getContentAsString());
//    }
//
//    @Test
//    public void findCovoiturageByIdNegativeTest() throws Exception {
//        mockMvc.perform(get("/api/covoiturage/"))
//                .andExpect(status().is(404)); // the id cannot be null
//    }
//
//    @Test
//    public void getCovoiturageByIdPositiveTest() throws Exception {
//        long id=-1;
//
//        MvcResult mvcResult = mockMvc.perform(get("/api/covoiturage/"+id))
//                .andExpect(status().isOk()).andReturn();
//        assertEquals("",mvcResult.getResponse().getContentAsString());
//
//    }
//
//    @Test
//    public void getCovoiturageByIdNegativeTest() throws Exception{
//
//        mockMvc.perform(get("/api/covoiturage/"))
//                .andExpect(status().is(404));
//
//    }
//
//
//    @Test
//    public void deleteCovoiturageByIdPositiveTest() throws Exception {
//        long id =-1;
//        MvcResult mvcResult = mockMvc.perform(delete("/api/covoiturage/deleteCovoiturage/"+id))
//                .andExpect(status().isOk()).andReturn();
//        assertEquals("",mvcResult.getResponse().getContentAsString());
//    }
//
//    @Test
//    public void deleteCovoiturageByIdNegativeTest() throws Exception {
//
//        mockMvc.perform(delete("/api/covoiturage/deleteCovoiturage/"))
//                .andExpect(status().is(405));
//
//    }
//
//
//    @Test
//    public void saveCovoiturageNegativeTest() throws Exception {
//
//        mockMvc.perform(post("/api/covoiturage/saveCovoiturage"))
//                .andExpect(status().is(400));// because we do not have any covoiturage in the request body
//    }
//
//
//    @Test
//    public void findAllPagedCovoituragesTest() throws Exception {
//        MvcResult mvcResult = mockMvc.perform(get("/api/covoiturage/getPagedCovoiturages"))
//                .andExpect(status().isOk()).andReturn();
//
//        assertEquals(MediaType.APPLICATION_JSON_VALUE , mvcResult.getResponse().getContentType());
//    }
//
//
//    @Test
//    public void findCovoituragesByVilleDepartAndByVilleArriveTest() throws Exception {
//
//        MvcResult mvcResult = mockMvc.perform(get("/api/covoiturage/findByVilleName"))
//                .andExpect(status().isOk())
//                .andReturn();
//        assertEquals(MediaType.APPLICATION_JSON_VALUE, mvcResult.getResponse().getContentType());
//    }
//    @Test
//    public void findCovoituragesByGouvernoratDepartAndByGouvernoratArriveTest() throws Exception {
//
//        MvcResult mvcResult = mockMvc.perform(get("/api/covoiturage/findByGovName"))
//                .andExpect(status().isOk()).andReturn();
//        System.out.println("hey : "+MediaType.APPLICATION_JSON.toString());
//        assertEquals(MediaType.APPLICATION_JSON_VALUE, mvcResult.getResponse().getContentType());
//    }
//
//    @Test
//    public void findCovoituragesByMultipleParametersTest() throws Exception {
//
//
//
//        MvcResult mvcResult = mockMvc.perform(get("/api/covoiturage/covoiturages"))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        assertEquals(MediaType.APPLICATION_JSON_VALUE , mvcResult.getResponse().getContentType());
//    }
//
//}
