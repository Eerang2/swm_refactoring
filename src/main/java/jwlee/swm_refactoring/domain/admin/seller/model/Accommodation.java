package jwlee.swm_refactoring.domain.admin.seller.model;

import jwlee.swm_refactoring.domain.admin.seller.enums.AccommodationType;
import jwlee.swm_refactoring.domain.admin.seller.repository.entity.AccommodationEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Accommodation {

    private long id;
    private String name;
    private String phone;
    private AccommodationType accommodationType;        // 숙소 형태
    private String description;         // 숙소 정보란
    private GeoLocation geoLocation;    // 위경도
    private String address1;    // 지번
    private String address2;     // 도로명

    private String mainImagePath;

    private Long sellerAdminId;

    public static Accommodation from(AccommodationEntity entity) {
        return Accommodation.builder()
                .id(entity.getId())
                .name(entity.getName())
                .phone(entity.getPhone())
                .accommodationType(entity.getAccommodationType())
                .description(entity.getDescription())
                .geoLocation(new GeoLocation(entity.getLatitude(), entity.getLatitude()))
                .address1(entity.getAddress1())
                .address2(entity.getAddress2())
                .sellerAdminId(entity.getSellerAdminId())
                .build();
    }

    public AccommodationEntity toEntity() {
        return AccommodationEntity.builder()
                .id(this.id)
                .name(this.name)
                .phone(this.phone)
                .description(this.description)
                .accommodationType(this.accommodationType)
                .longitude(this.getGeoLocation().getLongitude())
                .latitude(this.getGeoLocation().getLatitude())
                .address1(this.address1)
                .address2(this.address2)
                .build();
    }
}
