package BackEndCovoiturage.tools;


import BackEndCovoiturage.Model.Covoiturage;
import BackEndCovoiturage.Model.Submission;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.List;

public class MyHelpers {

    static public <T> HashMap<String, Object> pageWrapper(Page<T> page) {
        HashMap<String, Object> res = new HashMap<>();
        res.put("data", page.getContent());
        res.put("fullLength", page.getTotalElements());
        return res;
    }

    static public <T> HashMap<String, Object> pageNextAndPrevWrapper(Page<T> page, Page<T> nextPageData, Page<T> prevPageData) {
        HashMap<String, Object> res = new HashMap<>();
        res.put("data", page.getContent());
        res.put("nextPageData", nextPageData.getContent());
        res.put("prevPageData", prevPageData.getContent());
        res.put("fullLength", page.getTotalElements());
        return res;
    }

    static public <T> HashMap<String , Object> wrapCovAndSub(Covoiturage covoiturage , List<Submission> submissions) {
        HashMap<String , Object> res = new HashMap<>();
        res.put("cov",covoiturage);
        res.put("submission",submissions);
        return  res;
    }

    static public <T> HashMap<String,Object> wrapArrays(List<HashMap<String,Object>> myObjectList , Page<T> page) {
        HashMap <String , Object> res = new HashMap<>();
        res.put("data",myObjectList);
        res.put("totalLength",page.getTotalElements());

        return res;
    }

}
