package econovation.moodtracker.repository;

import econovation.moodtracker.domain.calendarLog.CalendarLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CalendarLogRepository extends JpaRepository<CalendarLog, Long> {
    List<CalendarLog> findCalendarLogsByDate(LocalDate localDate);
}
