package econovation.moodtracker.service;

import econovation.moodtracker.domain.dto.Request.UserCreateRequestDTO;
import econovation.moodtracker.domain.User;
import econovation.moodtracker.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class UserServiceTest {
    @Autowired UserService userService;
    @Autowired UserRepository userRepository;

    @Test
    @Rollback(false)
    public void 회원가입() throws Exception{
        //given
        String userId = "abc123";
        String password = "123abc";

        UserCreateRequestDTO userCreateRequestDTO = UserCreateRequestDTO.builder()
                .userId(userId)
                .password(password)
                .build();
        //when
        Long savedId = userService.join(userCreateRequestDTO);
        //then
        // pk값으로 조회
        Optional<User> getUserById = userRepository.findById(savedId);
        assertNotNull(getUserById);
        assertEquals(userRepository.findAll().size(), 1);
        // 아이디로 조회
        Optional<User> getUserByUserId = userRepository.findUserByUserId(userId);
        assertNotNull(getUserByUserId);
        assertEquals(getUserByUserId.get().getUserId(), userId);
    }

    @Test
    public void 중복_회원_예외() throws Exception{
        //given
        String userId = "abc123";
        String password = "123abc";

        UserCreateRequestDTO userCreateRequestDTO = UserCreateRequestDTO.builder()
                .userId(userId)
                .password(password)
                .build();

        UserCreateRequestDTO userCreateRequestDTO1 = UserCreateRequestDTO.builder()
                .userId(userId)
                .password(password)
                .build();

        //when
        userService.join(userCreateRequestDTO);
        //then
        Throwable exception = assertThrows(RuntimeException.class, () -> {
            userService.join(userCreateRequestDTO1);
        });
        assertEquals("이미 존재하는 아이디입니다.", exception.getMessage());
    }

}