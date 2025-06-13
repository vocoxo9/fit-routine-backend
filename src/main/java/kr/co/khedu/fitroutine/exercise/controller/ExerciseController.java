package kr.co.khedu.fitroutine.exercise.controller;

import kr.co.khedu.fitroutine.exercise.model.dto.ExerciseRoutine;
import kr.co.khedu.fitroutine.exercise.model.vo.ExerciseOpenData;
import kr.co.khedu.fitroutine.exercise.service.ExerciseService;
import kr.co.khedu.fitroutine.member.model.dto.MemberDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exercises")
public final class ExerciseController {
    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping("/open-data")
    public ResponseEntity<List<? extends ExerciseOpenData>> getAllExerciseOpenDataList() {
        return ResponseEntity.ok(exerciseService.getAllExerciseOpenDataList());
    }

    @GetMapping("/random-routine")
    public ResponseEntity<ExerciseRoutine> getRandomExerciseRoutine(@RequestParam int dayRepeat, @RequestParam String purpose){
        return ResponseEntity.ok(exerciseService.getRandomExerciseRoutineTransform(dayRepeat, purpose));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExerciseOpenData> getExerciseById(@PathVariable int id){
        return ResponseEntity.ok(exerciseService.getExerciseById(id));
    }

    @GetMapping("/member-detail")
    public ResponseEntity<MemberDetail> getMemberDetail(){
        long memberId = 1;
        return ResponseEntity.ok(exerciseService.getMemberDetail(memberId));
    }
}
