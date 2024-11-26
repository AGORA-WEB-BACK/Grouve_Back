package agora.webproject.controller;

import agora.webproject.dto.MeetingDTO;
import agora.webproject.service.MeetingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/meetings")
@RequiredArgsConstructor
@Slf4j
public class MeetingController {

    private final MeetingService meetingService;

    // 모임 등록 처리
    @PostMapping("/create")
    public ResponseEntity<?> registerMeeting(
            @RequestPart("meeting") @RequestBody MeetingDTO meetingDTO,
            @RequestPart(value = "image", required = false) MultipartFile image) {

        try {
            meetingService.saveMeeting(meetingDTO, image);
            return ResponseEntity.status(HttpStatus.CREATED).body("모임이 성공적으로 등록되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            log.error("모임 등록 실패", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("모임 등록에 실패했습니다.");
        }
    }
}
