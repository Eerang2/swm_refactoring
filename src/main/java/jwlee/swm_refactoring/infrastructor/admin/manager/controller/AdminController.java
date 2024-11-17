package jwlee.swm_refactoring.infrastructor.admin.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("admin/login")
    public String login() {
        return "web_manager/webcenter_login";
    }

    @GetMapping("admin/seller/create")
    public String create() {
        return "web_manager/webcenter_seller";
    }
}
