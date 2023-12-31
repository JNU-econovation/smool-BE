package econovation.moodtracker.domain.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class User {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String userid;
    private String password;
}
