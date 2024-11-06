package jwlee.swm_refactoring.web.accommodation;

import jwlee.swm_refactoring.domain.enums.ErrorCode;
import jwlee.swm_refactoring.domain.exception.CustomException;
import jwlee.swm_refactoring.domain.model.Accommodation;
import jwlee.swm_refactoring.domain.service.AccommodationService;
import jwlee.swm_refactoring.web.common.BaseApiController;
import jwlee.swm_refactoring.web.dto.AccommodationReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AccommodationRestController {

    @Autowired
    private final AccommodationService accommodationService;

    @PostMapping("/api/accommodation/create")
    public Accommodation createAccommodation(@RequestBody AccommodationReq accommodationReq)  {
        return accommodationService.save(accommodationReq.toAccommodation());
    }
}
