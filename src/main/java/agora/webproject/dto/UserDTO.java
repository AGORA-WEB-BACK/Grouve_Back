package agora.webproject.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @NotBlank(message = "이름을 입력하세요")
    private String username;

    @NotBlank(message = "비밀번호를 입력하세요")
    private String password;

    @NotBlank(message = "이메일을 입력하세요")
    private String email;

    @NotBlank(message = "비밀번호 다시 입력하세요")
    private String confirmPassword;
}
