package econovation.moodtracker.domain.diary;

import econovation.moodtracker.domain.calendarLog.CalendarLog;
import econovation.moodtracker.domain.emotion.Emotion;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
public class Diary {
    @Id
    @GeneratedValue
    @Column(name = "diary_id")
    private Long id;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "calendarLog_id")
    private CalendarLog calendarLog;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "emotion_id")
    private Emotion emotion;

    private LocalDateTime time;

    private String content;


}
