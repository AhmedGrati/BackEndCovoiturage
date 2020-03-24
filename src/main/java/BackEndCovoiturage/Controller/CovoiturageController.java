package BackEndCovoiturage.Controller;

import BackEndCovoiturage.Model.Covoiturage;
import BackEndCovoiturage.Service.CovoiturageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "api/covoiturage")
public class CovoiturageController {

    @Autowired
    public CovoiturageService covoiturageService;

    @GetMapping(path = "all")
    public List<Covoiturage> getAllCovoiturage(){
        return this.covoiturageService.getAllCovoiturages();
    }

    @PostMapping(path = "saveCovoiturage")
    public Covoiturage saveCovoiturage(@RequestBody @Valid Covoiturage covoiturage){
        System.out.println("covoiturage : "+covoiturage);
        return (this.covoiturageService.saveCovoiturage(covoiturage));
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
            , @RequestParam(defaultValue = "price") String sortBy
            , @RequestParam(defaultValue = "true") boolean allowOld) {
        return(this.covoiturageService.findAllPagedCovoiturage(pageNo , pageSize , sortBy , allowOld));
    }

    @GetMapping(path="covoiturageNumber")
    public int getCovoiturageNumber(){
        return this.covoiturageService.getCovoiturageNumber();
    }
}