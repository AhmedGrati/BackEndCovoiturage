package BackEndCovoiturage.Controller;

import BackEndCovoiturage.Repository.VilleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/ville")
@CrossOrigin
public class VilleController {

    @Autowired
    private VilleRepo villeRepo;

    @GetMapping(value = "{nameOfVille}")
    public boolean villeExistByName(@PathVariable @NonNull String nameOfVille){
        return this.villeRepo.existsByName(nameOfVille);
    }
}
