package econovation.moodtracker.domain.calendarLog;

import econovation.moodtracker.domain.diary.Diary;
import econovation.moodtracker.domain.emotion.Emotion;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CalendarLog {
    @Id @GeneratedValue
    @Column(name = "calendarlog_id")
    private Long id;
    private LocalDate date;
    @OneToMany(mappedBy = "calendarLog")
    private List<Diary> diaries = new ArrayList<>();

    @OneToOne
    private Emotion emotion;
}
