package agora.webproject.repository;

import agora.webproject.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String>{
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    Optional<User> findByEmail(String email); //이메일로 사용자 찾기
}