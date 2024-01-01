package econovation.moodtracker.service;


import econovation.moodtracker.domain.emotion.Emotion;
import econovation.moodtracker.repository.EmotionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class EmotionService {

    private final EmotionRepository emotionRepository;

    public Long join(Emotion emotion){
        emotionRepository.save(emotion);
        return emotion.getId();
    }

}
