package agora.webproject.service;

import agora.webproject.domain.User;
import agora.webproject.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // 이메일로 사용자 조회
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + email));

        // UserDetails 객체 생성 및 반환
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail()) // 이메일을 사용자 이름으로 설정
                .password(user.getPassword()) // 저장된 비밀번호
                .authorities("USER") // 기본 권한 설정 (필요 시 다르게 설정 가능)
                .build();
    }
}
