package agora.webproject.controller;

import agora.webproject.dto.UserDTO;
import agora.webproject.service.UserRegisterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class UserRegisterController {

    private final UserRegisterService userRegisterService;

    // 회원가입 처리
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDTO userDTO) {
        try {
            // 이메일 중복 검사
            if (userRegisterService.isEmailExist(userDTO.getEmail())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(
                        Map.of("error", "이미 존재하는 이메일입니다.")
                );
            }

            // 사용자 이름 중복 검사
            if (userRegisterService.isUsernameExist(userDTO.getUsername())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(
                        Map.of("error", "이미 존재하는 사용자 이름입니다.")
                );
            }

            // 비밀번호 확인
            if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
                return ResponseEntity.badRequest().body(
                        Map.of("error", "비밀번호와 비밀번호 확인이 일치하지 않습니다.")
                );
            }

            userRegisterService.saveDTO(userDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    Map.of("message", "회원가입이 성공적으로 완료되었습니다.")
            );
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(
                    Map.of("error", e.getMessage())
            );
        } catch (Exception e) {
            log.error("회원가입 실패", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    Map.of("error", "회원가입에 실패했습니다.")
            );
        }
    }
}
