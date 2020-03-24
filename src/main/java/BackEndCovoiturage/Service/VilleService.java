package BackEndCovoiturage.Service;

import BackEndCovoiturage.Repository.VilleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VilleService {

    @Autowired
    private VilleRepo villeRepo;

    public boolean villeExistsByName(String name){
        return this.villeRepo.existsByName(name);
    }

}
