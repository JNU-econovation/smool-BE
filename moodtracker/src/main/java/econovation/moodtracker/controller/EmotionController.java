package econovation.moodtracker.controller;

import econovation.moodtracker.service.EmotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EmotionController {
    private final EmotionService emotionService;
}
