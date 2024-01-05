package econovation.moodtracker.service;

import econovation.moodtracker.domain.user.User;
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
        User user = User.builder()
                .userId(userId)
                .build();
        //when
        Long savedId = userService.join(user);
        //then
        // pk값으로 조회
        Optional<User> getUserById = userRepository.findById(savedId);
        assertNotNull(getUserById);
        assertEquals(user, userRepository.findById(savedId).get());

        // 아이디로 조회
        Optional<User> getUserIdByUserId = userRepository.findUserByUserId(userId);
        assertNotNull(getUserIdByUserId);
        assertEquals(user, getUserIdByUserId.get());
    }

    @Test
    public void 중복_회원_예외() throws Exception{
        //given
        User user = User.builder()
                .userId("abc123")
                .build();
        User user1 = User.builder()
                .userId("abc123")
                .build();
        //when
        userService.join(user);
        //then
        Throwable exception = assertThrows(RuntimeException.class, () -> {
            userService.join(user1);
        });
        assertEquals("이미 존재하는 아이디입니다.", exception.getMessage());
    }

}