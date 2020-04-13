package BackEndCovoiturage.Controller;

import BackEndCovoiturage.Model.Covoiturage;
import BackEndCovoiturage.Repository.CovoiturageRepo;
import BackEndCovoiturage.Repository.VilleRepo;
import BackEndCovoiturage.Service.CovoiturageService;
import BackEndCovoiturage.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "api/covoiturage")
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
    public Covoiturage saveCovoiturage(@RequestBody @Valid Covoiturage.DTO covoiturageDTO) {


        return (this.covoiturageService.saveCovoiturage(
                covoiturageDTO.toCovoiturage(userService, villeRepo)
        ));
    }

    @GetMapping(path = "{id}")
    public Covoiturage getCovoiturageById(@PathVariable @NonNull long id){
        return this.covoiturageService.getCovoiturageById(id);
    }

    @DeleteMapping(path = "deleteCovoiturage/{id}")
    @Transactional
    public void deleteCovoiturageById(@PathVariable @NonNull long id){
        this.covoiturageService.deleteCovoiturageById(id);
    }

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
    public HashMap<String,Object> findCovoiturageByGovDepartAndByGovArrive(@RequestParam(defaultValue = "0") int pageNo,
                                                               @RequestParam(defaultValue = "3") int pageSize,
                                                               @RequestParam(defaultValue = "datedepart") String sortBy,
                                                               @RequestParam(defaultValue = "") String nameOfGovDepart,
                                                               @RequestParam(defaultValue = "") String nameOfGovArrive) {


        return this.covoiturageService.findCovoituragesByGouvernoratDepartAndByGouvernoratArrive(pageNo, pageSize, sortBy, nameOfGovDepart, nameOfGovArrive);
    }


    @GetMapping("findByVilleName")
    public HashMap<String,Object> findCovoiturageByVilleDepartAndByVilleArrivee(@RequestParam(defaultValue = "0") int pageNo,
                                                                    @RequestParam(defaultValue = "3") int pageSize,
                                                                    @RequestParam(defaultValue = "datedepart") String sortBy,
                                                                    @RequestParam(defaultValue = "") String nameOfVilleDepart,
                                                                    @RequestParam(defaultValue = "") String nameOfVilleArrivee){
        return this.covoiturageService.findCovoituragesByVilleDepartAndByVilleArrive(pageNo, pageSize, sortBy, nameOfVilleDepart, nameOfVilleArrivee);
    }


    @PostMapping("rand")
    public Iterable<Covoiturage> seed() {

        ArrayList<Covoiturage> c = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            c.add(Covoiturage.rand(userService, villeRepo));
        }
        return covoiturageRepo.saveAll(c);
    }


    @GetMapping("covoiturages")
    public HashMap<String, Object> main(@RequestParam(defaultValue = "0") int pageNo,
                                        @RequestParam(defaultValue = "9") int pageSize,
                                        @RequestParam(defaultValue = "dateDepart") String sortBy,
                                        @RequestParam(defaultValue = "ASC") String direction,
                                        @RequestParam(defaultValue = "all") String govDepart,
                                        @RequestParam(defaultValue = "all") String govArrive,
                                        @RequestParam(defaultValue = "0") int min,
                                        @RequestParam(defaultValue = "100000") int max,
                                        @RequestParam(defaultValue = "2000-01-01T00:00:00Z") Instant dateDepart,
                                        @RequestParam(defaultValue = "0") int place,
                                        @RequestParam(defaultValue = "true") boolean fumer

    ) {
        return covoiturageService.main(pageNo, pageSize, sortBy, direction, govDepart
                , govArrive, min, max, dateDepart, place, fumer);
    }

}
