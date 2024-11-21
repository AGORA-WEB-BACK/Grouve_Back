package agora.webproject.controller;

import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @GetMapping("api/user/login")
    public String showLoginPage() {
        return "login"; //로그인 페이지의 템플릿 이름
    }
    @PostMapping("api/user/login")
    public String login(@RequestParam String email, @RequestParam String passwword, Model model) {
        return "/home"; // 로그인 성공 후 갈 URL
    }
}
