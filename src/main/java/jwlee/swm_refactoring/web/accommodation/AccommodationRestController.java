package jwlee.swm_refactoring.web.accommodation;

import jwlee.swm_refactoring.domain.model.Accommodation;
import jwlee.swm_refactoring.domain.service.AccommodationService;
import jwlee.swm_refactoring.web.dto.AccommodationReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AccommodationRestController {

    private final AccommodationService accommodationService;

    @PostMapping("/api/accommodation/create")
    public Accommodation createAccommodation(@RequestBody AccommodationReq accommodationReq)  {
        return accommodationService.save(accommodationReq.toAccommodation());

    }
}
