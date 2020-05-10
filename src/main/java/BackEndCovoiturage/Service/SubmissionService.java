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

    public boolean addSubmission(long userId , long covoiturageId) {
        User user = userRepo.findUserById(userId);
        Covoiturage covoiturage = covoiturageRepo.getCovoiturageById(covoiturageId);
        if((user != null)&&(covoiturage != null)) {
            Submission submission = new Submission(Instant.now() ,user  , Status.pending , covoiturage);
            this.submissionRepo.save(submission);
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
            return true;
        }
        return false;
    }
    public boolean declineSubmission(long submissionId) {
        Submission submission = this.submissionRepo.findSubmissionById(submissionId);
        if(submission != null) {
            this.submissionRepo.deleteSubmissionById(submissionId);
            return true;
        }
        return false;
    }

    public List<HashMap<String,Object>> getAllCovoituragesByParticipant(long user_id , int pageNo , int pageSize , String sortBy) {
        User user = this.userRepo.findUserById(user_id);
        List<HashMap<String,Object>> returnedData = new ArrayList<>();
        if (user != null) {
            Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
            Page<Covoiturage> page = this.submissionRepo.getCovoituragesOfParticipant(user_id, pageable);
            if(page.hasContent()) {
                for (int i = 0; i < page.getContent().size(); i++) {
                    List<Submission> submissions = this.submissionRepo.findSubmissionByCovoiturageId(page.getContent().get(i).getId());
                    HashMap<String, Object> element = MyHelpers.wrapCovAndSub(page.getContent().get(i), submissions);
                    returnedData.add(element);
                }
            }
        }
        return returnedData;
    }


    public boolean leaveCovoiturageSubmission(long userId, long covoiturageId) {

        return submissionRepo.deleteByCovoiturageIdAndOwnerId(covoiturageId, userId);

//        Covoiturage covoiturage = this.covoiturageRepo.getCovoiturageById(covoiturageId);
//        User user = this.userRepo.findUserById(userId);
//        if((user!=null)&&(covoiturage!=null)) {
//            Submission submission = this.submissionRepo.findSubmissionByCovoiturageAndUserId(covoiturage , userId);
//            if((submission != null)&&(submission.getStatus() == Status.accepted)) {
//                this.submissionRepo.deleteSubmissionById(submission.getId());
//                return true;
//            }
//        }
//        return false;
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
}
