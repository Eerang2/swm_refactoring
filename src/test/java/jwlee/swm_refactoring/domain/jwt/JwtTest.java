package jwlee.swm_refactoring.domain.jwt;

import jwlee.swm_refactoring.domain.admin.manager.enums.AdminRole;
import jwlee.swm_refactoring.domain.admin.manager.model.Admin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class JwtTest {

    @Autowired
    private JwtUtil jwtUtil;

    @Test
    @DisplayName("토큰생성해보기")
    void 토큰생성해보기() {
        String newToken = jwtUtil.createAccessToken(new Admin(2L, "seller001", "dlwlsdn1!!", AdminRole.ACCOMMODATION_ADMIN));
        Assertions.assertNotEquals("", newToken);
    }

    @Test
    @DisplayName("토큰에서_회원번호_꺼내보기")
    void 토큰에서_회원번호_꺼내보기() {
        String accessToken = jwtUtil.createAccessToken(new Admin(2L, "seller001", "dlwlsdn1!!", AdminRole.ACCOMMODATION_ADMIN));
        Admin loginUser = jwtUtil.getLoginUserFromAccessToken(accessToken);
        Assertions.assertEquals(2L,loginUser.getId());
    }

    @Test
    void testGetLoginUserFromAccessToken() {
        // Arrange: 테스트용 Admin 객체 및 토큰 생성
        Admin admin = Admin.builder()
                .id(1L)
                .adminId("testAdmin")
                .build();
        String accessToken = jwtUtil.createAccessToken(admin);

        // Act: 토큰에서 Admin 정보 추출
        Admin extractedAdmin = jwtUtil.getLoginUserFromAccessToken(accessToken);

        // Assert: 추출된 정보 검증
        assertNotNull(extractedAdmin);
        assertEquals(admin.getId(), extractedAdmin.getId());
        assertEquals(admin.getAdminId(), extractedAdmin.getAdminId());
    }
}