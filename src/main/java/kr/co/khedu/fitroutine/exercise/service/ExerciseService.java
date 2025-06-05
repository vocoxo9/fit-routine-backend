package kr.co.khedu.fitroutine.exercise.service;

import kr.co.khedu.fitroutine.exercise.model.dao.ExerciseMapper;
import kr.co.khedu.fitroutine.exercise.model.vo.ExerciseOpenData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class ExerciseService {

    private final ExerciseMapper exerciseMapper;
    @Autowired
    public ExerciseService(ExerciseMapper exerciseMapper) {
        this.exerciseMapper = exerciseMapper;
    }

    // 운동 공공데이터 추출
    public List<? extends ExerciseOpenData> getAllExerciseOpenDataList() {
        return exerciseMapper.getAllExerciseOpenDataList();
    }


}
