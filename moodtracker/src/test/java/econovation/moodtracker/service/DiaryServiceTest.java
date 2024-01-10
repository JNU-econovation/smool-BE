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
        Long savedId = createUser();
        /*
        일기 작성
        */
        DiaryCreateRequestDTO diaryCreateRequestDTO = createDiary(1,1,1,1,1,savedId, "bbbb");
        DiaryCreateRequestDTO diaryCreateRequestDTO1 = createDiary(2,2,2,2,2,savedId,"cccc");
        //when
        Long savedId1 = diaryService.join(diaryCreateRequestDTO);
        Long savedId2 = diaryService.join(diaryCreateRequestDTO1);

        //then
        assertEquals(diaryService.findDiary(savedId1).get().getEmotion(), diaryService.findDiary(savedId2).get().getEmotion());
    }
    @Test
    //@Rollback(value = false)
    public void 일기찾기(){
        //given
        Long savedId = createUser();
        /*
        일기 작성
        */
        DiaryCreateRequestDTO diaryCreateRequestDTO = createDiary(1,1,1,1,1,savedId, "bbbb");

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
    //@Rollback(value = false)
    public void 일기수정(){
        //given
        Long savedId = createUser();
        /*
        일기 작성
        */
        DiaryCreateRequestDTO diaryCreateRequestDTO = createDiary(1,1,1,1,1,savedId, "bbbb");
        diaryService.join(diaryCreateRequestDTO);
        //when
        DiaryUpdateRequestDTO diaryUpdateRequestDTO = DiaryUpdateRequestDTO
                .builder()
                .diaryPK(1L)
                .content("updated updated")

                .build();
        diaryService.updateDiary(diaryUpdateRequestDTO.getDiaryPK(), diaryUpdateRequestDTO.getContent());
        //then
        assertEquals(diaryService.findDiary(1L).get().getContent(), "updated updated");
    }
    @Test
    @Rollback(value = false)
    public void 일기삭제(){
        //given
        Long savedId =createUser();
        //when
        DiaryCreateRequestDTO diaryCreateRequestDTO = createDiary(1,1,1,1,1,savedId,"bbbb");
        diaryService.join(diaryCreateRequestDTO);
        //then
        diaryService.deleteDiary(1L);
    }

    private Long createUser() {
        String userId = "abc123";
        String password = "123abc";

        UserCreateRequestDTO userCreateRequestDTO = UserCreateRequestDTO.builder()
                .userId(userId)
                .password(password)
                .build();

        return userService.join(userCreateRequestDTO);
    }

    private DiaryCreateRequestDTO createDiary(Integer happiness, Integer stress, Integer anxiety, Integer sleep, Integer gloom, Long savedId, String content) {
        return DiaryCreateRequestDTO
                .builder()
                .localDate(LocalDate.now())

                .happiness(happiness)
                .stress(stress)
                .anxiety(anxiety)
                .sleep(sleep)
                .gloom(gloom)

                .content(content)
                .userPK(savedId)
                .build();
    }
}