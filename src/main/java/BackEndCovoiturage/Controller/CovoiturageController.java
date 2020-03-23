package BackEndCovoiturage.Controller;

import BackEndCovoiturage.Model.Covoiturage;
import BackEndCovoiturage.Service.CovoiturageService;
import BackEndCovoiturage.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping(path = "all")
    public List<Covoiturage> getAllCovoiturage(){
        return this.covoiturageService.getAllCovoiturages();
    }

    @PostMapping(path = "saveCovoiturage")
    public Covoiturage saveCovoiturage(@RequestBody @Valid Covoiturage.DTO covoiturageDTO) {


        return (this.covoiturageService.saveCovoiturage(
                covoiturageDTO.toCovoiturage(userService)
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
    public List<Covoiturage> getPagedCovoiturages(@RequestParam(defaultValue = "0") int pageNo
            , @RequestParam(defaultValue = "6") int pageSize
            , @RequestParam(defaultValue = "price") String sortBy){
        return(this.covoiturageService.findAllPagedCovoiturage(pageNo , pageSize , sortBy));
    }

    @GetMapping(path = "covoiturageNumber")
    public Map<String, Integer> getCovoiturageNumber() {
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("length", this.covoiturageService.getCovoiturageNumber());
        return hashMap;
    }


    @PostMapping("rand")
    public Covoiturage seed(){
        return new Covoiturage() ;
    }
}
