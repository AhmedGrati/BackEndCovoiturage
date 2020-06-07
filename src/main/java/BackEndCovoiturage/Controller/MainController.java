package BackEndCovoiturage.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@CrossOrigin
public class MainController {
    @GetMapping("")
    public RedirectView redirectToDocs(){
        return new RedirectView("swagger-ui/index.html?configUrl=%2Fapi-docs%2Fswagger-config");
    }
}
