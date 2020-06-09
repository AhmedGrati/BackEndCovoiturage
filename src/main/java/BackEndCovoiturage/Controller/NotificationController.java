package BackEndCovoiturage.Controller;

import BackEndCovoiturage.Model.Notification;
import BackEndCovoiturage.Service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @MessageMapping("/chat.register")
    @SendTo("/topic/public")
    public Notification register(@Payload Notification notification , SimpMessageHeaderAccessor message){
        return this.notificationService.register(notification , message);
    }

    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    public Notification sendMessage(@Payload Notification notification){
        return this.notificationService.sendMessage(notification);
    }


}
