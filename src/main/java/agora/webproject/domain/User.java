package agora.webproject.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor //생성자 자동 생성(Lombok)
@NoArgsConstructor //매개변수 없는 기본 생성자 자동 생성(Lombok)
@Builder //Builder 패턴으로 객체 생성
public class User {
    @Id
    //email은 중복 금지
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;
}
