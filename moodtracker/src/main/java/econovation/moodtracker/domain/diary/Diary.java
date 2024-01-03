package econovation.moodtracker.domain.diary;

import econovation.moodtracker.domain.emotion.Emotion;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Diary {
    @Id
    @GeneratedValue
    @Column(name = "diary_id")
    private Long id;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "emotion_id")
    private Emotion emotion;

    private LocalDateTime time;

    private String content;
//
//    public void setCalendarLog(CalendarLog calendarLog){
//        this.calendarLog = calendarLog;
//        calendarLog.getDiaries().add(this);
//    }
//
//    public void setEmotion(Emotion emotion){
//        this.emotion = emotion;
//        emotion.getDiaries().add(this);
//    }
    @Builder
    public Diary(Long id, Emotion emotion, LocalDateTime time, String content) {
        this.id = id;
        this.emotion = emotion;
        this.time = time;
        this.content = content;



    }
}
