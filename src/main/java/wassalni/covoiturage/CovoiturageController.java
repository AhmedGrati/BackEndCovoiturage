package wassalni.covoiturage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CovoiturageController {
    @Autowired
    CovoiturageRepo covoiturageRepo;

    @GetMapping("/rand")
    public void f() {
        Covoiturage covoiturage = new Covoiturage();
        covoiturage.setDestination("gegw");
        covoiturageRepo.save(covoiturage);
    }


}
