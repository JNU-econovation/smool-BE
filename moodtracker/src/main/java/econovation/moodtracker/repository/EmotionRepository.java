package econovation.moodtracker.repository;

import econovation.moodtracker.domain.calendarLog.CalendarLog;
import econovation.moodtracker.domain.calendarLog.DTO.CalendarLogDTO;
import econovation.moodtracker.domain.emotion.Emotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmotionRepository extends JpaRepository<Emotion, Long> {
    Optional<Emotion> findEmotionByCalendarLog(CalendarLog calendarLog);
}
