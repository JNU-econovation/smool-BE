package econovation.moodtracker.service;

import econovation.moodtracker.domain.calendarLog.CalendarLog;
import econovation.moodtracker.repository.CalendarLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CalendarLogService {

    private final CalendarLogRepository calendarLogRepository;

    public Long join(CalendarLog calendarLog){
        calendarLogRepository.save(calendarLog);
        return calendarLog.getId();
    }

}