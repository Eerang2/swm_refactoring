package jwlee.swm_refactoring.infrastructor.admin.seller.controller;

import jwlee.swm_refactoring.domain.admin.manager.model.Admin;
import jwlee.swm_refactoring.domain.admin.seller.model.Accommodation;
import jwlee.swm_refactoring.domain.admin.seller.model.Facility;
import jwlee.swm_refactoring.domain.admin.seller.service.AccommodationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class AccommodationController {

    private final AccommodationService accommodationService;



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
    public String update(Admin admin, Model model) {

        log.info("admin : {}" , admin.getId());
        Accommodation seller = accommodationService.findSellerById(admin.getId());
        List<String> facilities = accommodationService.findFacilitiesById(seller.getId());
        log.info("facilities : {}" , facilities.get(1));
        model.addAttribute("seller", seller);
        model.addAttribute("facilities", facilities);
        return "accommodation/update";
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    // POST 로 바꿔서 데이터 값 받기
    @GetMapping("/seller/ss/{id}")
    public String updateAccommodation(@PathVariable("id") Long id) {
        return "seller/seller";
    }
}

