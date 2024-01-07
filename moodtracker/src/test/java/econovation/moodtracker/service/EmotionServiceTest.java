package econovation.moodtracker.service;

import econovation.moodtracker.repository.EmotionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class EmotionServiceTest {
    @Autowired EmotionService emotionService;
    @Autowired EmotionRepository emotionRepository;

    @Test
    public void 감정저장(){
        //given
        //when
        //then

    }
}