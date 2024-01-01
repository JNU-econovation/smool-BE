package econovation.moodtracker.repository;

import econovation.moodtracker.domain.calendarLog.CalendarLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarLogRepository extends JpaRepository<CalendarLog, Long> {
}
