package agora.webproject.repository;

import agora.webproject.domain.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {
}