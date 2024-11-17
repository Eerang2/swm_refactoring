package jwlee.swm_refactoring.infrastructor.admin.seller.controller;

import jwlee.swm_refactoring.domain.admin.seller.model.Accommodation;
import jwlee.swm_refactoring.domain.admin.seller.model.Facility;
import jwlee.swm_refactoring.domain.admin.seller.service.AccommodationService;
import jwlee.swm_refactoring.infrastructor.admin.seller.dto.AccommodationReq;
import jwlee.swm_refactoring.infrastructor.admin.seller.dto.FacilityReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api")
@RequiredArgsConstructor
public class AccommodationRestController {

    private final AccommodationService accommodationService;

    @PostMapping("/accommodation/create")
    public Accommodation createAccommodation(@RequestBody AccommodationReq accommodationReq)  {
        return accommodationService.saveAccommodation(accommodationReq.toAccommodation());
    }

    @PostMapping("/facility/add")
    public void addFacility(@RequestBody FacilityReq facilityReq) {
        List<Facility> facilities = facilityReq.toFacility();
        accommodationService.saveFacilities(facilities);
    }
}
