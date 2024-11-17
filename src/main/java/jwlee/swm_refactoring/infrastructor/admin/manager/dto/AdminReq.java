package jwlee.swm_refactoring.infrastructor.admin.manager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jwlee.swm_refactoring.domain.admin.manager.enums.AdminRole;
import jwlee.swm_refactoring.domain.admin.manager.model.Admin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminReq {

    @NotBlank(message =  "아이디는 필수 입력값입니다")
    @Pattern(regexp = "^(?=.*[a-z])[a-z0-9]{6,20}$", message = "영문 소문자 + 숫자, 6 ~ 20자리여야 합니다.")
    private String adminId;

    @NotBlank(message = "비밀번호는 필수 입력값입니다.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,20}$", message = "영문+숫자+특수문자를 포함하여 8~20자로 입력하세요.")
    private String password;

    @NotNull
    private AdminRole adminRole;

    public Admin toAdmin() {
        return Admin.builder()
                .adminId(adminId)
                .password(password)
                .adminRole(adminRole)
                .build();
    }

}
