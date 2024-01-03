package econovation.moodtracker.domain.user;

import econovation.moodtracker.domain.diary.Diary;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class User {
    @Id @GeneratedValue
    @Column(name = "users_id")
    private Long id;
    private String userId;
    private String password;
    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<Diary> diaries = new ArrayList<>();
}
