package econovation.moodtracker.exception;

import jakarta.persistence.EntityNotFoundException;

public class UserNotFoundException extends EntityNotFoundException {
    public UserNotFoundException(){
        super("회원이 존재하지 않습니다.");
    }
}
