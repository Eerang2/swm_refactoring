package jwlee.swm_refactoring.infrastructor.admin.manager.dto;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginRes {

    private Long id;
    private String token;
}
