package BackEndCovoiturage.Service;

import BackEndCovoiturage.Model.Covoiturage;
import BackEndCovoiturage.Model.Gouvernorat;
import BackEndCovoiturage.Model.Ville;
import BackEndCovoiturage.Repository.CovoiturageRepo;
import BackEndCovoiturage.Repository.GouvernoratRepo;
import BackEndCovoiturage.Repository.VilleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class CovoiturageService {

    @Autowired
    private CovoiturageRepo covoiturageRepo;

    @Autowired
    private GouvernoratRepo gouvernoratRepo;

    @Autowired
    private VilleRepo villeRepo;

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

    public HashMap<String ,Covoiturage> findAllPagedCovoiturage(int pageNo , int pageSize , String sortBy , boolean allowOld ){
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Covoiturage> pagedCovoiturage = this.covoiturageRepo.findAll(allowOld, pageable);
        if (pagedCovoiturage.hasContent()) {
            HashMap res = new HashMap<>();
            res.put("data",pagedCovoiturage.getContent());
            res.put("fullLength",pagedCovoiturage.getTotalElements());
            return res;
        } else {
            return new HashMap<String ,Covoiturage>();
        }
    }

    public int getCovoiturageNumber(){
        return this.covoiturageRepo.getCovoiturageNumber();
    }

    public HashMap<String ,Covoiturage> findCovoituragesByGouvernoratDepartAndByGouvernoratArrive(int pageNo,
                                                                                       int pageSize ,
                                                                                       String sortBy ,
                                                                                       String nameOfGouvDepart ,
                                                                                       String nameOfGouvArrive
                                                                                       ) {
        Gouvernorat gouvernoratDepart = this.gouvernoratRepo.findGouvernoratByName(nameOfGouvDepart);
        Gouvernorat gouvernoratArrive = this.gouvernoratRepo.findGouvernoratByName(nameOfGouvArrive);
        Pageable pageable = PageRequest.of(pageNo , pageSize , Sort.by(sortBy));
        Page<Covoiturage> pagedCovoiturage = this.covoiturageRepo.findAllByGouvernoratDepartAndGouvernoratArrive(gouvernoratDepart , gouvernoratArrive , pageable);

        if(pagedCovoiturage.hasContent()) {
            HashMap res = new HashMap<>();
            res.put("data",pagedCovoiturage.getContent());
            res.put("fullLength",pagedCovoiturage.getTotalElements());
            return res;
        } else {
            return new HashMap<String ,Covoiturage>();
        }
    }

    public HashMap<String ,Covoiturage> findCovoituragesByVilleDepartAndByVilleArrive(int pageNo ,
                                                                           int pageSize ,
                                                                           String sortBy ,
                                                                           String nameOfVilleDepart ,
                                                                           String nameOfVilleArrive
    ) {
        Ville villeDepart = this.villeRepo.findVilleByName(nameOfVilleDepart);
        Ville villeArrive = this.villeRepo.findVilleByName(nameOfVilleArrive);
        Pageable pageable = PageRequest.of(pageNo , pageSize , Sort.by(sortBy));
        Page<Covoiturage> pagedCovoiturage = this.covoiturageRepo.findAllByVilleDepartAndVilleArrivee(villeDepart , villeArrive , pageable);
        if(pagedCovoiturage.hasContent()) {
            HashMap res = new HashMap<>();
            res.put("data",pagedCovoiturage.getContent());
            res.put("fullLength",pagedCovoiturage.getTotalElements());
            return res;
        } else {
            return new HashMap<>();
        }
    }

}
