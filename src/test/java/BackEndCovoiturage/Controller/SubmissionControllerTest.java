package BackEndCovoiturage.Controller;

import BackEndCovoiturage.Model.Submission;
import BackEndCovoiturage.Service.SubmissionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest
public class SubmissionControllerTest {
    MockMvc mockMvc;
    @Mock
    SubmissionService submissionService;

    @InjectMocks
    SubmissionController submissionController;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(submissionController).build();
    }

    @Test
    public void addSubmissionNegativeTest() throws Exception {
        long covoiturageId = 0;
        long userId = 0;
        when(submissionService.addSubmission(userId , covoiturageId)).thenReturn(false);

        mockMvc.perform(get("/api/submission/addSubmission"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(submissionService).addSubmission(userId,covoiturageId);
    }

    @Test
    public void findAllSubmissionsTest() throws Exception {
        when(submissionService.findAllSubmissions()).thenReturn(Stream.of(new Submission() , new Submission()).collect(Collectors.toList()));

        mockMvc.perform(get("/api/submission/allSubmission"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(submissionService).findAllSubmissions();
    }

    @Test
    public void canSubmitNegativeTest() throws Exception {

        mockMvc.perform(get("/api/submission/canSubmit"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void acceptSubmissionNegativeTest() throws Exception {
        long submissionId = 0,covoiturageId = 0 ;
        when(submissionService.acceptSubmission(submissionId , covoiturageId)).thenReturn(false);

        mockMvc.perform(get("/api/submission/acceptSubmission"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(submissionService).acceptSubmission(submissionId , covoiturageId);
    }

    @Test
    public void declineSubmissionNegativeTest() throws Exception {
        long submissionId = 0;
        when(submissionService.declineSubmission(submissionId)).thenReturn(false);

        mockMvc.perform(get("/api/submission/declineSubmission"))
                .andExpect(status().is(405));

        verifyNoInteractions(submissionService);
    }

    @Test
    public void getCovoituragesByParticipantTest() throws Exception {
        long participantId = 0;
        int pageNo = 0;
        int pageSize = 5;
        String sortBy = "submissionDate";
        when(submissionService.getAllCovoituragesByParticipant(participantId , pageNo , pageSize , sortBy)).thenReturn(new HashMap<>());

        mockMvc.perform(get("/api/submission/allCovByParticipant"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(submissionService).getAllCovoituragesByParticipant(participantId , pageNo , pageSize , sortBy);
    }

    @Test
    public void leaveCovoiturageTest() throws Exception {
        long userId = 0 , covoiturageId = 0;
        when(submissionService.leaveCovoiturageSubmission(userId,covoiturageId)).thenReturn(false);

        mockMvc.perform(delete("/api/submission/leaveCovoiturage"))
                .andExpect(status().isBadRequest());

        verifyNoInteractions(submissionService);
    }

    @Test
    public void deleteCovoiturageWithItsSubmissionsNegativeTest() throws Exception {
        long covoiturageId = 0;
        when(submissionService.deleteCovoiturageWithItsSubmissions(covoiturageId)).thenReturn(false);

        mockMvc.perform(delete("/api/submission/deleteCovWithSubs"))
                .andExpect(status().isBadRequest());

        verifyNoInteractions(submissionService);
    }



}
