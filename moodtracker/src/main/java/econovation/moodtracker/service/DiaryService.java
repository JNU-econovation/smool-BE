package econovation.moodtracker.service;

import econovation.moodtracker.domain.Diary;
import econovation.moodtracker.repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DiaryService {
    private final DiaryRepository diaryRepository;

    public Long join(Diary diary){
        diaryRepository.save(diary);
        return diary.getId();
    }
    
    public void updateDiary(Long diaryPK, String content){
        Diary diary = diaryRepository.findById(diaryPK)
                .orElseThrow(() -> new NullPointerException("일기가 없어용"));
        diary.update(content);
    }
}
