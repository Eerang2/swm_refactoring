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
    @GetMapping("/accommodation/update")
    public String update() {
        return "seller/seller";
    }


    // POST 로 바꿔서 데이터 값 받기
    @GetMapping("/seller/{id}")
    public String updateAccommodation(@PathVariable("id") Long id) {
        return "seller/seller";
    }

    @GetMapping("admin/login")
    public String login() {
        return "web_manager/webcenter_login";
    }
}

