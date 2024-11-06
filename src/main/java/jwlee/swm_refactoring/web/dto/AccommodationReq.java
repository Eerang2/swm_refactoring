package jwlee.swm_refactoring.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jwlee.swm_refactoring.domain.enums.AccommodationType;
import jwlee.swm_refactoring.domain.model.Accommodation;
import jwlee.swm_refactoring.domain.model.GeoLocation;
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

    public Accommodation toAccommodation() {
        return Accommodation.builder()
                .name(name)
                .phone(phone)
                .description(description)
                .accommodationType(accommodationType)
                .address1(address1)
                .address2(address2)
                .geoLocation(new GeoLocation(longitude, latitude))
                .build();
    }
}
