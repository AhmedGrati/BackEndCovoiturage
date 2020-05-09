package BackEndCovoiturage.Service;

import BackEndCovoiturage.Model.Covoiturage;
import BackEndCovoiturage.Model.Status;
import BackEndCovoiturage.Model.Submission;
import BackEndCovoiturage.Model.User;
import BackEndCovoiturage.Repository.CovoiturageRepo;
import BackEndCovoiturage.Repository.SubmissionRepo;
import BackEndCovoiturage.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;


import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class SubmissionService {
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

    public List<Covoiturage> getAllCovoituragesByParticipant(long user_id , int pageNo , int pageSize , String sortBy) {
        User user = this.userRepo.findUserById(user_id);
        if (user != null) {
            Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
            Page<Covoiturage> page = this.submissionRepo.getCovoituragesOfOwner(user_id , pageable);
            if(page.hasContent()) {
                return page.getContent();
            }
        }
        return new ArrayList<Covoiturage>();
    }

    public boolean leaveCovoiturageSubmission(long userId,long covoiturageId) {
        Covoiturage covoiturage = this.covoiturageRepo.getCovoiturageById(covoiturageId);
        User user = this.userRepo.findUserById(userId);
        if((user!=null)&&(covoiturage!=null)) {
            Submission submission = this.submissionRepo.findSubmissionByCovoiturage(covoiturage);
            if((submission != null)&&(submission.getStatus() == Status.accepted)) {
                this.submissionRepo.deleteSubmissionById(submission.getId());
                return true;
            }
        }
        return false;
    }
}
