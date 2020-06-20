package BackEndCovoiturage.IntegrationTests;

import BackEndCovoiturage.BackEndWasalniApplication;
import BackEndCovoiturage.Controller.NotificationController;
import BackEndCovoiturage.Model.Notification;
import BackEndCovoiturage.Repository.NotificationRepo;
import BackEndCovoiturage.Service.NotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = BackEndWasalniApplication.class
)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class NotificationControllerIntegrationTest {

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
        long id = -1;


        MvcResult mvcResult = mockMvc.perform(get("/api/notification/getNotificationsByReceiverId?pageNo="+pageNo
                +"&pageSize="+pageSize+"&sortBy="+sortBy+"&id="+id))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(MediaType.APPLICATION_JSON_VALUE,mvcResult.getResponse().getContentType()); // expect nothing in content type beacause the id is -1
    }

    @Test
    public void markAsReadNegativeTest() throws Exception {
        mockMvc.perform(post("/api/notification/markAsRead"))
                .andExpect(status().is(400)); // because there was no object passed as request body
    }

    @Test
    public void markAsReadTest() throws Exception {
        System.out.println(notificationService.markNotificationsAsRead((List.of())));
        MvcResult mvcResult = mockMvc.perform(post("/api/notification/markAsRead")
                .content(String.valueOf(List.of()))
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();// because there was no object passed as request body

        assertEquals(MediaType.APPLICATION_JSON_VALUE , mvcResult.getResponse().getContentType());
    }

}
