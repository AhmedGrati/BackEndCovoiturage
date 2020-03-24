package BackEndCovoiturage.Service;

import BackEndCovoiturage.Model.Covoiturage;
import BackEndCovoiturage.Repository.CovoiturageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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

    public List<Covoiturage> findAllPagedCovoiturage(int pageNo , int pageSize , String sortBy , boolean allowOld ){
        Pageable pageable = PageRequest.of(pageNo , pageSize , Sort.by(sortBy));
        Page<Covoiturage> pagedCovoiturage = this.covoiturageRepo.findAll(pageable);
        if(pagedCovoiturage.hasContent()){
            if(allowOld){
                return pagedCovoiturage.getContent();
            }else{ // if allow old is equal to false it means that it's not allowd to return "covoiturages" which their date are expired
                Date currentDate = new Date();//the current date (date of now)
                ArrayList<Covoiturage> returnedCovoiturages = new ArrayList<Covoiturage>();
                for(int i=0;i<pagedCovoiturage.getContent().size();i++){
                    Date covoiturageDate = pagedCovoiturage.getContent().get(i).getDatedepart();
                    if(currentDate.before(covoiturageDate)){ //if the date of the "covoiturage" is not expired i return it
                        returnedCovoiturages.add(pagedCovoiturage.getContent().get(i));
                    }
                }
                return returnedCovoiturages;
            }
        }else{
            return new ArrayList<Covoiturage>();
        }
    }

    public int getCovoiturageNumber(){
        return this.covoiturageRepo.getCovoiturageNumber();
    }



}
