package agora.webproject.controller;

import agora.webproject.dto.UserDTO;
import agora.webproject.service.UserRegisterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Slf4j
public class UserRegisterController {

    private final UserRegisterService userRegisterService;

    // 회원가입 페이지 반환
    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "login";
    }

    // 회원가입 처리
    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("userDTO") UserDTO userDTO,
                               BindingResult bindingResult,
                               Model model) {

        // 유효성 검사 오류 처리
        if (bindingResult.hasErrors()) {
            return "Register";
        }

        // 이메일 중복 검사
        if (userRegisterService.isEmailExist(userDTO.getEmail())) {
            model.addAttribute("message", "이미 존재하는 이메일입니다.");
            return "Register";
        }

        // 사용자 이름 중복 검사
        if (userRegisterService.isUsernameExist(userDTO.getUsername())) {
            model.addAttribute("message", "이미 존재하는 사용자 이름입니다.");
            return "Register";
        }

        // 비밀번호 길이 검사
        if (userDTO.getPassword().length() < 4 || userDTO.getPassword().length() > 13) {
            model.addAttribute("message", "비밀번호는 4~13 글자 사이여야 합니다.");
            return "Register";
        }

        if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
            model.addAttribute("message", "비밀번호와 비밀번호 확인이 일치하지 않습니다.");
            return "Register";
        }

        // 사용자 저장
        userRegisterService.saveDTO(userDTO);
        model.addAttribute("message", "회원가입이 성공적으로 완료되었습니다.");
        return "Register";

    }
}
