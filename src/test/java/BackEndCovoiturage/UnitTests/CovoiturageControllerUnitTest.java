package BackEndCovoiturage.UnitTests;

import BackEndCovoiturage.Controller.CovoiturageController;
import BackEndCovoiturage.Model.Covoiturage;
import BackEndCovoiturage.Service.CovoiturageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.Instant;
import java.util.HashMap;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class CovoiturageControllerUnitTest {
    MockMvc mockMvc;

    @Mock
    CovoiturageService covoiturageService;

    @InjectMocks
    CovoiturageController covoiturageController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(covoiturageController).build();
    }

    // todo  bygouvDepartArrive , villeDepartArrivee

    @Test
    public void findCovoiturageByIdPositiveTest() throws Exception {
        long id = 0;
        when(covoiturageService.getCovoiturageById(id)).thenReturn(new Covoiturage());

        mockMvc.perform(get("/api/covoiturage/"+id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(covoiturageService).getCovoiturageById(id);
    }

    @Test
    public void findCovoiturageByIdNegativeTest() throws Exception {
        long id = 0;
        when(covoiturageService.getCovoiturageById(id)).thenReturn(new Covoiturage());

        mockMvc.perform(get("/api/covoiturage/"))
                .andExpect(status().is(404)); // the id cannot be null

        verifyNoInteractions(covoiturageService);
    }

    @Test
    public void getCovoiturageByIdPositiveTest() throws Exception {
        long id=0;
        when(covoiturageService.getCovoiturageById(id)).thenReturn(new Covoiturage());

        mockMvc.perform(get("/api/covoiturage/"+id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(covoiturageService).getCovoiturageById(id);
    }

    @Test
    public void getCovoiturageByIdNegativeTest() throws Exception{
        long id=0;
        when(covoiturageService.getCovoiturageById(id)).thenReturn(new Covoiturage());
        mockMvc.perform(get("/api/covoiturage/"))
                .andExpect(status().is(404));

        verifyNoInteractions(covoiturageService);

    }


    @Test
    public void deleteCovoiturageByIdPositiveTest() throws Exception {
        long id =0;
        mockMvc.perform(delete("/api/covoiturage/deleteCovoiturage/"+id))
                .andExpect(status().isOk());

        verify(covoiturageService).deleteCovoiturageById(id);
    }

    @Test
    public void deleteCovoiturageByIdNegativeTest() throws Exception {

        mockMvc.perform(delete("/api/covoiturage/deleteCovoiturage/"))
                .andExpect(status().is(405));

        verifyNoInteractions(covoiturageService);
    }


    @Test
    public void saveCovoiturageNegativeTest() throws Exception {

        Covoiturage covoiturage = new Covoiturage();
        when(covoiturageService.saveCovoiturage(covoiturage)).thenReturn(new Covoiturage());

        mockMvc.perform(post("/api/covoiturage/saveCovoiturage"))
                .andExpect(status().is(400));// because we do not have any covoiturage in the request body

        verifyNoInteractions(covoiturageService);

    }



    @Test
    public void findAllPagedCovoituragesTest() throws Exception {
        when(covoiturageService.findAllPagedCovoiturage(0,6,"price",true))
                .thenReturn(new HashMap<>());

        mockMvc.perform(get("/api/covoiturage/getPagedCovoiturages"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        verify(covoiturageService).findAllPagedCovoiturage(0,6,"price",true);// defaults values
    }


    @Test
    public void findCovoituragesByVilleDepartAndByVilleArriveTest() throws Exception {

        when(covoiturageService.findCovoituragesByVilleDepartAndByVilleArrive(0,0,"dateDepart","",""))
                .thenReturn(new HashMap<>());

        mockMvc.perform(get("/api/covoiturage/findByVilleName"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(covoiturageService).findCovoituragesByVilleDepartAndByVilleArrive(0,3,"dateDepart","","");

    }
    @Test
    public void findCovoituragesByGouvernoratDepartAndByGouvernoratArriveTest() throws Exception {
        when(covoiturageService.findCovoituragesByGouvernoratDepartAndByGouvernoratArrive(0,0,"price",
                "sfax","tunis"))
                .thenReturn(new HashMap<>());

        mockMvc.perform(get("/api/covoiturage/findByGovName"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(covoiturageService).findCovoituragesByGouvernoratDepartAndByGouvernoratArrive(0,3,"dateDepart","","");
    }

    @Test
    public void findCovoituragesByMultipleParametersTest() throws Exception {

        when(covoiturageService.findCovoituragesByMultipleParameters(0,0,"price","ASC",
                "tunis","sfax",0,10,Instant.now(),4,true))
                .thenReturn(new HashMap<>());

        mockMvc.perform(get("/api/covoiturage/covoiturages"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(covoiturageService).findCovoituragesByMultipleParameters(0,9,"dateDepart","ASC",
                "all","all",0,
                100000 , Instant.parse("2000-01-01T00:00:00Z"), 0 , true );
    }



}
