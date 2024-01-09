package econovation.moodtracker.service;

import econovation.moodtracker.domain.Diary;
import econovation.moodtracker.domain.dto.Request.DiaryCreateRequestDTO;
import econovation.moodtracker.domain.dto.Request.DiaryUpdateRequestDTO;
import econovation.moodtracker.domain.dto.Request.UserCreateRequestDTO;
import econovation.moodtracker.repository.DiaryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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
    //@Rollback(value = false)
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
                .localDate(LocalDate.now())

                .happiness(1)
                .stress(1)
                .anxiety(1)
                .sleep(1)
                .gloom(1)

                .content("dafd")
                .userPK(savedId)
                .build();

        DiaryCreateRequestDTO diaryCreateRequestDTO1 = DiaryCreateRequestDTO
                .builder()
                .localDate(LocalDate.now())

                .happiness(2)
                .stress(2)
                .anxiety(2)
                .sleep(2)
                .gloom(2)

                .content("bbbbb")
                .userPK(savedId)
                .build();
        //when
        diaryService.join(diaryCreateRequestDTO);
        diaryService.join(diaryCreateRequestDTO1);

        //then
        List<Diary> allDiaries = diaryService.getAllDiaries(LocalDate.now(), savedId);
        assertEquals(allDiaries.get(0).getEmotion(), allDiaries.get(1).getEmotion());
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
                .localDate(LocalDate.now())

                .happiness(1)
                .stress(1)
                .anxiety(1)
                .sleep(1)
                .gloom(1)

                .content("dafd")
                .userPK(savedId)
                .build();

        //when
        diaryService.join(diaryCreateRequestDTO);
        //then
        LocalDateTime startTime = LocalDateTime.now().withHour(0).withMinute(0);
        startTime = LocalDateTime.of(2024,1,1,0,0,0);
        LocalDateTime endTime = LocalDateTime.now().withHour(11).withMinute(59).withSecond(59);
        endTime = LocalDateTime.of(2024,12,1,11,59,59);
        List<Diary> allByTimeBetweenAndUserId = diaryRepository.findAllByTimeBetweenAndUserId(startTime, endTime, savedId);
        assertEquals(allByTimeBetweenAndUserId.size(), 1);
    }
    @Test
    @Rollback(value = false)
    public void 일기수정(){
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
                .localDate(LocalDate.now())

                .happiness(1)
                .stress(1)
                .anxiety(1)
                .sleep(1)
                .gloom(1)

                .content("dafd")
                .userPK(savedId)
                .build();
        diaryService.join(diaryCreateRequestDTO);
        //when
        DiaryUpdateRequestDTO diaryUpdateRequestDTO = DiaryUpdateRequestDTO
                .builder()
                .diaryPK(1L)
                .content("updated updated")

                .build();
        diaryService.updateDiary(diaryUpdateRequestDTO.getDiaryPK(), diaryUpdateRequestDTO.getContent());
        //then
        assertEquals(diaryService.getDiary(1L).getContent(), "updated updated");
    }
}