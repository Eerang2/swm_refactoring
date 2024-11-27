package jwlee.swm_refactoring.infrastructor.admin.manager.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jwlee.swm_refactoring.domain.admin.manager.model.Admin;
import jwlee.swm_refactoring.infrastructor.admin.manager.dto.AdminReq;
import jwlee.swm_refactoring.domain.admin.manager.service.AdminService;
import jwlee.swm_refactoring.infrastructor.admin.manager.dto.LoginRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class AdminRestController {

    private final AdminService adminService;

    @PostMapping("/admin/sign")
    public void signIn() {

    }

    @PostMapping("/seller/create")
    public void sellerCreate(@Valid @RequestBody AdminReq adminReq) {
        Admin admin = adminService.sellerCreate(adminReq.toAdmin());
    }

    @PostMapping("/admin/login")
    public LoginRes login(@RequestBody final Admin admin, HttpServletResponse response) {

        LoginRes loginRes = adminService.authenticateAndGenerateToken(admin);
        log.info("admin : {}", admin.getAdminId());

        Cookie jwtCookie = new Cookie("JWT_TOKEN", loginRes.getToken());
        jwtCookie.setHttpOnly(true); // 클라이언트 스크립트 접근 방지
        jwtCookie.setPath("/");
        jwtCookie.setMaxAge(60 * 60 * 24); // 1일
        response.addCookie(jwtCookie);

        log.debug("jwtCookie : {}", jwtCookie);
        return loginRes;
    }
}
