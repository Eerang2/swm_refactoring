package jwlee.swm_refactoring.domain.admin.manager.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AdminRole {
    ACCOMMODATION_ADMIN("ACCOMMODATION_ADMIN"), SITE_ADMIN("SITE_ADMIN");
    private String value;
}
