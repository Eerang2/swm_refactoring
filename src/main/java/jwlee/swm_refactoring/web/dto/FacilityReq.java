package jwlee.swm_refactoring.web.dto;

import jwlee.swm_refactoring.domain.model.admin.seller.Facility;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FacilityReq {

    private List<String> facilities;
    private Long accommodationId;

    public List<Facility> toFacility() {
        return facilities.stream()
                .map(facilityName -> Facility.builder()
                        .name(facilityName)
                        .accommodationId(accommodationId)
                        .build())
                .collect(Collectors.toList());
    }
}
