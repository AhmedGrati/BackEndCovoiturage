package BackEndCovoiturage.UnitTests;

import BackEndCovoiturage.Controller.VilleController;
import BackEndCovoiturage.Service.VilleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class VilleControllerUnitTest {

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
        when(villeService.villeExistsByName(villeName)).thenReturn(true);

        mockMvc.perform(get("/api/ville/"+villeName))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        verify(villeService).villeExistsByName(villeName);
    }

    @Test
    public void villeExistsByNameNegativeTest() throws Exception {
        String villeName ="";
        when(villeService.villeExistsByName(villeName)).thenReturn(false);

        mockMvc.perform(get("/api/ville/"+villeName))
                .andExpect(status().is(404));// because the ville name should not be null

        // because when the ville name is null it will crash immediately and we won't call the ville service
        verifyNoInteractions(villeService);
    }
}
