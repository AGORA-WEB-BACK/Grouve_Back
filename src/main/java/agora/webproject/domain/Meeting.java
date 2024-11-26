package agora.webproject.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String leader;

    @Column(nullable = false)
    private String meetingName;

    @Column(nullable = false)
    private String category;

    @Lob
    private String image;

    @Column(nullable = false)
    private String description;

    private String participants;
    private String creationDate;
    private String roles;

    @Column(nullable = false)
    private String status; // 추가: 모임 상태 (대기중, 수락 등)
}
