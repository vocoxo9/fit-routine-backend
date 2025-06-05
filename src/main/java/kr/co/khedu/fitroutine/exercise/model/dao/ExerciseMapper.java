package kr.co.khedu.fitroutine.exercise.model.dao;

import kr.co.khedu.fitroutine.exercise.model.vo.ExerciseOpenData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExerciseMapper {

    // 운동 공공데이터 리스트 가져오기
    public List<? extends ExerciseOpenData> getAllExerciseOpenDataList();

}
