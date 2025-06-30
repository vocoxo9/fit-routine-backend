package kr.co.khedu.fitroutine.exercise.service;

import kr.co.khedu.fitroutine.exercise.mapper.ExerciseMapper;
import kr.co.khedu.fitroutine.todo.model.dto.RoutineInfo;
import kr.co.khedu.fitroutine.exercise.model.dto.ExerciseRoutineList;
import kr.co.khedu.fitroutine.exercise.model.vo.ExerciseOpenData;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public final class ExerciseService {
    private final ExerciseMapper exerciseMapper;

    public ExerciseService(ExerciseMapper exerciseMapper) {
        this.exerciseMapper = exerciseMapper;
    }

    public List<? extends ExerciseOpenData> getAllExerciseOpenDataList(String purpose) {
        if (purpose == null || purpose.trim().isEmpty()) {
            throw new IllegalArgumentException("목적은 null일 수 없습니다.");
        }
        return exerciseMapper.getAllExerciseOpenDataList(purpose);
    }

    // 운동 루틴 랜덤 추출
    public List<? extends Integer> getRandomExerciseRoutine(int dayRepeat, String purpose) {
        if (dayRepeat <= 0) {
            throw new IllegalArgumentException("반복일은 0이나 음수일 수 없습니다.");
        }
        if (purpose == null || purpose.trim().isEmpty()) {
            throw new IllegalArgumentException("목적은 null일 수 없습니다.");
        }
        return exerciseMapper.getRandomExerciseRoutine(dayRepeat, purpose);
    }

    // 운동 루틴 랜덤 추출을 통해 Front에서 사용할 형태로 변환
    public ExerciseRoutineList getRandomExerciseRoutineTransform(int dayRepeat, String purpose) {
        if(dayRepeat <= 0){
            throw new IllegalArgumentException("반복일은 0이나 음수일 수 없습니다.");
        }
        List<Integer> randomExerciseList = (List<Integer>) getRandomExerciseRoutine(dayRepeat, purpose);
        if(randomExerciseList.size() < dayRepeat*5){
            throw new IllegalStateException("배열의 크기가 충분하지 않습니다.");
        }
        /*
            0 : 0 ~ 4
            1 : 5 ~ 9
            2 : 10 ~ 14
            ...
            6 : 30 ~ 34
         */
        List<List<Integer>> exerciseList = new ArrayList<>();
        for (int i = 0; i < dayRepeat; i++) {
            int start = i * 5;
            int end = start + 5;

            exerciseList.add(randomExerciseList.subList(start, end));
        }
        return new ExerciseRoutineList(exerciseList);
    }

    public ExerciseOpenData getExerciseById(int id) {
        ExerciseOpenData data = exerciseMapper.getExerciseById(id);
        if (data == null) {
            throw new NoSuchElementException("운동 ID가 존재하지 않습니다 " + id);
        }
        return data;
    }

}
