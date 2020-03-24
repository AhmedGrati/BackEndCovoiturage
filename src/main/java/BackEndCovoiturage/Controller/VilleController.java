package BackEndCovoiturage.Controller;

import BackEndCovoiturage.Repository.VilleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/ville")
public class VilleController {

    @Autowired
    private VilleRepo villeRepo;

    @GetMapping(value = "{nameOfVille}")
    public boolean villeExistByName(@PathVariable @NonNull String nameOfVille){
        return this.villeRepo.existsByName(nameOfVille);
    }
}
