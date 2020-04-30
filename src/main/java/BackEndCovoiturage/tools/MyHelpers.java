package BackEndCovoiturage.tools;


import org.springframework.data.domain.Page;

import java.util.HashMap;

public class MyHelpers {
    static public <T> HashMap<String, Object> pageWrapper(Page<T> page) {
        HashMap<String, Object> res = new HashMap<>();
        res.put("data", page.getContent());
        res.put("fullLength", page.getTotalElements());
        return res;
    }

    static public <T> HashMap<String, Object> pageNextAndPrevWrapper(Page<T> page , Page<T> nextPageData , Page<T> prevägeData) {
        HashMap<String, Object> res = new HashMap<>();
        res.put("data", page.getContent());
        res.put("nextPageData",nextPageData.getContent());
        res.put("prevPageData",prevägeData.getContent());
        res.put("fullLength", page.getTotalElements());
        return res;
    }


}
