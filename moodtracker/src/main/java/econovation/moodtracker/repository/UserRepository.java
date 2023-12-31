package econovation.moodtracker.repository;

import econovation.moodtracker.domain.user.User;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    public void save(User user){
        em.persist(user);
    }

    public List<User> findByUserId(String userId) {
        return em.createQuery("select m from User m where m.userId = :userId",
                        User.class)
                .setParameter("userId", userId)
                .getResultList();
    }
}
