package econovation.moodtracker.exception;

import jakarta.persistence.EntityNotFoundException;

public class DiaryNotFoundException extends EntityNotFoundException{
    public DiaryNotFoundException(){
        super("일기가 존재하지 않습니다.");
    }
}
