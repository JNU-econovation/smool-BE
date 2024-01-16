package econovation.moodtracker.service;

import econovation.moodtracker.domain.Emotion;
import econovation.moodtracker.domain.dto.Request.DiaryCreateRequestDTO;
import econovation.moodtracker.domain.dto.Request.DiaryUpdateRequestDTO;
import econovation.moodtracker.domain.dto.Request.UserCreateRequestDTO;
import econovation.moodtracker.repository.EmotionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class EmotionServiceTest {
    @Autowired EmotionService emotionService;
    @Autowired EmotionRepository emotionRepository;
    @Autowired UserService userService;

    @Test
    //@Rollback(value = false)
    public void 감정저장(){
        //given
        DiaryCreateRequestDTO diaryCreateRequestDTO = createEmotion();
        //when
        emotionService.join(diaryCreateRequestDTO);
        //then
        assertEquals(emotionRepository.findAll().size(), 1);
    }
    @Test
    //@Rollback(value = false)
    public void 감정수정(){
        //given
        Long savedId = createUser();
        DiaryCreateRequestDTO diaryCreateRequestDTO = createDiary(1,1,1,1,1,savedId,"abcd");
        Emotion getEmotion = emotionService.join(diaryCreateRequestDTO);
        //when
        DiaryUpdateRequestDTO diaryUpdateRequestDTO = updateDiary(getEmotion.getId(), 4,4,4,4,4);
        Emotion emotion = emotionService.updateEmotion(diaryUpdateRequestDTO);
        //then
        assertEquals(emotionService.findEmotion(emotion.getId()).getAnxiety(), 4);
    }

    @Test
    //@Rollback(value = false)
    public void 감정삭제(){
        //given
        DiaryCreateRequestDTO diaryCreateRequestDTO = createEmotion();
        Emotion emotion = emotionService.join(diaryCreateRequestDTO);
        Long emotionId = emotion.getId();
        //when
        emotionService.deleteEmotion(emotionId);
        //then
        assertEquals(emotionRepository.findAll().size(), 0);
    }

    private DiaryCreateRequestDTO createEmotion() {
        return DiaryCreateRequestDTO
                .builder()
                .happiness(1)
                .stress(1)
                .anxiety(1)
                .sleep(1)
                .gloom(1)
                .build();
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
                .userPk(savedId)
                .build();
    }

    private DiaryUpdateRequestDTO updateDiary(Long emotionPK, Integer happiness, Integer gloom,Integer anxiety,Integer stress,Integer sleep) {
        return DiaryUpdateRequestDTO
                .builder()

                .emotionPk(emotionPK)
                .happiness(happiness)
                .gloom(gloom)
                .anxiety(anxiety)
                .stress(stress)
                .sleep(sleep)
                .build();
    }
}