package econovation.moodtracker.exception;

public class UserPasswordNotCorrect extends IllegalArgumentException{
    public UserPasswordNotCorrect(){
        super("아이디 또는 비밀번호가 옳지 않습니다.");
    }
}
