package agora.webproject.service;

import agora.webproject.dto.MeetingDTO;
import agora.webproject.domain.Meeting;
import agora.webproject.repository.MeetingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class MeetingService {

    private final MeetingRepository meetingRepository;

    public void saveMeeting(MeetingDTO meetingDTO, MultipartFile image) throws IOException {
        String encodedImage = null;

        // 이미지 처리
        if (image != null && !image.isEmpty()) {
            encodedImage = Base64.getEncoder().encodeToString(image.getBytes());
        }

        Meeting meeting = Meeting.builder()
                .leader(meetingDTO.getLeader())
                .meetingName(meetingDTO.getMeetingName())
                .category(meetingDTO.getCategory())
                .description(meetingDTO.getDescription())
                .participants(meetingDTO.getParticipants())
                .creationDate(meetingDTO.getCreationDate())
                .roles(meetingDTO.getRoles())
                .status(meetingDTO.getStatus())
                .image(encodedImage)
                .build();

        meetingRepository.save(meeting);
    }
}
