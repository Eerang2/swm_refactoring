package jwlee.swm_refactoring.infrastructor.admin.manager.dto;


import jwlee.swm_refactoring.domain.admin.manager.enums.AdminRole;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginRes {

    private Long id;
    private String token;
    private AdminRole role;
}
