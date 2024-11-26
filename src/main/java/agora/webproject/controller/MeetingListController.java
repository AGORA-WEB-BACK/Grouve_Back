package agora.webproject.controller;

import agora.webproject.dto.MeetingDTO;
import agora.webproject.service.MeetingListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/meetings")
@RequiredArgsConstructor
@Slf4j
public class MeetingListController {

    private final MeetingListService meetingListService;

    @GetMapping("/list")
    public ResponseEntity<?> getAllMeetings() {
        try {
            List<MeetingDTO> meetings = meetingListService.getAllMeetings();
            return ResponseEntity.ok(meetings);
        } catch (Exception e) {
            log.error("모임 목록 조회 실패", e);
            return ResponseEntity.status(500).body("{\"message\": \"모임 목록 조회 중 오류 발생\"}");
        }
    }
}
