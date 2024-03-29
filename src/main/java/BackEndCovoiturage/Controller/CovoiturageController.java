package BackEndCovoiturage.Controller;

import BackEndCovoiturage.Model.Covoiturage;
import BackEndCovoiturage.Model.ObjectResponse;
import BackEndCovoiturage.Repository.CovoiturageRepo;
import BackEndCovoiturage.Repository.VilleRepo;
import BackEndCovoiturage.Service.CovoiturageService;
import BackEndCovoiturage.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "api/covoiturage")
@CrossOrigin
public class CovoiturageController {

    @Autowired
    public CovoiturageService covoiturageService;

    @Autowired
    public UserService userService;


    // todo : clean those
    @Autowired
    private VilleRepo villeRepo;
    @Autowired
    private CovoiturageRepo covoiturageRepo;

    @GetMapping(path = "all")
    public List<Covoiturage> getAllCovoiturage() {
        return this.covoiturageService.getAllCovoiturages();
    }

    @PostMapping(path = "saveCovoiturage")
    public Covoiturage saveCovoiturage(@RequestBody @Valid @NonNull Covoiturage.DTO covoiturageDTO) {


        return (this.covoiturageService.saveCovoiturage(
                covoiturageDTO.toCovoiturage(userService, villeRepo)
        ));
    }

    @GetMapping(path = "{id}")
    public Covoiturage getCovoiturageById(@PathVariable @NotNull(message = "the covoiturage Id should not be empty") long id) {
        return this.covoiturageService.getCovoiturageById(id);
    }

    @DeleteMapping(path = "deleteCovoiturage/{id}")
    @Transactional
    public void deleteCovoiturageById(@PathVariable @NotNull(message = "the covoiturage Id should not be empty") long id) {
        this.covoiturageService.deleteCovoiturageById(id);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "getPagedCovoiturages")
    public HashMap<String, Object> getPagedCovoiturages(@RequestParam(defaultValue = "0") int pageNo
            , @RequestParam(defaultValue = "6") int pageSize
            , @RequestParam(defaultValue = "price") String sortBy
            , @RequestParam(defaultValue = "true") boolean allowOld) {
        return (this.covoiturageService.findAllPagedCovoiturage(pageNo, pageSize, sortBy, allowOld));
    }

    @GetMapping(path = "covoiturageNumber")
    public Map<String, Integer> getCovoiturageNumber() {
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("length", this.covoiturageService.getCovoiturageNumber());
        return hashMap;
    }

    @GetMapping("findByGovName")
    public HashMap<String, Object> findCovoiturageByGovDepartAndByGovArrive(@RequestParam(defaultValue = "0") int pageNo,
                                                                            @RequestParam(defaultValue = "3") int pageSize,
                                                                            @RequestParam(defaultValue = "dateDepart") String sortBy,
                                                                            @RequestParam(defaultValue = "") String nameOfGovDepart,
                                                                            @RequestParam(defaultValue = "") String nameOfGovArrive) {


        return this.covoiturageService.findCovoituragesByGouvernoratDepartAndByGouvernoratArrive(pageNo, pageSize, sortBy, nameOfGovDepart, nameOfGovArrive);
    }


    @GetMapping("findByVilleName")
    public HashMap<String, Object> findCovoiturageByVilleDepartAndByVilleArrivee(@RequestParam(defaultValue = "0") int pageNo,
                                                                                 @RequestParam(defaultValue = "3") int pageSize,
                                                                                 @RequestParam(defaultValue = "dateDepart") String sortBy,
                                                                                 @RequestParam(defaultValue = "") String nameOfVilleDepart,
                                                                                 @RequestParam(defaultValue = "") String nameOfVilleArrivee) {
        return this.covoiturageService.findCovoituragesByVilleDepartAndByVilleArrive(pageNo, pageSize, sortBy, nameOfVilleDepart, nameOfVilleArrivee);
    }


    @PostMapping("rand")
    public Iterable<Covoiturage> seed(long count) {

        ArrayList<Covoiturage> c = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            c.add(Covoiturage.rand(userService, villeRepo));
        }
        return covoiturageRepo.saveAll(c);
    }


    @GetMapping("covoiturages")
    public HashMap<String, Object> findCovoituragesByMultipleParameters(@RequestParam(defaultValue = "0") int pageNo,
                                                                        @RequestParam(defaultValue = "9") int pageSize,
                                                                        @RequestParam(defaultValue = "dateDepart") String sortBy,
                                                                        @RequestParam(defaultValue = "DSC") String direction,
                                                                        @RequestParam(defaultValue = "all") String villeDepart,
                                                                        @RequestParam(defaultValue = "all") String villeArriv,
                                                                        @RequestParam(defaultValue = "0") int min,
                                                                        @RequestParam(defaultValue = "100000") int max,
                                                                        @RequestParam(defaultValue = "2000-01-01T00:00:00Z") Instant dateDepart,
                                                                        @RequestParam(defaultValue = "0") int place,
                                                                        @RequestParam(defaultValue = "true") boolean fumer

    ) {
        return covoiturageService.findCovoituragesByMultipleParameters(pageNo, pageSize, sortBy, direction, villeDepart
                , villeArriv, min, max, dateDepart, place, fumer);
    }

    /*@GetMapping("participateToCovoiturage")
    public ResponseEntity<JSONObject> participateToCovoiturage(@RequestParam(defaultValue = "0") long userId, @RequestParam(defaultValue = "0") long covoiturageId) throws JSONException {
        ObjectResponse objectResponse = new ObjectResponse();

        JSONObject obj = new JSONObject();

        return (this.covoiturageService.participateToCovoiturage(userId, covoiturageId)) ?
                new ResponseEntity<>(obj.put("response", "succes"), HttpStatus.OK) :
                new ResponseEntity<>(obj.put("response", "failed"), HttpStatus.BAD_REQUEST);

    }*/

    @PostMapping("submitCovoiturage/{userId}")
    public ObjectResponse submitCovoiturage(@PathVariable(value = "userId") @NotNull(message = "the user Id should not be empty") long userId,
                                            @RequestBody @NotNull @Valid Covoiturage covoiturage) {
        ObjectResponse objectResponse = new ObjectResponse();
        if (this.covoiturageService.submitCovoiturage(userId, covoiturage)) {
            objectResponse.setResponseMessage("ok");
        } else {
            objectResponse.setResponseError("not ok");
        }
        return objectResponse;
    }

    @GetMapping("sumOfCovoiturages")
    public int sumOfCovoituragesByUser(@RequestParam @NotNull(message = "the user Id should not be empty") long userId) {
        return this.covoiturageService.sumOfCovoituragesByUser(userId);
    }

    @DeleteMapping("deleteAllByOwnerId")
    public void deleteCovoituragesByOwnerId(@RequestParam @NotNull(message = "the user Id should not be empty") long userId) {
        this.covoiturageService.deleteCovoituragesByOwnerId(userId);
    }

    @GetMapping("randomLastCovoiturages")
    public List<Covoiturage> getRandomCovsByVilleDepartAndVilleArrivee(long id , int max) {
        return this.covoiturageService.findRandomCovoituragesByGouvDepartAndGouvArrivee(id , max);
    }

    @GetMapping("allCovByOwner")
    public List<Covoiturage> getAllCovoituragesByOwnerId(@RequestParam @NotNull(message = "the owner Id should not be empty") long owner_id) {
        List<Covoiturage> covoiturageList = this.covoiturageService.getAllCovoituragesOfOwner(owner_id);
        return covoiturageList;
    }

    /*@GetMapping("allCovByParticipant")
    public List<Covoiturage> getAllCovoituragesByParticipant(@RequestParam(defaultValue = "0") long participant_id , @RequestParam(defaultValue = "1") int pageNo
            , @RequestParam(defaultValue = "5") int pageSize , @RequestParam(defaultValue = "dateDepart") String sortBy) {
        List<Covoiturage> covoiturageList = this.covoiturageService.getAllCovoituragesByParticipant(participant_id , pageNo , pageSize , sortBy);
        return covoiturageList;
    }*/




}
