package jwlee.swm_refactoring.domain.model;

import jwlee.swm_refactoring.domain.enums.ImageType;
import lombok.Getter;

import javax.imageio.ImageTypeSpecifier;

@Getter
public class AccommodationImage {

    private Long id;
    private ImageType imageType;
    private String path;
    private Long accommodationId;


}
