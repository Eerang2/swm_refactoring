package jwlee.swm_refactoring.infrastructor.admin.seller.controller;

import jwlee.swm_refactoring.domain.admin.manager.model.Admin;
import jwlee.swm_refactoring.domain.admin.manager.repository.entity.AdminEntity;
import jwlee.swm_refactoring.domain.admin.seller.model.Accommodation;
import jwlee.swm_refactoring.domain.admin.seller.model.Facility;
import jwlee.swm_refactoring.domain.admin.seller.service.AccommodationService;
import jwlee.swm_refactoring.domain.jwt.JwtUtil;
import jwlee.swm_refactoring.infrastructor.admin.seller.dto.AccommodationReq;
import jwlee.swm_refactoring.infrastructor.admin.seller.dto.FacilityReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/api")
@RequiredArgsConstructor
public class AccommodationRestController {

    private final AccommodationService accommodationService;
    private final JwtUtil jwtUtil;

    @PostMapping("/accommodation/create")
    public Accommodation createAccommodation(@RequestBody AccommodationReq accommodationReq)  {
        return accommodationService.saveAccommodation(accommodationReq.toAccommodation());
    }

    @PostMapping("/facility/add")
    public void addFacility(@RequestBody FacilityReq facilityReq) {
        List<Facility> facilities = facilityReq.toFacility();
        accommodationService.saveFacilities(facilities);
    }

    @GetMapping("/get/id")
    public Accommodation getAccommodation(@CookieValue(value = "AUTH_ACCESS_TOKEN", required = false) String token) {
        if (token == null || token.isEmpty()) {
             return null;
        }
            Admin admin = jwtUtil.getLoginUserFromAccessToken(token);

            // 필요 시 DB에서 추가 정보를 가져올 수 있음
            accommodationService.findById(admin.getId());
            return Accommodation.builder().build();

    }
}
