package agora.webproject.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class LoginController {

    @GetMapping("/login")
    public ResponseEntity<String> showLoginPage() {
        return ResponseEntity.ok("로그인 페이지에 접근했습니다."); // 로그인 페이지 접근 메시지
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
        // 여기서 이메일과 비밀번호를 검증하는 로직을 추가하세요.

        boolean loginSuccess = true; // 로그인 성공 여부 (예시)

        if (loginSuccess) {
            return ResponseEntity.ok("{\"message\": \"로그인 성공\", \"redirectUrl\": \"/home\"}");
        } else {
            return ResponseEntity.status(401).body("{\"message\": \"로그인 실패\"}");
        }
    }
}
