package jwlee.swm_refactoring.web.accommodation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccommodationController {
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/enroll")
    public String enroll() {
        return "accommodation/enroll";
    }
}

