package econovation.moodtracker.service;


import econovation.moodtracker.domain.Diary;
import econovation.moodtracker.domain.dto.Request.DiaryCreateRequestDTO;
import econovation.moodtracker.domain.Emotion;
import econovation.moodtracker.domain.dto.Request.DiaryUpdateRequestDTO;
import econovation.moodtracker.domain.dto.Response.EmotionDateDTO;
import econovation.moodtracker.domain.dto.Response.EmotionResponseDTO;
import econovation.moodtracker.exception.DiaryNotFoundException;
import econovation.moodtracker.exception.EmotionNotFoundException;
import econovation.moodtracker.repository.DiaryRepository;
import econovation.moodtracker.repository.EmotionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class EmotionService {

    private final EmotionRepository emotionRepository;
    private final DiaryRepository diaryRepository;
    public Emotion join(DiaryCreateRequestDTO diaryCreateRequestDTO){

        Emotion emotion = Emotion.builder()
                .happiness(diaryCreateRequestDTO.getHappiness())
                .gloom(diaryCreateRequestDTO.getGloom())
                .anxiety(diaryCreateRequestDTO.getAnxiety())
                .stress(diaryCreateRequestDTO.getStress())
                .sleep(diaryCreateRequestDTO.getSleep())
                .diaries(new ArrayList<>())
                .build();
        return emotionRepository.save(emotion);
    }

    public Emotion updateEmotion(DiaryUpdateRequestDTO diaryUpdateRequestDTO){
        Emotion emotion = findEmotion(diaryUpdateRequestDTO.getEmotionPk());
        emotion.update(diaryUpdateRequestDTO.getHappiness(),
                diaryUpdateRequestDTO.getGloom(),
                diaryUpdateRequestDTO.getAnxiety(),
                diaryUpdateRequestDTO.getStress(),
                diaryUpdateRequestDTO.getSleep());
        return emotion;
    }

    public Emotion updateEmotion(DiaryCreateRequestDTO diaryCreateRequestDTO){
        LocalDate localDate = diaryCreateRequestDTO.getLocalDate();
        LocalDateTime startTime = LocalDateTime.now()
                .withYear(localDate.getYear())
                .withMonth(localDate.getMonthValue())
                .withDayOfMonth(localDate.getDayOfMonth())
                .withHour(0)
                .withMinute(0);
        LocalDateTime endTime = startTime
                .withHour(23)
                .withMinute(59)
                .withSecond(59);

        List<Diary> diaries = diaryRepository.findAllByTimeBetweenAndUserId(startTime, endTime, diaryCreateRequestDTO.getUserPk());
        if (diaries.isEmpty()){
            throw new DiaryNotFoundException();
        }
        Emotion emotion = diaries.get(0).getEmotion();
        emotion.update(diaryCreateRequestDTO.getHappiness(), diaryCreateRequestDTO.getGloom(),
                       diaryCreateRequestDTO.getAnxiety(), diaryCreateRequestDTO.getStress(),
                       diaryCreateRequestDTO.getSleep());

        return emotion;
    }

    public Emotion findEmotion(Long emotionPK){
        return emotionRepository.findById(emotionPK)
                .orElseThrow(EmotionNotFoundException::new);
    }

    public EmotionResponseDTO findStatisticsEmotions(LocalDate endDate, Long userPK){
        List<EmotionDateDTO> happinessDateDTOList = new ArrayList<>();
        List<EmotionDateDTO> gloomDateDTOList = new ArrayList<>();
        List<EmotionDateDTO> anxietyDateDTOList = new ArrayList<>();
        List<EmotionDateDTO> stressDateDTOList = new ArrayList<>();
        List<EmotionDateDTO> sleepDateDTOList = new ArrayList<>();

        List<Integer> existDate = new ArrayList<>();
        Map<Integer, Emotion> emotionMap = new HashMap<>();

        LocalDate startTime = endDate.withDayOfMonth(1);
        for(int i=0; i<endDate.getDayOfMonth();i++){
            LocalDate date = startTime.plusDays((i));
            List<Diary> diaries = findEmotionByDiaries(date, userPK);

            if (!diaries.isEmpty()){
                existDate.add(date.getDayOfMonth());
                emotionMap.put(date.getDayOfMonth(), diaries.get(0).getEmotion());
            }
            else{
                emotionMap.put(date.getDayOfMonth(), null);
            }
        }

        if(!existDate.contains(1)){
            existDate.add(0,1);
            emotionMap.put(1, Emotion.builder()
                    .happiness(5)
                    .gloom(5)
                    .anxiety(5)
                    .stress(5)
                    .sleep(5)
                    .build());
        }
        if(!existDate.contains(endDate.getDayOfMonth())){
            existDate.add(endDate.getDayOfMonth());
            emotionMap.put(endDate.getDayOfMonth(), Emotion.builder()
                    .happiness(5)
                    .gloom(5)
                    .anxiety(5)
                    .stress(5)
                    .sleep(5)
                    .build());
        }

        for(int i=0;i<existDate.size()-1;i++){
            int start = existDate.get(i);
            int end = existDate.get(i+1);
            if ((end - start) != 1){
                for(int j=1;j<end-start;j++){
                    int startHappiness = emotionMap.get(start).getHappiness();
                    int startGloom = emotionMap.get(start).getGloom();
                    int startAnxiety = emotionMap.get(start).getAnxiety();
                    int startStress = emotionMap.get(start).getStress();
                    int startSleep = emotionMap.get(start).getSleep();

                    int endHappiness = emotionMap.get(end).getHappiness();
                    int endGloom = emotionMap.get(end).getGloom();
                    int endAnxiety = emotionMap.get(end).getAnxiety();
                    int endStress = emotionMap.get(end).getStress();
                    int endSleep = emotionMap.get(end).getSleep();

                    emotionMap.put(start+j, Emotion.builder()
                            .happiness(startHappiness + (j * (endHappiness - startHappiness))/(end-start))
                            .gloom(startGloom + (j * (endGloom - startGloom))/(end-start))
                            .anxiety(startAnxiety + (j * (endAnxiety - startAnxiety))/(end-start))
                            .stress(startStress + (j * (endStress - startStress))/(end-start))
                            .sleep(startSleep + (j * (endSleep - startSleep))/(end-start))
                            .build());
                }
            }
        }
        for(int i=0;i<endDate.getDayOfMonth();i++){
            LocalDate date = startTime.plusDays((i));
            happinessDateDTOList.add(EmotionDateDTO.of(date, emotionMap.get(i+1).getHappiness()));
            gloomDateDTOList.add(EmotionDateDTO.of(date, emotionMap.get(i+1).getGloom()));
            anxietyDateDTOList.add(EmotionDateDTO.of(date, emotionMap.get(i+1).getAnxiety()));
            stressDateDTOList.add(EmotionDateDTO.of(date, emotionMap.get(i+1).getStress()));
            sleepDateDTOList.add(EmotionDateDTO.of(date, emotionMap.get(i+1).getSleep()));
        }

        return EmotionResponseDTO.of(happinessDateDTOList, gloomDateDTOList, anxietyDateDTOList, stressDateDTOList, sleepDateDTOList);
    }

    public void deleteEmotion(Long emotionPK){
        emotionRepository.delete(findEmotion(emotionPK));
    }

    public List<Diary> findEmotionByDiaries(LocalDate localDate, Long userPK) {
        LocalDateTime startTime = LocalDateTime.of(
                localDate.getYear(),
                localDate.getMonthValue(),
                localDate.getDayOfMonth(),
                0, 0, 0);
        ;
        LocalDateTime endTime = startTime
                .withHour(23)
                .withMinute(59)
                .withSecond(59);
        return diaryRepository.findAllByTimeBetweenAndUserId(startTime, endTime, userPK);
    }
}
