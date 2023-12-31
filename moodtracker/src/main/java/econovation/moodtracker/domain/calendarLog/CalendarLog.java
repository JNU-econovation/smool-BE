package econovation.moodtracker.domain.calendarLog;

import econovation.moodtracker.domain.diary.Diary;
import econovation.moodtracker.domain.emotion.Emotion;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.FetchType.*;

@Entity
@Getter
public class CalendarLog {
    @Id @GeneratedValue
    @Column(name = "calendarlog_id")
    private Long id;
    private LocalDate date;
    @OneToMany(mappedBy = "calendarLog", cascade = ALL)
    private List<Diary> diaries = new ArrayList<>();

    @OneToOne(fetch = LAZY)
    private Emotion emotion;
}
