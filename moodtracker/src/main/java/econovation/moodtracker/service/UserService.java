package econovation.moodtracker.service;

import econovation.moodtracker.domain.dto.Request.UserCreateRequestDTO;
import econovation.moodtracker.domain.User;
import econovation.moodtracker.exception.*;
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
        validatePasswordCorrect(userCreateRequestDTO);
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
            throw new SameUserIdException();
        }
    }

    private  void validatePasswordCorrect(UserCreateRequestDTO userCreateRequestDTO){
        if(!userCreateRequestDTO.getPassword().equals(userCreateRequestDTO.getCheckPassword())){
            throw new PasswordNotCorrectException();
        }
    }

    public User findUser(Long userPK){
        return userRepository.findById(userPK)
                .orElseThrow(UserNotFoundException::new);
    }

    public Long login(String userId, String password){
        User user = userRepository.findUserByUserId(userId)
                .orElseThrow(UserIdNotFountException::new);
        if (user.getPassword().equals(password)){
            return user.getId();
        }
        else {
            throw new UserPasswordNotCorrect();
        }
    }
}
