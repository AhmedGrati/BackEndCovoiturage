package BackEndCovoiturage.UnitTests;

import BackEndCovoiturage.Controller.CovoiturageController;
import BackEndCovoiturage.Controller.NotificationController;
import BackEndCovoiturage.Service.CovoiturageService;
import BackEndCovoiturage.Service.NotificationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.HashMap;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest
public class NotificationControllerUnitTest {
    MockMvc mockMvc;

    @Mock
    NotificationService notificationService;

    @InjectMocks
    NotificationController notificationController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(notificationController).build();
    }

    @Test
    public void getAllNotificationsByReceiverIdTest() throws Exception {
        int pageNo = 0;
        int pageSize = 5;
        String sortBy = "date";
        long id = 0;
        when(notificationService.getNotificationsByReceiverId(pageNo , pageSize , sortBy , id)).thenReturn(new HashMap<>());

        mockMvc.perform(get("/api/notification/getNotificationsByReceiverId?pageNo="+pageNo
                +"&pageSize="+pageSize+"&sortBy="+sortBy+"&id="+id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(notificationService).getNotificationsByReceiverId(pageNo,pageSize,sortBy,id);
    }

    @Test
    public void markAsReadNegativeTest() throws Exception {

        when(notificationService.markNotificationsAsRead(new ArrayList<Long>())).thenReturn(true);

        mockMvc.perform(post("/api/notification/markAsRead"))
                .andExpect(status().is(400)); // because there was no object passed as request body

        verifyNoInteractions(notificationService);
    }

    @Test
    public void markAsReadTest() throws Exception {

        when(notificationService.markNotificationsAsRead(new ArrayList<Long>())).thenReturn(true);

        mockMvc.perform(post("/api/notification/markAsRead")
                .content(String.valueOf(new ArrayList<>()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));// because there was no object passed as request body

        verify(notificationService).markNotificationsAsRead(new ArrayList<>());
    }


}
