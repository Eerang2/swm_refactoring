package jwlee.swm_refactoring.domain.admin.manager.model;

import jwlee.swm_refactoring.domain.admin.manager.repository.entity.AdminEntity;
import jwlee.swm_refactoring.domain.admin.manager.enums.AdminRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Admin {

    private Long id;
    private String adminId;
    private String password;
    private AdminRole adminRole;

    public static Admin from(AdminEntity entity) {
        return Admin.builder()
                .id(entity.getId())
                .adminId(entity.getAdminId())
                .password(entity.getPassword())
                .adminRole(entity.getAdminRole())
                .build();
    }

    public AdminEntity toEntity() {
        return AdminEntity.builder()
                .id(id)
                .adminId(adminId)
                .password(password)
                .adminRole(adminRole)
                .build();
    }
}
