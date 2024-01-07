package econovation.moodtracker.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    public void update(Integer happiness, Integer sadness, Integer anxiety, Integer stress, Integer sleepTime){
        this.happiness = happiness;
        this.sadness = sadness;
        this.anxiety = anxiety;
        this.stress = stress;
        this.sleepTime = sleepTime;
    }
}
