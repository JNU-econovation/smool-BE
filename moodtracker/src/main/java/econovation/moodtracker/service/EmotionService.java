package econovation.moodtracker.service;


import econovation.moodtracker.domain.emotion.DTO.Request.EmotionCreateRequestDTO;
import econovation.moodtracker.domain.emotion.DTO.Response.EmotionResponseDTO;
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
    private final UserRepository userRepository;

    public EmotionResponseDTO join(EmotionCreateRequestDTO emotionCreateRequestDTO){

        User user = userRepository.findById(emotionCreateRequestDTO.getUserPK())
                .orElseThrow(()->new IllegalArgumentException("아이디 없음"));
        Emotion emotion = Emotion.builder()
                .happiness(emotionCreateRequestDTO.getHappiness())
                .sadness(emotionCreateRequestDTO.getSadness())
                .anxiety(emotionCreateRequestDTO.getAnxiety())
                .stress(emotionCreateRequestDTO.getStress())
                .sleepTime(emotionCreateRequestDTO.getSleepTime())
                .diaries(new ArrayList<>())
                .build();
        emotionRepository.save(emotion);
        return EmotionResponseDTO.of(emotion);
    }

}
