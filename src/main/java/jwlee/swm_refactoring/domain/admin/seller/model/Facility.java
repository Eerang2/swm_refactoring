package jwlee.swm_refactoring.domain.admin.seller.model;

import jwlee.swm_refactoring.domain.admin.seller.repository.entity.FacilityEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Facility {

    /**
     * 부대 시설
     */
    private Long id;
    private String name;
    private Long accommodationId;

    public static Facility from(FacilityEntity entity) {
        return Facility.builder()
                .id(entity.getId())
                .name(entity.getName())
                .accommodationId(entity.getAccommodationId())
                .build();
    }

    public FacilityEntity toEntity() {
        return FacilityEntity.builder()
                .id(id)
                .name(name)
                .accommodationId(accommodationId)
                .build();


    }
}
