package econovation.moodtracker.domain.emotion;

import econovation.moodtracker.domain.diary.Diary;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Emotion {
    @Id
    @GeneratedValue
    @Column(name = "emotion_id")
    private Long id;

    @OneToOne(mappedBy = "emotion", fetch = LAZY)
    private Diary diary;
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
    public Emotion(Long id, Diary diary, Integer happiness, Integer sadness, Integer anxiety, Integer stress, Integer sleepTime) {
        this.id = id;
        this.diary = diary;
        this.happiness = happiness;
        this.sadness = sadness;
        this.anxiety = anxiety;
        this.stress = stress;
        this.sleepTime = sleepTime;

        diary.setEmotion(this);
    }
}
