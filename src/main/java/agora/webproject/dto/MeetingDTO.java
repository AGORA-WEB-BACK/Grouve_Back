package agora.webproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MeetingDTO {
    private Long id; // 추가: 모임 ID
    private String leader;
    private String meetingName;
    private String category;
    private String description;
    private String participants;
    private String creationDate;
    private String roles;
    private String status; // 추가: 모임 상태 (대기중, 수락 등)
}
