package jwlee.swm_refactoring.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccommodationType {
    HOTEL("HOTEL"), MOTEL("MOTEL"), PENSION("PENSION"), RESORT("RESORT");
    private String value;

}
