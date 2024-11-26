package agora.webproject.service;

import agora.webproject.dto.MeetingDTO;
import agora.webproject.domain.Meeting;
import agora.webproject.repository.MeetingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MeetingListService {

    private final MeetingRepository meetingRepository;

    public List<MeetingDTO> getAllMeetings() {
        List<Meeting> meetings = meetingRepository.findAll(); //db에 모든 모임 조회
        return meetings.stream().map(meeting -> MeetingDTO.builder()
                .id(meeting.getId())
                .leader(meeting.getLeader())
                .meetingName(meeting.getMeetingName())
                .category(meeting.getCategory())
                .description(meeting.getDescription())
                .participants(meeting.getParticipants())
                .creationDate(meeting.getCreationDate())
                .roles(meeting.getRoles())
                .status(meeting.getStatus()) // 모임 상태
                .build()).collect(Collectors.toList());
    }
}
