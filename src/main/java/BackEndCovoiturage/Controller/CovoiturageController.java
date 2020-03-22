package BackEndCovoiturage.Controller;

import BackEndCovoiturage.Model.Covoiturage;
import BackEndCovoiturage.Service.CovoiturageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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
    public Covoiturage saveCovoiturage(Covoiturage covoiturage){
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
}
