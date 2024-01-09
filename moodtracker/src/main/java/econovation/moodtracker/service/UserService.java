package econovation.moodtracker.service;

import econovation.moodtracker.domain.dto.Request.UserCreateRequestDTO;
import econovation.moodtracker.domain.User;
import econovation.moodtracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    //회원 가입
    public Long join(UserCreateRequestDTO userCreateRequestDTO){
        validateDuplicateUser(userCreateRequestDTO);
        User user = User.builder()
                .userId(userCreateRequestDTO.getUserId())
                .password(userCreateRequestDTO.getPassword())
                .build();
        userRepository.save(user);
        return user.getId();
    }
    //중복 회원
    private void validateDuplicateUser(UserCreateRequestDTO userCreateRequestDTO){
        Optional<User> findUser = userRepository.findUserByUserId(userCreateRequestDTO.getUserId());
        if(findUser.isPresent()){
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        }
    }

    public User findUser(Long userPK){
        return userRepository.findById(userPK)
                .orElseThrow(() -> new NullPointerException("사람이 없어용"));
    }
}
