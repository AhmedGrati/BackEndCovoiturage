package BackEndCovoiturage.IntegrationTests;
import BackEndCovoiturage.BackEndWasalniApplication;
import BackEndCovoiturage.Service.VilleService;
import BackEndCovoiturage.Controller.VilleController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = BackEndWasalniApplication.class
)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class VilleControllerIntegrationTest {
    MockMvc mockMvc;

    @Mock
    VilleService villeService;

    @InjectMocks
    VilleController villeController;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(villeController).build();
    }

    @Test
    public void villeExistsByNamePositiveTest() throws Exception {
        String villeName ="Sfax";

        MvcResult mvcResult = mockMvc.perform(get("/api/ville/"+villeName))
                .andExpect(status().isOk()).andReturn();

        assertEquals(MediaType.APPLICATION_JSON_VALUE,mvcResult.getResponse().getContentType());
    }

    @Test
    public void villeExistsByNameNegativeTest() throws Exception {

        mockMvc.perform(get("/api/ville/"))
                .andExpect(status().is(404));// because the ville name should not be null

    }
}
