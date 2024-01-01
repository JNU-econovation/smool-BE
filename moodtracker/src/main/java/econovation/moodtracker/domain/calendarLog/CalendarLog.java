package econovation.moodtracker.domain.calendarLog;

import econovation.moodtracker.domain.diary.Diary;
import econovation.moodtracker.domain.emotion.Emotion;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.FetchType.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CalendarLog {
    @Id @GeneratedValue
    @Column(name = "calendarlog_id")
    private Long id;
    private LocalDate date;
    @OneToMany(mappedBy = "calendarLog")
    private List<Diary> diaries = new ArrayList<>();

    @OneToOne(fetch = LAZY)
    private Emotion emotion;

    @Builder
    public CalendarLog(Long id, LocalDate date, List<Diary> diaries, Emotion emotion) {
        this.id = id;
        this.date = date;
        this.diaries = diaries;
        this.emotion = emotion;
    }
}
