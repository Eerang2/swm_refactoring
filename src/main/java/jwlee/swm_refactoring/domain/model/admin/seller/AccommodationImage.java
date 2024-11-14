package jwlee.swm_refactoring.domain.model.admin.seller;

import jwlee.swm_refactoring.domain.enums.ImageType;
import lombok.Getter;

@Getter
public class AccommodationImage {

    private Long id;
    private ImageType imageType;
    private String path;
    private Long accommodationId;


}
