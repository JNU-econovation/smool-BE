package econovation.moodtracker.exception;

import jakarta.persistence.EntityNotFoundException;

public class UserIdNotFountException extends EntityNotFoundException {
    public UserIdNotFountException(){
        super("아이디 또는 비밀번호가 옳지 않습니다.");
    }
}
