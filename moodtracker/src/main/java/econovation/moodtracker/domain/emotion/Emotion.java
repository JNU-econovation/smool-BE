package econovation.moodtracker.domain.emotion;

import econovation.moodtracker.domain.calendarLog.CalendarLog;
import econovation.moodtracker.domain.diary.Diary;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.*;

@Entity
@Getter
public class Emotion {
    @Id
    @GeneratedValue
    @Column(name = "emotion_id")
    private Long id;
    @OneToOne(mappedBy = "emotion", fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "calendarlog_id")
    private CalendarLog calendarLog;

    @OneToMany(mappedBy = "emotion", cascade = ALL)
    private List<Diary> diaries = new ArrayList<>();
    private Integer happiness;
    private Integer sadness;
    private Integer anxiety;
    private Integer stress;
    private Integer sleepTime;




}
