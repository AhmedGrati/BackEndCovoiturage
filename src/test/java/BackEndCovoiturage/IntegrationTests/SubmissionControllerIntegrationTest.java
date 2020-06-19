//package BackEndCovoiturage.IntegrationTests;
//
//import BackEndCovoiturage.BackEndWasalniApplication;
//import BackEndCovoiturage.Model.Submission;
//import BackEndCovoiturage.Service.SubmissionService;
//import BackEndCovoiturage.Controller.SubmissionController;
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
//import java.util.HashMap;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//@SpringBootTest(
//        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
//        classes = BackEndWasalniApplication.class
//)
//@AutoConfigureMockMvc
//@TestPropertySource(locations = "classpath:application-test.properties")
//public class SubmissionControllerIntegrationTest {
//    MockMvc mockMvc;
//    @Mock
//    SubmissionService submissionService;
//
//    @InjectMocks
//    SubmissionController submissionController;
//
//    @BeforeEach
//    void setUp() {
//        this.mockMvc = MockMvcBuilders.standaloneSetup(submissionController).build();
//    }
//
//    @Test
//    public void addSubmissionNegativeTest() throws Exception {
//
//        mockMvc.perform(get("/api/submission/addSubmission"))
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    public void findAllSubmissionsTest() throws Exception {
//
//
//        MvcResult mvcResult = mockMvc.perform(get("/api/submission/allSubmission"))
//                .andExpect(status().isOk()).andReturn();
//
//        assertEquals(MediaType.APPLICATION_JSON_VALUE , mvcResult.getResponse().getContentType());
//    }
//
//    @Test
//    public void canSubmitNegativeTest() throws Exception {
//
//        mockMvc.perform(get("/api/submission/canSubmit"))
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    public void acceptSubmissionNegativeTest() throws Exception {
//
//
//        mockMvc.perform(get("/api/submission/acceptSubmission"))
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    public void declineSubmissionNegativeTest() throws Exception {
//        mockMvc.perform(get("/api/submission/declineSubmission"))
//                .andExpect(status().is(405));
//
//    }
//
//    @Test
//    public void getCovoituragesByParticipantTest() throws Exception {
//
//
//        MvcResult mvcResult = mockMvc.perform(get("/api/submission/allCovByParticipant"))
//                .andExpect(status().isOk()).andReturn();
//
//        assertEquals(MediaType.APPLICATION_JSON_VALUE , mvcResult.getResponse().getContentType());
//    }
//
//    @Test
//    public void leaveCovoiturageTest() throws Exception {
//
//        mockMvc.perform(delete("/api/submission/leaveCovoiturage"))
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    public void deleteCovoiturageWithItsSubmissionsNegativeTest() throws Exception {
//        mockMvc.perform(delete("/api/submission/deleteCovWithSubs"))
//                .andExpect(status().isBadRequest());
//
//    }
//
//
//}
