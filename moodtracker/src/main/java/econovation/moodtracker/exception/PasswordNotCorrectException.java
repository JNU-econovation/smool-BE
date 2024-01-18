package econovation.moodtracker.exception;

public class PasswordNotCorrectException extends IllegalArgumentException{
    public PasswordNotCorrectException(){
        super("비밀번호를 다시 확인해주세요.");
    }
}
