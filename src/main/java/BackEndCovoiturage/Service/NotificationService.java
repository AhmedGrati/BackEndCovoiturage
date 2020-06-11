package BackEndCovoiturage.Service;

import BackEndCovoiturage.Model.Notification;
import BackEndCovoiturage.Model.User;
import BackEndCovoiturage.Repository.NotificationRepo;
import BackEndCovoiturage.Repository.UserRepo;
import BackEndCovoiturage.tools.MyHelpers;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepo notificationRepo;

    @Autowired
    private UserRepo userRepo;

    public boolean markNotificationsAsRead(List<Long> idList){

        for(int i=0;i<idList.size();i++) {
            Optional<Notification> notification = this.notificationRepo.findById(idList.get(i));
            if(!notification.isPresent()){ // if the notification does not exist
                return false;
            }else{
                notification.get().setRead(true);
                System.out.println(notification.get());
                this.notificationRepo.save(notification.get());
            }
        }
        return true;
    }

    public List<Notification> getAllNotifications() {
        return this.notificationRepo.findAll();
    }

    public boolean addNotification(long receiverId , String content) {
        User user = this.userRepo.findUserById(receiverId);
        if(user == null) {
            return false;
        }else{
            Notification notification = new Notification(user , content , Instant.now() , false); // by default the notification is not read
            this.notificationRepo.save(notification);
        }

        return true;
    }

    public HashMap<String , Object> getNotificationsByReceiverId(int pageNo, int pageSize, String sortBy, Long id) {
        Pageable pageable = PageRequest.of(pageNo , pageSize , Sort.by(sortBy).descending()); // the latest notifications come first
        Page<Notification> pagedNotifications =this.notificationRepo.findAllByReceiver(id,pageable);
        return MyHelpers.pageWrapper(pagedNotifications);
    }

}
