package econovation.moodtracker.exception;

import jakarta.persistence.EntityNotFoundException;

public class StatisticsEmotionNotFoundException extends EntityNotFoundException {
    public StatisticsEmotionNotFoundException(){
        super("통계 조회 실패");
    }
}
