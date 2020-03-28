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
}
