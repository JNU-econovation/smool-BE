package econovation.moodtracker.domain.diary;

import econovation.moodtracker.domain.emotion.Emotion;
import econovation.moodtracker.domain.user.User;
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

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "emotion_id")
    private Emotion emotion;

    private LocalDateTime time;

    private String content;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "users_id")
    private User user;
    @Builder
    public Diary(Long id, Emotion emotion, LocalDateTime time, String content, User user) {
        this.id = id;
        this.emotion = emotion;
        this.time = time;
        this.content = content;

        this.user = user;
    }
}
