package econovation.moodtracker.domain.user;

import econovation.moodtracker.domain.diary.Diary;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;
    private String userId;
    private String password;
    @Builder
    public User(Long id, String userId, String password) {
        this.id = id;
        this.userId = userId;
        this.password = password;
    }
}
