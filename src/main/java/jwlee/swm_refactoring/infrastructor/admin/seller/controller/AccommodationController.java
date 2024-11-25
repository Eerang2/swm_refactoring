package jwlee.swm_refactoring.infrastructor.admin.seller.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AccommodationController {
    @GetMapping("/admin/seller/{id}")
    public String index(@PathVariable("id") long id, Model model) {
        model.addAttribute("id", id);
        return "seller/seller";
    }

    @GetMapping("/admin/seller/enroll")
    public String enroll() {
        return "accommodation/enroll";
    }

    @GetMapping("/admin/seller/update")
    public String update() {
        return "accommodation/update";
    }


    // POST 로 바꿔서 데이터 값 받기
    @GetMapping("/seller/ss/{id}")
    public String updateAccommodation(@PathVariable("id") Long id) {
        return "seller/seller";
    }
}

