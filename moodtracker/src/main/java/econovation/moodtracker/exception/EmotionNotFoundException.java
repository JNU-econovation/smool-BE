package econovation.moodtracker.exception;

import jakarta.persistence.EntityNotFoundException;

public class EmotionNotFoundException extends EntityNotFoundException {
    public EmotionNotFoundException(){
        super("감정 조회 실패");
    }
}
