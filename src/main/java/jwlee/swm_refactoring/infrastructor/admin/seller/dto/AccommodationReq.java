package jwlee.swm_refactoring.infrastructor.admin.seller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jwlee.swm_refactoring.domain.admin.seller.enums.AccommodationType;
import jwlee.swm_refactoring.domain.admin.seller.model.Accommodation;
import jwlee.swm_refactoring.domain.admin.seller.model.GeoLocation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccommodationReq {

    @NotBlank(message = "이름은 필수입니다.")
    private String name;

    @NotBlank(message = "이름은 필수입니다.")
    private String phone;

    @NotBlank(message = "이름은 필수입니다.")
    private AccommodationType accommodationType;

    @NotNull
    private double longitude;
    private double latitude;
    private String address1;    // 지번
    private String address2;

    @NotNull
    private String description;

    private String imagePath;

    private Long sellerAdminId;

    public Accommodation toAccommodation() {
        return Accommodation.builder()
                .name(name)
                .phone(phone)
                .description(description)
                .accommodationType(accommodationType)
                .address1(address1)
                .address2(address2)
                .geoLocation(new GeoLocation(longitude, latitude))
                .sellerAdminId(sellerAdminId)
                .build();
    }
}
