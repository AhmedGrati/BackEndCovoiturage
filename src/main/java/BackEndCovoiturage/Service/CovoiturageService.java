package BackEndCovoiturage.Service;

import BackEndCovoiturage.Model.Covoiturage;
import BackEndCovoiturage.Repository.CovoiturageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CovoiturageService {

    @Autowired
    private CovoiturageRepo covoiturageRepo;

    public List<Covoiturage> getAllCovoiturages(){
        return this.covoiturageRepo.findAll();
    }

    public Covoiturage getCovoiturageById(long id){
        return this.covoiturageRepo.getCovoiturageById(id);
    }

    public void deleteCovoiturageById(long id){
        this.covoiturageRepo.deleteCovoiturageById(id);
    }

    public Covoiturage saveCovoiturage(Covoiturage covoiturage){
        return this.covoiturageRepo.save(covoiturage);
    }



}
