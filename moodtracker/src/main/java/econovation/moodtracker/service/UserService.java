package econovation.moodtracker.service;

import econovation.moodtracker.domain.user.User;
import econovation.moodtracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    //회원 가입
    public Long join(User user){
        validateDuplicateUser(user);
        userRepository.save(user);
        return user.getId();
    }

    private void validateDuplicateUser(User user) {
        List<User> findUsers = userRepository.findByUserId(user.getUserId());
        if (!findUsers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        }
    }
}
