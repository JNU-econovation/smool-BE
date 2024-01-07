package econovation.moodtracker.service;


import econovation.moodtracker.domain.dto.Request.DiaryCreateRequestDTO;
import econovation.moodtracker.domain.emotion.Emotion;
import econovation.moodtracker.domain.user.User;
import econovation.moodtracker.repository.EmotionRepository;
import econovation.moodtracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Transactional
@RequiredArgsConstructor
public class EmotionService {

    private final EmotionRepository emotionRepository;

    public void join(DiaryCreateRequestDTO diaryCreateRequestDTO){

        Emotion emotion = Emotion.builder()
                .happiness(diaryCreateRequestDTO.getHappiness())
                .sadness(diaryCreateRequestDTO.getSadness())
                .anxiety(diaryCreateRequestDTO.getAnxiety())
                .stress(diaryCreateRequestDTO.getStress())
                .sleepTime(diaryCreateRequestDTO.getSleepTime())
                .diaries(new ArrayList<>())
                .build();
        emotionRepository.save(emotion);
    }
}
