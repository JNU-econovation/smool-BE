package econovation.moodtracker.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class SameUserIdException extends DataIntegrityViolationException {
    public SameUserIdException(){
        super("이미 존재하는 아이디입니다.");

    }
}
