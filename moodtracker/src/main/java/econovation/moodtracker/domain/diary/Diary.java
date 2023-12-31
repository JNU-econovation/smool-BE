package econovation.moodtracker.domain.diary;

import econovation.moodtracker.domain.calendarLog.CalendarLog;
import econovation.moodtracker.domain.emotion.Emotion;
import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
public class Diary {
    @Id
    @GeneratedValue
    @Column(name = "diary_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "calendarLog_id")
    private CalendarLog calendarLog;
    @ManyToOne
    @JoinColumn(name = "emotion_id")
    private Emotion emotion;

    private LocalDateTime time;

    private String content;
}
