package econovation.moodtracker.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Diary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diary_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "emotion_id")
    private Emotion emotion;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "users_id")
    private User user;

    @Column(length = 10000)
    private String content;

    private LocalDateTime time;
    @Builder
    public Diary(Long id, Emotion emotion, LocalDateTime time, String content, User user) {
        this.id = id;
        this.time = time;
        this.content = content;

        this.user = user;
        user.getDiaries().add(this);

        this.emotion = emotion;
        emotion.getDiaries().add(this);
    }

    public void update(String content){
        this.content = content;
    }
}
