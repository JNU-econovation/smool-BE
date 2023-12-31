package econovation.moodtracker.domain.emotion;

import econovation.moodtracker.domain.calendarLog.CalendarLog;
import econovation.moodtracker.domain.diary.Diary;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Emotion {
    @Id
    @GeneratedValue
    @Column(name = "emotion_id")
    private Long id;
    @OneToOne(mappedBy = "emotion")
    @JoinColumn(name = "calendarlog_id")
    private CalendarLog calendarLog;

    @OneToMany(mappedBy = "emotion")
    private List<Diary> diaries = new ArrayList<>();
    private Integer happiness;
    private Integer sadness;
    private Integer anxiety;
    private Integer stress;
    private Integer sleepTime;




}
