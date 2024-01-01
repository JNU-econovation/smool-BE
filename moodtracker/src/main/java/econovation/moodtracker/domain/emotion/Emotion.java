package econovation.moodtracker.domain.emotion;

import econovation.moodtracker.domain.calendarLog.CalendarLog;
import econovation.moodtracker.domain.diary.Diary;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Emotion {
    @Id
    @GeneratedValue
    @Column(name = "emotion_id")
    private Long id;
    @OneToOne(mappedBy = "emotion", fetch = LAZY)
    @JoinColumn(name = "calendarlog_id")
    private CalendarLog calendarLog;

    @OneToMany(mappedBy = "emotion")
    private List<Diary> diaries = new ArrayList<>();
    private Integer happiness;
    private Integer sadness;
    private Integer anxiety;
    private Integer stress;
    private Integer sleepTime;

//    public void setCalendarLog(CalendarLog calendarLog){
//        this.calendarLog = calendarLog;
//        calendarLog.setEmotion(this);
//    }
    @Builder
    public Emotion(Long id, CalendarLog calendarLog, List<Diary> diaries, Integer happiness, Integer sadness, Integer anxiety, Integer stress, Integer sleepTime) {
        this.id = id;
        this.diaries = diaries;
        this.happiness = happiness;
        this.sadness = sadness;
        this.anxiety = anxiety;
        this.stress = stress;
        this.sleepTime = sleepTime;

        this.calendarLog = calendarLog;
        calendarLog.setEmotion(this);
    }
}
