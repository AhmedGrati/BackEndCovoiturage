package BackEndCovoiturage.Service;

import BackEndCovoiturage.Model.Covoiturage;
import BackEndCovoiturage.Model.Gouvernorat;
import BackEndCovoiturage.Model.User;
import BackEndCovoiturage.Model.Ville;
import BackEndCovoiturage.Repository.CovoiturageRepo;
import BackEndCovoiturage.Repository.GouvernoratRepo;
import BackEndCovoiturage.Repository.UserRepo;
import BackEndCovoiturage.Repository.VilleRepo;
import BackEndCovoiturage.tools.MyHelpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;


@Service
public class CovoiturageService {

    @Autowired
    private CovoiturageRepo covoiturageRepo;

    @Autowired
    private GouvernoratRepo gouvernoratRepo;

    @Autowired
    private UserRepo userRepo;

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


    public HashMap<String, Object> findCovoituragesByGouvernoratDepartAndByGouvernoratArrive(int pageNo,
                                                                                             int pageSize,
                                                                                             String sortBy,
                                                                                             String nameOfGouvDepart,
                                                                                             String nameOfGouvArrive
    ) {
        if ((nameOfGouvArrive == null) && (nameOfGouvDepart == null)) {
            HashMap res = new HashMap<>();
            res.put("data", this.covoiturageRepo.findAll());
            res.put("fullLength", this.covoiturageRepo.getCovoiturageNumber());
            return res;
        }

        //else
        Gouvernorat gouvernoratDepart = this.gouvernoratRepo.findGouvernoratByName(nameOfGouvDepart);
        Gouvernorat gouvernoratArrive = this.gouvernoratRepo.findGouvernoratByName(nameOfGouvArrive);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Covoiturage> pagedCovoiturage = this.covoiturageRepo.findAllByGouvernoratDepartAndGouvernoratArrive(gouvernoratDepart, gouvernoratArrive, pageable);

        if (pagedCovoiturage.hasContent()) {
            HashMap res = new HashMap<>();
            res.put("data", pagedCovoiturage.getContent());
            res.put("fullLength", pagedCovoiturage.getTotalElements());
            return res;
        } else {
            return new HashMap<String, Object>();
        }
    }

    public HashMap<String, Object> findCovoituragesByVilleDepartAndByVilleArrive(int pageNo,
                                                                                 int pageSize,
                                                                                 String sortBy,
                                                                                 String nameOfVilleDepart,
                                                                                 String nameOfVilleArrive

    ) {
        if ((nameOfVilleArrive == null) && (nameOfVilleDepart == null)) {
            HashMap<String, Object> res = new HashMap<>();
            res.put("data", this.covoiturageRepo.findAll());
            res.put("fullLength", this.covoiturageRepo.getCovoiturageNumber());
            return res;
        }

        //else
        Ville villeDepart = this.villeRepo.findVilleByName(nameOfVilleDepart);
        Ville villeArrive = this.villeRepo.findVilleByName(nameOfVilleArrive);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Covoiturage> pagedCovoiturage = this.covoiturageRepo.findAllByVilleDepartAndVilleArrivee(villeDepart, villeArrive, pageable);

        return MyHelpers.pageWrapper(pagedCovoiturage);

    }


    public HashMap<String, Object> findCovoituragesByMultipleParameters(int pageNo,
                                        int pageSize,
                                        String sortBy,
                                        String direction,
                                        String govDepart,
                                        String govArrive,
                                        int min,
                                        int max,
                                        Instant dateDepart,
                                        int place,
                                        boolean fumer) {

        Sort.Direction d = direction.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;

        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(d, sortBy));

        Page<Covoiturage> pagedCovoiturage = this.covoiturageRepo.findCovoituragesByMultipleParameters(govDepart
                , govArrive, min, max, dateDepart, place, fumer, pageable);

        return MyHelpers.pageWrapper(pagedCovoiturage);

        /*if(pageNo==0) { // if we want the first page that means that previous page does not exist so we just need the page and the next one
            Pageable nextPageable = PageRequest.of(pageNo+1 , pageSize , Sort.by(d , sortBy));

            Page<Covoiturage> nextPage = this.covoiturageRepo.findCovoituragesByMultipleParameters( govDepart
                    , govArrive , min , max , dateDepart , place , fumer , nextPageable);

            return MyHelpers.pageNextAndPrevWrapper(pagedCovoiturage,nextPage, Page.empty());

        }else if (pageNo == pagedCovoiturage.getTotalPages()) {// if we want the last page that means that next page does not exist so we just need the page and the previous one
            Pageable previousPageable = PageRequest.of(pageNo-1 , pageSize , Sort.by(d , sortBy));

            Page<Covoiturage> previousPage = this.covoiturageRepo.findCovoituragesByMultipleParameters( govDepart
                    , govArrive , min , max , dateDepart , place , fumer , previousPageable);

            return MyHelpers.pageNextAndPrevWrapper(pagedCovoiturage,Page.empty(),previousPage);

        }else { // in this case we want the page and both : the previous and the next ones
            Pageable nextPageable = PageRequest.of(pageNo+1 , pageSize , Sort.by(d , sortBy));
            Page<Covoiturage> nextPage = this.covoiturageRepo.findCovoituragesByMultipleParameters( govDepart
                    , govArrive , min , max , dateDepart , place , fumer , nextPageable);

            Pageable previousPageable = PageRequest.of(pageNo-1 , pageSize , Sort.by(d , sortBy));
            Page<Covoiturage> previousPage = this.covoiturageRepo.findCovoituragesByMultipleParameters( govDepart
                    , govArrive , min , max , dateDepart , place , fumer , previousPageable);

            return MyHelpers.pageNextAndPrevWrapper(pagedCovoiturage,nextPage,previousPage);*/
        }

    public boolean participateToCovoiturage(long userId , long covoiturageId) {
        User user = userRepo.findUserById(userId);
        Covoiturage covoiturage = covoiturageRepo.getCovoiturageById(covoiturageId);
        if((user != null)&&(covoiturage != null)&&(covoiturage.getNbrPlaceDispo()>0)) {
            covoiturage.setNbrPlaceDispo(covoiturage.getNbrPlaceDispo()-1);
            covoiturage.getParticipants().add(user);
            covoiturageRepo.save(covoiturage);
            return true;
        }
        return false;
    }

    public boolean submitCovoiturage(long userId , Covoiturage covoiturage) {
        User user = userRepo.findUserById(userId);
        if(user != null) {
            covoiturage.setOwner(user);
            covoiturageRepo.save(covoiturage);
            return true;
        }

        return false;
    }

    public int sumOfCovoituragesByUser(long userId) {
        return this.covoiturageRepo.sumOfCovoituragesByUser(userId);
    }

    public void deleteCovoituragesByOwnerId(long userId) {
        this.covoiturageRepo.deleteCovoituragesByOwnerId(userId);
    }

    public List<Covoiturage> findRandomCovoituragesByGouvDepartAndGouvArrivee(long id) {
        Pageable limit = PageRequest.of(0, 3);
        Covoiturage covoiturage = this.covoiturageRepo.getCovoiturageById(id);
        if (covoiturage != null) {
            return this.covoiturageRepo.findCovoituragesByGouvArriveeAndGouvDepart(covoiturage.getGouvernoratDepart(), covoiturage.getGouvernoratArrive(), id, limit);
        }
        return List.of(); // empty list
    }

}

