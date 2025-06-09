package kr.co.khedu.fitroutine.exercise.controller;

import kr.co.khedu.fitroutine.exercise.model.dto.ExerciseRoutine;
import kr.co.khedu.fitroutine.exercise.service.ExerciseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exercises")
public final class ExerciseController {
    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping("/open-data")
    public ResponseEntity<?> getAllExerciseOpenDataList() {
        return ResponseEntity.ok(exerciseService.getAllExerciseOpenDataList());
    }

    @GetMapping("/random-routine")
    public ResponseEntity<?> getRandomExerciseRoutine(@RequestParam int dayRepeat){
        return ResponseEntity.ok(exerciseService.getRandomExerciseRoutineTransform(dayRepeat));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getExerciseById(@PathVariable int id){
        return ResponseEntity.ok(exerciseService.getExerciseById(id));
    }
}
