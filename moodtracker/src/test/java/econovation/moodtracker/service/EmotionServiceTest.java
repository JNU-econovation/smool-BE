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
                .userPK(savedId)
                .build();
    }
}