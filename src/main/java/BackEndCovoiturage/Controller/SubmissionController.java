package BackEndCovoiturage.Controller;

import BackEndCovoiturage.Model.Covoiturage;
import BackEndCovoiturage.Model.Submission;
import BackEndCovoiturage.Service.SubmissionService;
import com.sun.mail.iap.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RequestMapping(value = "api/submission")
@RestController
public class SubmissionController {

    @Autowired
    private SubmissionService submissionService;
    @GetMapping("addSubmission")
    public ResponseEntity<JSONObject> addSubmission(@RequestParam(defaultValue = "0") long userId , @RequestParam(defaultValue = "0") long covoiturageId) throws JSONException {
        JSONObject obj = new JSONObject();

        return (this.submissionService.addSubmission(userId, covoiturageId)) ?
                new ResponseEntity<>(obj.put("response", "succes"), HttpStatus.OK) :
                new ResponseEntity<>(obj.put("response", "failed"), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("acceptSubmission")
    public ResponseEntity<JSONObject> acceptSubmission(@RequestParam(defaultValue = "0") long submissionId , @RequestParam(defaultValue = "0") long covoiturageId) throws JSONException {
        JSONObject obj = new JSONObject();

        return (this.submissionService.acceptSubmission(submissionId,covoiturageId)) ?
                new ResponseEntity<>(obj.put("response", "succes"), HttpStatus.OK) :
                new ResponseEntity<>(obj.put("response", "failed"), HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("declineSubmission")
    public ResponseEntity<JSONObject> declineSubmission(@RequestParam(defaultValue = "0") long submission_id) throws JSONException {
        JSONObject obj = new JSONObject();

        return (this.submissionService.declineSubmission(submission_id)) ?
                new ResponseEntity<>(obj.put("response", "succes"), HttpStatus.OK) :
                new ResponseEntity<>(obj.put("response", "failed"), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("allCovByParticipant")
    public List<HashMap<String,Object>> getAllCovoituragesByParticipant(@RequestParam(defaultValue = "0") long participantId , @RequestParam(defaultValue = "1") int pageNo
            , @RequestParam(defaultValue = "5") int pageSize , @RequestParam(defaultValue = "submissionDate") String sortBy) {
        List<HashMap<String,Object>> covoiturageList = this.submissionService.getAllCovoituragesByParticipant(participantId, pageNo, pageSize, sortBy);
        return covoiturageList;
    }

    @DeleteMapping("leaveCovoiturage")
    public ResponseEntity<JSONObject> leaveCovoiturageSubmission(@RequestParam(defaultValue = "0") long userId , @RequestParam(defaultValue = "0") long covoiturageId) throws JSONException {
        JSONObject obj = new JSONObject();

        return (this.submissionService.leaveCovoiturageSubmission(userId,covoiturageId)) ?
                new ResponseEntity<>(obj.put("response", "succes"), HttpStatus.OK) :
                new ResponseEntity<>(obj.put("response", "failed"), HttpStatus.BAD_REQUEST);
    }

}
