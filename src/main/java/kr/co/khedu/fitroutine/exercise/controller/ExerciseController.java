package kr.co.khedu.fitroutine.exercise.controller;

import kr.co.khedu.fitroutine.exercise.model.vo.ExerciseOpenData;
import kr.co.khedu.fitroutine.exercise.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/exercise")
public final class ExerciseController {


    private final ExerciseService exerciseService;
    @Autowired
    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    /**
     * @return 운동 공공데이터 리스트 반환
     */
    @GetMapping("/openData")
    public ResponseEntity<List<? extends ExerciseOpenData>> getAllExerciseOpenDataList() {
        List<? extends ExerciseOpenData> exerciseList = exerciseService.getAllExerciseOpenDataList();
        return ResponseEntity.ok(exerciseList);
    }
}
