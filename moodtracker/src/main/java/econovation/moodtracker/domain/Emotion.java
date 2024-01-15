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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emotion_id")
    private Long id;
    @Builder.Default
    @OneToMany(mappedBy = "emotion", fetch = LAZY)
    private List<Diary> diaries = new ArrayList<>();
    private Integer happiness;
    private Integer gloom;
    private Integer anxiety;
    private Integer stress;
    private Integer sleep;

    public void update(Integer happiness, Integer gloom, Integer anxiety, Integer stress, Integer sleep){
        this.happiness = happiness;
        this.gloom = gloom;
        this.anxiety = anxiety;
        this.stress = stress;
        this.sleep = sleep;
    }
}
