package agora.webproject.service;

import agora.webproject.domain.User;
import agora.webproject.dto.UserDTO;
import agora.webproject.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserRegisterServiceImpl implements UserRegisterService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User saveEntity(User user) {
        return userRepository.save(user);
    }

    @Override
    public User saveDTO(UserDTO userdto) {
        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(userdto.getPassword());

        // User 엔티티 생성
        User user = User.builder()
                .email(userdto.getEmail())
                .password(encodedPassword) // 암호화된 비밀번호 사용
                .username(userdto.getUsername())
                .build();

        // 저장 및 반환
        return saveEntity(user);
    }

    @Override
    public boolean isEmailExist(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean isUsernameExist(String username) {
        return userRepository.existsByUsername(username);
    }
}
