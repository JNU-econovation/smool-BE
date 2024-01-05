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
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class DiaryServiceTest {
    @Autowired UserService userService;
    @Autowired UserRepository userRepository;
    @Autowired DiaryService diaryService;
    @Autowired DiaryRepository diaryRepository;
    @Autowired EmotionService emotionService;
    @Autowired EmotionRepository emotionRepository;

    @Test
    //@Rollback(value = false)
    public void 한_사용자_일기_두_개() {
    //given
    User user = User.builder().build();
    userService.join(user);

    Emotion emotion = Emotion.builder().build();
    emotionService.join(emotion);

    Diary diary = Diary.builder()
            .user(user)
            .emotion(emotion)
            .build();

    Diary diary1 = Diary.builder()
            .user(user)
            .emotion(emotion)
            .build();
    //when
    diaryService.join(diary);
    diaryService.join(diary1);
    //then
    //일기 작성자 확인
    assertEquals(diary.getUser(), diary1.getUser());
    //한 사용자가 쓴 일기 개수 확인
    assertEquals(user.getDiaries().size(),2);

    }
}