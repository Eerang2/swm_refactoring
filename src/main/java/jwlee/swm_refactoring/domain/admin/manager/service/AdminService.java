package jwlee.swm_refactoring.domain.admin.manager.service;

import jwlee.swm_refactoring.domain.admin.manager.model.Admin;
import jwlee.swm_refactoring.domain.admin.manager.repository.AdminCreateRepository;
import jwlee.swm_refactoring.domain.admin.manager.repository.entity.AdminEntity;
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

}
