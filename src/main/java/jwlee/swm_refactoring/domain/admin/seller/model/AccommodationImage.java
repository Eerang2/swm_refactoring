package jwlee.swm_refactoring.domain.admin.seller.model;

import jwlee.swm_refactoring.domain.admin.seller.enums.ImageType;
import lombok.Getter;

@Getter
public class AccommodationImage {

    private Long id;
    private ImageType imageType;
    private String path;
    private Long accommodationId;


}
