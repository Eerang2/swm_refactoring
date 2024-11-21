package jwlee.swm_refactoring.infrastructor.admin.manager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import jwlee.swm_refactoring.common.BaseMockMvcTest;
import jwlee.swm_refactoring.domain.admin.manager.enums.AdminRole;
import jwlee.swm_refactoring.domain.admin.manager.model.Admin;
import jwlee.swm_refactoring.domain.jwt.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class AdminRestControllerTest extends BaseMockMvcTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JwtUtil jwtUtil;

    @Test
    void 토큰생성해서_헤더에_토큰넣고_컨트롤러_호출해서_정상인지_확인하기() throws Exception {
        Admin testLoginUser = new Admin(7L,  "seller001", "dlwlsdn1!", AdminRole.ACCOMMODATION_ADMIN);

        String testAccessToken = jwtUtil.createAccessToken(testLoginUser);

        final ResultActions actions = mockMvc.perform(post("/api/admin/login")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + testAccessToken)
        );

        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(testLoginUser.getId()))
                .andExpect(jsonPath("$.adminId").value(testLoginUser.getAdminId()));
    }

    @Test
    void 토큰생성해서_쿠키에_토큰넣고_컨트롤러_호출해서_정상인지_확인하기() throws Exception {
        Admin testLoginUser = new Admin(7L,  "seller001", "dlwlsdn1!", AdminRole.ACCOMMODATION_ADMIN);

        String testAccessToken = jwtUtil.createAccessToken(testLoginUser);

        Cookie authCookie = new Cookie("AUTH_ACCESS_TOKEN", testAccessToken);

        final ResultActions actions = mockMvc.perform(post("/api/admin/login")
                .contentType(MediaType.APPLICATION_JSON)
                .cookie(authCookie)
        );

        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(testLoginUser.getId()))
                .andExpect(jsonPath("$.adminId").value(testLoginUser.getAdminId()));
    }

}