package econovation.moodtracker.repository;

import econovation.moodtracker.domain.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {
    List<Diary> findAllByTimeBetweenAndUserId(LocalDateTime startTime, LocalDateTime endTime, Long userId);
    List<Diary> findAllByEmotionId(Long emotionId);
}
