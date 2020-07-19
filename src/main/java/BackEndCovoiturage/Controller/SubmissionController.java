package BackEndCovoiturage.Controller;

import BackEndCovoiturage.Model.Covoiturage;
import BackEndCovoiturage.Model.Submission;
import BackEndCovoiturage.Repository.CovoiturageRepo;
import BackEndCovoiturage.Repository.SubmissionRepo;
import BackEndCovoiturage.Repository.UserRepo;
import BackEndCovoiturage.Service.SubmissionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RequestMapping(value = "api/submission")
@RestController
public class SubmissionController {

    @Autowired
    ObjectMapper mapper;
    @Autowired
    SubmissionRepo submissionRepo;
    @Autowired
    CovoiturageRepo covoiturageRepo;
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private SubmissionService submissionService;

    @GetMapping("addSubmission")
    public ResponseEntity<ObjectNode> addSubmission(@RequestParam @NotNull(message = "the user Id should not be null") long userId,
                                                    @RequestParam @NotNull(message = "the covoiturage Id should not be null") long covoiturageId) {
//        System.out.println(new ObjectMapper());
        ObjectNode obj = new ObjectMapper().createObjectNode();

        return (this.submissionService.addSubmission(userId, covoiturageId)) ?
                new ResponseEntity<>(obj.put("response", "success"), HttpStatus.OK) :
                new ResponseEntity<>(obj.put("response", "failed"), HttpStatus.BAD_REQUEST);
    }



    @GetMapping("allSubmission")
    public List<Submission> findAllSubmissions() {
        return this.submissionService.findAllSubmissions();
    }


    // todo optimize as sql query
    @GetMapping("canSubmit")
    public ResponseEntity<ObjectNode> canSubmit(@RequestParam @NotNull(message = "the covoiturage Id should not be null")  long covoiturageId,
                                                @RequestParam @NotNull(message = "the user Id should not be null") long userId) {
        ObjectNode obj = new ObjectMapper().createObjectNode();
        final Optional<Covoiturage> target = this.covoiturageRepo.findById(covoiturageId);
        if (target.isPresent()) {
            if (target.get().getOwner().getId() == userId)
                return new ResponseEntity<>(obj.put("response", "Self"), HttpStatus.OK);
            else {
                Boolean result = !this.submissionRepo.existsByCovoiturageIdAndOwnerId(covoiturageId, userId);
                return new ResponseEntity<>(obj.put("response", result), HttpStatus.OK);
            }
        } else
            return new ResponseEntity<>(obj.put("response", "error covoiturage not found"), HttpStatus.NOT_FOUND);
    }



    @GetMapping("acceptSubmission")

    public ResponseEntity<ObjectNode> acceptSubmission(@RequestParam @NotNull(message = "the submission Id should not be null") long submissionId,
                                                       @RequestParam @NotNull(message = "the covoiturage Id should not be null") long covoiturageId) {
        ObjectNode obj = new ObjectMapper().createObjectNode();

        return (this.submissionService.acceptSubmission(submissionId, covoiturageId)) ?
                new ResponseEntity<>(obj.put("response", "success"), HttpStatus.OK) :
                new ResponseEntity<>(obj.put("response", "failed"), HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("declineSubmission")
    @Transactional
    public ResponseEntity<ObjectNode> declineSubmission(@RequestParam @NotNull(message = "the submission Id should not be null")  long submission_id) throws JSONException {
        ObjectNode obj = new ObjectMapper().createObjectNode();

        return (this.submissionService.declineSubmission(submission_id)) ?
                new ResponseEntity<>(obj.put("response", "success"), HttpStatus.OK) :
                new ResponseEntity<>(obj.put("response", "failed"), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("allCovByParticipant")
    public HashMap<String, Object> getAllCovoituragesByParticipant(@RequestParam @NotNull(message = "the participant Id should not be null")  long participantId,
                                                                         @RequestParam(defaultValue = "0") int pageNo,
                                                                         @RequestParam(defaultValue = "5") int pageSize,
                                                                         @RequestParam(defaultValue = "submissionDate") String sortBy) {
        HashMap<String, Object> covoiturageList = this.submissionService.getAllCovoituragesByParticipant(participantId, pageNo, pageSize, sortBy);
        return covoiturageList;
    }
    @GetMapping("pendingSubmissions")
    public HashMap<String, Object> getAllPendingSubmissions(@RequestParam @NotNull(message = "the participant Id should not be null") long participantId,
                                                                   @RequestParam(defaultValue = "0") int pageNo,
                                                                   @RequestParam(defaultValue = "5") int pageSize,
                                                                   @RequestParam(defaultValue = "submissionDate") String sortBy) {
        HashMap<String, Object> covoiturageList = this.submissionService.getAllPendingSubmissionsOfUser(participantId, pageNo, pageSize, sortBy);
        return covoiturageList;
    }

    @DeleteMapping("leaveCovoiturage")
    @Transactional
    public ResponseEntity<ObjectNode> leaveCovoiturageSubmission(@RequestParam @NotNull(message = "the userId should not be empty") long userId,
                                                                 @RequestParam @NotNull(message = "the covoiturageId should not be empty") long covoiturageId) {
        ObjectNode obj = new ObjectMapper().createObjectNode();

        return (this.submissionService.leaveCovoiturageSubmission(userId, covoiturageId)) ?
                new ResponseEntity<>(obj.put("response", "succes"), HttpStatus.OK) :
                new ResponseEntity<>(obj.put("response", "failed"), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("createdCovoiturageWithSubmissions")
    public ObjectNode createdCovoiturageWithSubmissions(@RequestParam @NotNull(message = "the userId should not be empty") long userId,
                                                        @RequestParam(defaultValue = "5") int pageSize,
                                                        @RequestParam(defaultValue = "0") int pageNo,
                                                        @RequestParam(defaultValue = "dateDepart") String sortBy,
                                                        @RequestParam(defaultValue = "ASC") Sort.Direction direction) {

        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(direction, sortBy));
        return submissionService.createdCovoiturageWithSubmissions(userId, pageable);
    }


    @PostMapping("rand")
    public String generateRand() {
        for (int i = 0; i < 500; i++) {
            Submission.rand(userRepo, covoiturageRepo, submissionRepo);
        }
        return "ok";
    }

    @DeleteMapping("deleteCovWithSubs")
    @Transactional
    public ResponseEntity<ObjectNode> deleteCovoiturageWithItsSubmissions(@RequestParam @NotNull(message = "the covoiturage id should not be empty")long covoiturageId) {
        ObjectNode obj = new ObjectMapper().createObjectNode();

        return (this.submissionService.deleteCovoiturageWithItsSubmissions(covoiturageId)) ?
                new ResponseEntity<>(obj.put("response", "success"), HttpStatus.OK) :
                new ResponseEntity<>(obj.put("response", "0 target found"), HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("deleteOwnSubmission")
    @Transactional
    public ResponseEntity<ObjectNode> deleteOwnSubmission(@RequestParam @NotNull(message = "the submission id should not be empty") long submissionId) throws JSONException {
        ObjectNode obj = new ObjectMapper().createObjectNode();

        return (this.submissionService.leaveYourOwnSubmission(submissionId)) ?
                new ResponseEntity<>(obj.put("response", "success"), HttpStatus.OK) :
                new ResponseEntity<>(obj.put("response", "failed"), HttpStatus.BAD_REQUEST);
    }


}
