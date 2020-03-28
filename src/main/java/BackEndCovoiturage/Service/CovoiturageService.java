package BackEndCovoiturage.Service;

import BackEndCovoiturage.Model.Covoiturage;
import BackEndCovoiturage.Model.Gouvernorat;
import BackEndCovoiturage.Model.Ville;
import BackEndCovoiturage.Repository.CovoiturageRepo;
import BackEndCovoiturage.Repository.GouvernoratRepo;
import BackEndCovoiturage.Repository.VilleRepo;
import BackEndCovoiturage.tools.MyHelpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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

    public List<Covoiturage> getAllCovoiturages() {
        return this.covoiturageRepo.findAll();
    }

    public Covoiturage getCovoiturageById(long id) {
        return this.covoiturageRepo.getCovoiturageById(id);
    }

    public void deleteCovoiturageById(long id) {
        this.covoiturageRepo.deleteCovoiturageById(id);
    }

    public Covoiturage saveCovoiturage(Covoiturage covoiturage) {
        return this.covoiturageRepo.save(covoiturage);
    }

    public HashMap<String, Object> findAllPagedCovoiturage(int pageNo, int pageSize, String sortBy, boolean allowOld) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Covoiturage> pagedCovoiturage = this.covoiturageRepo.findAll(allowOld, pageable);

        return MyHelpers.pageWrapper(pagedCovoiturage);
    }


    public int getCovoiturageNumber() {
        return this.covoiturageRepo.getCovoiturageNumber();
    }


    public HashMap<String ,Object> findCovoituragesByGouvernoratDepartAndByGouvernoratArrive(int pageNo,
                                                                                       int pageSize ,
                                                                                       String sortBy ,
                                                                                       String nameOfGouvDepart ,
                                                                                       String nameOfGouvArrive
                                                                                       ) {
        if((nameOfGouvArrive==null)&&(nameOfGouvDepart==null)){
            HashMap res = new HashMap<>();
            res.put("data",this.covoiturageRepo.findAll());
            res.put("fullLength",this.covoiturageRepo.getCovoiturageNumber());
            return res;
        }

        //else
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
            return new HashMap<String ,Object>();
        }
    }

    public HashMap<String, Object> findCovoituragesByVilleDepartAndByVilleArrive(int pageNo,
                                                                                 int pageSize,
                                                                                 String sortBy,
                                                                                 String nameOfVilleDepart,
                                                                                 String nameOfVilleArrive

    ) {
        if((nameOfVilleArrive==null)&&(nameOfVilleDepart==null)){
            HashMap res = new HashMap<>();
            res.put("data",this.covoiturageRepo.findAll());
            res.put("fullLength",this.covoiturageRepo.getCovoiturageNumber());
            return res;
        }

        //else
        Ville villeDepart = this.villeRepo.findVilleByName(nameOfVilleDepart);
        Ville villeArrive = this.villeRepo.findVilleByName(nameOfVilleArrive);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Covoiturage> pagedCovoiturage = this.covoiturageRepo.findAllByVilleDepartAndVilleArrivee(villeDepart, villeArrive, pageable);

        return MyHelpers.pageWrapper(pagedCovoiturage);

    }


}
