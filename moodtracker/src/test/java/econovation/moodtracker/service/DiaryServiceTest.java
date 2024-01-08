package econovation.moodtracker.service;

import econovation.moodtracker.domain.Diary;
import econovation.moodtracker.domain.dto.Request.DiaryCreateRequestDTO;
import econovation.moodtracker.domain.dto.Request.UserCreateRequestDTO;
import econovation.moodtracker.repository.DiaryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class DiaryServiceTest {
    @Autowired UserService userService;
    @Autowired EmotionService emotionService;
    @Autowired DiaryService diaryService;
    @Autowired DiaryRepository diaryRepository;
    @Test
    @Rollback(value = false)
    public void 일기저장(){
        //given
        /*
        회원 가입
        */
        String userId = "abc123";
        String password = "123abc";

        UserCreateRequestDTO userCreateRequestDTO = UserCreateRequestDTO.builder()
                .userId(userId)
                .password(password)
                .build();

        Long savedId = userService.join(userCreateRequestDTO);
        /*
        일기 작성
        */
        DiaryCreateRequestDTO diaryCreateRequestDTO = DiaryCreateRequestDTO
                .builder()
                .happiness(1)
                .stress(1)
                .anxiety(1)
                .sleepTime(1)
                .gloom(1)

                .content("dafd")
                .userPK(savedId)
                .build();

        DiaryCreateRequestDTO diaryCreateRequestDTO1 = DiaryCreateRequestDTO
                .builder()
                .happiness(1)
                .stress(1)
                .anxiety(1)
                .sleepTime(1)
                .gloom(1)

                .content("dafd")
                .userPK(savedId)
                .build();
        //when
        diaryService.join(diaryCreateRequestDTO);
        diaryService.join(diaryCreateRequestDTO1);

        //then
    }
    @Test
    //@Rollback(value = false)
    public void 일기찾기(){
        //given
        String userId = "abc123";
        String password = "123abc";

        UserCreateRequestDTO userCreateRequestDTO = UserCreateRequestDTO.builder()
                .userId(userId)
                .password(password)
                .build();

        Long savedId = userService.join(userCreateRequestDTO);
        /*
        일기 작성
        */
        DiaryCreateRequestDTO diaryCreateRequestDTO = DiaryCreateRequestDTO
                .builder()
                .happiness(1)
                .stress(1)
                .anxiety(1)
                .sleepTime(1)
                .gloom(1)

                .content("dafd")
                .userPK(savedId)
                .build();

        //when
        diaryService.join(diaryCreateRequestDTO);
        //then
        LocalDateTime startTime = LocalDateTime.now().withHour(0).withMinute(0);
        LocalDateTime endTime = LocalDateTime.now().withHour(11).withMinute(59).withSecond(59);
        List<Diary> allByTimeBetweenAndUserId = diaryRepository.findAllByTimeBetweenAndUserId(startTime, endTime, savedId);
    }
}