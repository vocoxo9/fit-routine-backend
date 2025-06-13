package kr.co.khedu.fitroutine.exercise.service;

import kr.co.khedu.fitroutine.exercise.mapper.ExerciseMapper;
import kr.co.khedu.fitroutine.exercise.model.dto.ExerciseRoutine;
import kr.co.khedu.fitroutine.exercise.model.vo.ExerciseOpenData;
import kr.co.khedu.fitroutine.member.model.dto.MemberDetail;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public final class ExerciseService {
    private final ExerciseMapper exerciseMapper;

    public ExerciseService(ExerciseMapper exerciseMapper) {
        this.exerciseMapper = exerciseMapper;
    }

    public List<? extends ExerciseOpenData> getAllExerciseOpenDataList() {
        return exerciseMapper.getAllExerciseOpenDataList();
    }

    // 운동 루틴 랜덤 추출
    public List<? extends Integer> getRandomExerciseRoutine(int dayRepeat, String purpose) {
        return exerciseMapper.getRandomExerciseRoutine(dayRepeat, purpose);
    }

    // 운동 루틴 랜덤 추출을 통해 Front에서 사용할 형태로 변환
    public ExerciseRoutine getRandomExerciseRoutineTransform(int dayRepeat, String purpose) {
        List<Integer> randomExerciseList = (List<Integer>) getRandomExerciseRoutine(dayRepeat, purpose);
        /*
            0 : 0 ~ 4
            1 : 5 ~ 9
            2 : 10 ~ 14
            ...
            6 : 30 ~ 34
         */
        List<List<Integer>> exerciseList = new ArrayList<>();
        for (int i = 0; i < dayRepeat; i++) {
            int start = i * dayRepeat;
            int end = start + 5;

            exerciseList.add(randomExerciseList.subList(start, end));
        }
        return new ExerciseRoutine(dayRepeat, exerciseList);
    }

    public ExerciseOpenData getExerciseById(int id) {
        return exerciseMapper.getExerciseById(id);
    }

}
