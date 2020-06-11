package BackEndCovoiturage.Controller;

import BackEndCovoiturage.Model.Notification;
import BackEndCovoiturage.Model.ObjectResponse;
import BackEndCovoiturage.Service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/api/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;


    @GetMapping("getNotificationsByReceiverId")
    public HashMap<String , Object> getNotificatiosnByReceiverId(@RequestParam(defaultValue = "0")int pageNo ,
                                                              @RequestParam(defaultValue = "8") int pageSize ,
                                                              @RequestParam(defaultValue = "date") String sortBy,
                                                              @RequestParam long id) {
        return this.notificationService.getNotificationsByReceiverId(pageNo , pageSize , sortBy ,id);
    }

    @GetMapping("getAllNotifications")
    public List<Notification> getAllNotifications() {
        return this.notificationService.getAllNotifications();
    }

    @PostMapping("markAsRead")
    public ResponseEntity<ObjectResponse> markNotificationsAsRead(@RequestBody List<Long> idList) {
        ObjectResponse objectResponse = new ObjectResponse();
        if(this.notificationService.markNotificationsAsRead(idList)){
            objectResponse.setResponseMessage("ok");
            return new ResponseEntity<>(objectResponse , HttpStatus.OK);
        }else{
            objectResponse.setResponseError("not ok");
            return new ResponseEntity<>(objectResponse , HttpStatus.CONFLICT);
        }
    }


}
