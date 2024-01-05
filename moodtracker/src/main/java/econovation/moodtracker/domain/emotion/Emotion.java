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
@AllArgsConstructor
@Builder
public class Emotion {
    @Id
    @GeneratedValue
    @Column(name = "emotion_id")
    private Long id;
    @Builder.Default
    @OneToMany(mappedBy = "emotion", fetch = LAZY)
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

}
