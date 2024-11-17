package jwlee.swm_refactoring.admin.site;

import jwlee.swm_refactoring.infrastructor.admin.manager.dto.AdminReq;
import jwlee.swm_refactoring.domain.admin.manager.service.AdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AdminServiceTest {

    @Autowired
    private AdminService adminService; // 실제 AdminService 주입

    @Test
    void testPasswordEncoding() {
        // given
        AdminReq adminReq = AdminReq.builder()
                .password("plainPassword")
                .build();

        // when
        adminService.sellerCreate(adminReq.toAdmin());

        // 암호화된 비밀번호를 로그에서 확인할 수 있음
//        String encodedPassword = adminService.getEncodedPasswordForTest();
//
//        // then
//        assertNotEquals("plainPassword", encodedPassword); // 암호화된 값이 원문과 다른지 확인
//        assertTrue(adminService.matchesPassword("plainPassword", encodedPassword)); // 매칭 확인
    }

}