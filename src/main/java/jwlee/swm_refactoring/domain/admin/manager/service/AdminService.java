package jwlee.swm_refactoring.domain.admin.manager.service;

import jwlee.swm_refactoring.domain.admin.manager.enums.AdminRole;
import jwlee.swm_refactoring.domain.admin.manager.model.Admin;
import jwlee.swm_refactoring.domain.admin.manager.repository.AdminCreateRepository;
import jwlee.swm_refactoring.domain.admin.manager.repository.entity.AdminEntity;
import jwlee.swm_refactoring.domain.jwt.JwtUtil;
import jwlee.swm_refactoring.infrastructor.admin.manager.dto.LoginRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminService {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final AdminCreateRepository adminCreateRepository;
    private final JwtUtil jwtUtil;
    private String encode;

    public Admin sellerCreate(Admin admin) {
        String encodePwd = passwordEncoder.encode(admin.getPassword());
        log.info("encoded password: {}", encode);

        Admin encodedAdmin = Admin.builder()
                .adminId(admin.getAdminId())
                .password(encodePwd)
                .adminRole(admin.getAdminRole())
                .build();
        AdminEntity save = adminCreateRepository.save(encodedAdmin.toEntity());
        return Admin.from(save);
    }


    public LoginRes authenticateAndGenerateToken(Admin loginRequest) {

        // 1. 아이디로 사용자 조회
        AdminEntity admin = adminCreateRepository.findByAdminId(loginRequest.getAdminId())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        // 2. 비밀번호 확인
        if (!passwordEncoder.matches(loginRequest.getPassword(), admin.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        // 사이트와 판매자 정보 구분하고 리턴값으로 페이지전환
        return LoginRes.builder()
                .id(admin.getId())
                .token(jwtUtil.createAccessToken(Admin.from(admin)))
                .build();
    }
}
