package jwlee.swm_refactoring.infrastructor.admin.manager.controller;

import jakarta.validation.Valid;
import jwlee.swm_refactoring.infrastructor.admin.manager.dto.AdminReq;
import jwlee.swm_refactoring.domain.admin.manager.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
        adminService.sellerCreate(adminReq.toAdmin());
    }
}
