package jwlee.swm_refactoring.web.accommodation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AccommodationController {
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/accommodation/create")
    public String enroll() {
        return "accommodation/enroll";
    }
    @GetMapping("/accommodation/{id}")
    public String enroll(@PathVariable long id) {
        return "seller/seller";
    }
}

