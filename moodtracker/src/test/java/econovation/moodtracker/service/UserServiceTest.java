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

//    @Test
//    public void 중복_회원_예외() throws Exception{
//        //given
//        User user1 = new User();
//        user1.setUserId("ps9319");
//
//        User user2 = new User();
//        user2.setUserId("ps9319");
//
//        //when
//        userService.join(user1);
//        try{
//            userService.join(user2);
//        }
//        catch (IllegalStateException e){
//            return;
//        }
//
//        //then
//        fail("예외가 발생해야 한다.");
//    }

}