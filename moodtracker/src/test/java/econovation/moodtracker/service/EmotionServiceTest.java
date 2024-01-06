package econovation.moodtracker.service;

import econovation.moodtracker.domain.emotion.DTO.Request.EmotionCreateRequestDTO;
import econovation.moodtracker.domain.emotion.DTO.Response.EmotionResponseDTO;
import econovation.moodtracker.domain.user.DTO.Request.UserCreateRequestDTO;
import econovation.moodtracker.domain.user.User;
import econovation.moodtracker.repository.DiaryRepository;
import econovation.moodtracker.repository.EmotionRepository;
import econovation.moodtracker.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
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
        userService.join(UserCreateRequestDTO
                .builder()
                .build()
        );
        EmotionCreateRequestDTO emotionCreateRequestDTO = EmotionCreateRequestDTO.builder()
                .userPK(1L)
                .happiness(1)
                .anxiety(2)
                .sadness(3)
                .stress(4)
                .sleepTime(5)
                .build();

        //when
        EmotionResponseDTO savedEmotionDTO = emotionService.join(emotionCreateRequestDTO);

        //then
        assertEquals(emotionRepository.findAll().size(), 1);
    }
}