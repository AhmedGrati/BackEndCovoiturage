package BackEndCovoiturage.Service;

import BackEndCovoiturage.Model.Notification;
import org.springframework.messaging.simp.broker.SimpleBrokerMessageHandler;
import org.springframework.stereotype.Service;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
@Service
public class NotificationService {

    public Notification register(Notification notification, SimpMessageHeaderAccessor message) {
        message.getSessionAttributes().put("username",notification.getReceiver());
        return notification;
    }

    public Notification sendMessage(Notification notification) {
        return notification;
    }

}
