package econovation.moodtracker.exception;

public class UserNotFoundException extends IllegalArgumentException{
    public UserNotFoundException(){
        super("회원이 존재하지 않습니다.");
    }
}
