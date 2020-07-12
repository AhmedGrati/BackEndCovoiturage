package BackEndCovoiturage.Service;

import BackEndCovoiturage.Model.Covoiturage;
import BackEndCovoiturage.Model.Status;
import BackEndCovoiturage.Model.Submission;
import BackEndCovoiturage.Model.User;
import BackEndCovoiturage.Repository.CovoiturageRepo;
import BackEndCovoiturage.Repository.SubmissionRepo;
import BackEndCovoiturage.Repository.UserRepo;
import BackEndCovoiturage.tools.MyHelpers;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
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

@Service
public class SubmissionService {

    @Autowired
    ObjectMapper mapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CovoiturageRepo covoiturageRepo;

    @Autowired
    private SubmissionRepo submissionRepo;

    @Autowired
    private NotificationService notificationService;

    public boolean addSubmission(long userId , long covoiturageId) {
        User user = userRepo.findUserById(userId);
        Covoiturage covoiturage = covoiturageRepo.getCovoiturageById(covoiturageId);
        if((user != null)&&(covoiturage != null)) {
            Submission submission = new Submission(Instant.now() ,user  , Status.pending , covoiturage);
            this.submissionRepo.save(submission);
            // add notification for the owner of cov
            String notificationContent = " a postulé pour votre covoiturage";

            this.notificationService.addNotification(covoiturage.getOwner().getId(), user, notificationContent);
            return true;
        }
        return false;
    }

    public boolean acceptSubmission(long submissionId , long covoiturageId) {
        Submission submission = this.submissionRepo.findSubmissionById(submissionId);
        Covoiturage covoiturage = this.covoiturageRepo.getCovoiturageById(covoiturageId);
        if((submission != null)&&(covoiturage != null)) {
            submission.setStatus(Status.accepted);
            covoiturage.setNbrPlaceDispo(covoiturage.getNbrPlaceDispo()-1);
            this.submissionRepo.save(submission);
            this.covoiturageRepo.save(covoiturage);
            // add notification for the owner of the submission
            String notificationContent =" a accepté votre postulation";

            System.out.println(notificationContent);
            this.notificationService.addNotification(submission.getOwner().getId(),covoiturage.getOwner() , notificationContent);
            return true;
        }
        return false;
    }
    public boolean declineSubmission(long submissionId) {
        Submission submission = this.submissionRepo.findSubmissionById(submissionId);
        if(submission != null) {
            this.submissionRepo.deleteSubmissionById(submissionId);
            // add notification for the owner of the submission
            String notificationContent = " a rejeté votre postulation";
            this.notificationService.addNotification(submission.getOwner().getId(),submission.getCovoiturage().getOwner() , notificationContent);
            return true;
        }
        return false;
    }

    public HashMap<String,Object> getAllCovoituragesByParticipant(long user_id , int pageNo , int pageSize , String sortBy) {
        User user = this.userRepo.findUserById(user_id);
        List<HashMap<String,Object>> returnedData = new ArrayList<>();
        Page<Covoiturage> page = null;
        if (user != null) {
            Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
            page = this.submissionRepo.getCovoituragesOfParticipant(user_id, pageable);
            System.out.println(page.hasContent());
            if(page.hasContent()) {

                for (int i = 0; i < page.getContent().size(); i++) {
                    List<Submission> submission = this.submissionRepo.findSubmissionByCovoiturageId(page.getContent().get(i).getId());
                    HashMap<String, Object> element = MyHelpers.wrapCovAndSub(page.getContent().get(i), submission);
                    returnedData.add(element);
                }
            }
        }
        return MyHelpers.wrapArrays(returnedData , page);
    }


    public boolean leaveCovoiturageSubmission(long userId, long covoiturageId) {
//        User owner = this.userRepo.findUserById(userId);
//        Covoiturage covoiturage = this.covoiturageRepo.getCovoiturageById(covoiturageId);
//        return submissionRepo.deleteSubmissionByCovoiturageAndOwner(covoiturage, owner);

        Covoiturage covoiturage = this.covoiturageRepo.getCovoiturageById(covoiturageId);
        User user = this.userRepo.findUserById(userId);
        if((user!=null)&&(covoiturage!=null)) {
            Submission submission = this.submissionRepo.findSubmissionByCovoiturageAndUserId(covoiturageId , userId);
            System.out.println("submission id : "+submission.getId());
            if((submission != null)&&(submission.getStatus() == Status.accepted)) {
                covoiturage.setNbrPlaceDispo(covoiturage.getNbrPlaceDispo()+1);
                covoiturageRepo.save(covoiturage);
                this.submissionRepo.deleteSubmissionById(submission.getId());
                return true;
            }
        }
        return false;
    }

    public ObjectNode createdCovoiturageWithSubmissions(long userId, Pageable page) {

        ObjectNode result = mapper.createObjectNode();
        Page<Covoiturage> covoiturageList = covoiturageRepo.getAllCovoituragesOfOwner(userId, page);
        result.put("totalLength", covoiturageList.getTotalElements());
        ArrayNode data = result.putArray("data");
        for (Covoiturage e : covoiturageList) {
            List<Submission> submissions = submissionRepo.findSubmissionByCovoiturage(e);
            ObjectNode temp = mapper.createObjectNode();
            temp.putPOJO("cov", e);
            temp.putPOJO("submission", submissions);
            data.add(temp);
        }

        return result;
    }

    public List<Submission> findAllSubmissions() {
        return this.submissionRepo.findAll();
    }
    public boolean leaveYourOwnSubmission(long submissionId) {
        Submission submission = this.submissionRepo.findSubmissionById(submissionId);
        // if the submission status is not pending we should not execute this method , instead we should execute the previous one
        if((submission != null)&&(submission.getStatus() != Status.accepted)) {
            this.submissionRepo.deleteSubmissionById(submissionId);
            return true;
        }
        return false;
    }

    // TODO this should be done in one single query or using  cascase
    public boolean deleteCovoiturageWithItsSubmissions(long covoiturageId) {
        if (covoiturageRepo.deleteCovoiturageById(covoiturageId) > 0) {
            submissionRepo.deleteAllByCovoiturageId(covoiturageId);
            return true;
        }
        return false;

    }

    public HashMap<String , Object> getAllPendingSubmissionsOfUser(long userId , int pageNo , int pageSize , String sortBy) {
        User user = this.userRepo.findUserById(userId);
        List<HashMap<String,Object>> returnedData = new ArrayList<>();
        Page<Covoiturage> page = null;
        if (user != null) {
            Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
            page = this.submissionRepo.getAllPendingSubmissionsOfUser(userId, pageable);
            System.out.println(page.hasContent());
            if(page.hasContent()) {

                for (int i = 0; i < page.getContent().size(); i++) {
                    List<Submission> submission = this.submissionRepo.findSubmissionByCovoiturageId(page.getContent().get(i).getId());
                    HashMap<String, Object> element = MyHelpers.wrapCovAndSub(page.getContent().get(i), submission);
                    returnedData.add(element);
                }
            }
        }
        return MyHelpers.wrapArrays(returnedData , page);
    }
}
