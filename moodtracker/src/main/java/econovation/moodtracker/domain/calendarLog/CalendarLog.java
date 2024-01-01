package econovation.moodtracker.domain.calendarLog;

import econovation.moodtracker.domain.diary.Diary;
import econovation.moodtracker.domain.emotion.Emotion;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}
