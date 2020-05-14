package BackEndCovoiturage.Controller;

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
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

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
    public ResponseEntity<ObjectNode> addSubmission(@RequestParam(defaultValue = "0") long userId, @RequestParam(defaultValue = "0") long covoiturageId) {
        ObjectNode obj = mapper.createObjectNode();

        return (this.submissionService.addSubmission(userId, covoiturageId)) ?
                new ResponseEntity<>(obj.put("response", "success"), HttpStatus.OK) :
                new ResponseEntity<>(obj.put("response", "failed"), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("canSubmit")
    public ResponseEntity<ObjectNode> canSubmit(long covoiturageId, long userId) {
        ObjectNode obj = mapper.createObjectNode();
        Boolean result = !this.submissionRepo.existsByCovoiturageIdAndOwnerId(covoiturageId, userId);
        return new ResponseEntity<>(obj.put("response", result), HttpStatus.OK);
    }


    @GetMapping("acceptSubmission")
    public ResponseEntity<ObjectNode> acceptSubmission(@RequestParam(defaultValue = "0") long submissionId, @RequestParam(defaultValue = "0") long covoiturageId) {
        ObjectNode obj = mapper.createObjectNode();

        return (this.submissionService.acceptSubmission(submissionId, covoiturageId)) ?
                new ResponseEntity<>(obj.put("response", "success"), HttpStatus.OK) :
                new ResponseEntity<>(obj.put("response", "failed"), HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("declineSubmission")
    public ResponseEntity<ObjectNode> declineSubmission(@RequestParam long submission_id) throws JSONException {
        ObjectNode obj = mapper.createObjectNode();

        return (this.submissionService.declineSubmission(submission_id)) ?
                new ResponseEntity<>(obj.put("response", "success"), HttpStatus.OK) :
                new ResponseEntity<>(obj.put("response", "failed"), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("allCovByParticipant")
    public List<HashMap<String, Object>> getAllCovoituragesByParticipant(@RequestParam(defaultValue = "0") long participantId,
                                                                         @RequestParam(defaultValue = "1") int pageNo,
                                                                         @RequestParam(defaultValue = "5") int pageSize,
                                                                         @RequestParam(defaultValue = "submissionDate") String sortBy) {
        List<HashMap<String, Object>> covoiturageList = this.submissionService.getAllCovoituragesByParticipant(participantId, pageNo, pageSize, sortBy);
        return covoiturageList;
    }

    @DeleteMapping("leaveCovoiturage")
    public ResponseEntity<ObjectNode> leaveCovoiturageSubmission(@RequestParam long userId, @RequestParam long covoiturageId) {
        ObjectNode obj = mapper.createObjectNode();

        return (this.submissionService.leaveCovoiturageSubmission(userId, covoiturageId)) ?
                new ResponseEntity<>(obj.put("response", "succes"), HttpStatus.OK) :
                new ResponseEntity<>(obj.put("response", "failed"), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("createdCovoiturageWithSubmissions")
    public ObjectNode createdCovoiturageWithSubmissions(long userId,
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

}
