package econovation.moodtracker.service;

import econovation.moodtracker.domain.diary.Diary;
import econovation.moodtracker.domain.emotion.Emotion;
import econovation.moodtracker.domain.user.User;
import econovation.moodtracker.repository.DiaryRepository;
import econovation.moodtracker.repository.EmotionRepository;
import econovation.moodtracker.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class EmotionServiceTest {
    @Autowired EmotionService emotionService;
    @Autowired EmotionRepository emotionRepository;
    @Autowired DiaryService diaryService;
    @Autowired DiaryRepository diaryRepository;
    @Autowired UserService userService;
    @Autowired UserRepository userRepository;

    @Test
    //@Rollback(value = false)
    public void 감정저장(){
        //given
        User user = User.builder().build();
        Long userId = userService.join(user);

        Emotion emotion = Emotion.builder().build();
        //when
        Long savedEmotionId = emotionService.join(emotion);
        //then
        Optional<Emotion> getEmotion = emotionRepository.findById(savedEmotionId);
        assertNotNull(getEmotion);
        assertEquals(emotion, getEmotion.get());
    }
}