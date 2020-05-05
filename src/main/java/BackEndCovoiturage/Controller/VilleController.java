package BackEndCovoiturage.Controller;

import BackEndCovoiturage.Model.Ville;
import BackEndCovoiturage.Service.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/ville")
@CrossOrigin
public class VilleController {

    @GetMapping("all")
    public Iterable<Ville> getAll() {
        return this.villeService.getAll();
    }

    @Autowired
    private VilleService villeService;

    @GetMapping(value = "{nameOfVille}")
    public boolean villeExistByName(@PathVariable @NonNull String nameOfVille) {
        System.out.println(nameOfVille);
        return this.villeService.villeExistsByName(nameOfVille);
    }
}
