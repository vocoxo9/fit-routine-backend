package kr.co.khedu.fitroutine.todo.controller;

import kr.co.khedu.fitroutine.todo.model.dto.RoutineInfo;
import kr.co.khedu.fitroutine.security.model.dto.UserDetailsImpl;
import kr.co.khedu.fitroutine.todo.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public final class TodoController {
    private final TodoService todoService;
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/routine-rankings/monthly")
    public ResponseEntity<?> getRoutineMvpTOP3() {
        return ResponseEntity.ok(todoService.getRoutineMvpTOP3());
    }

    @GetMapping("/routine-rankings/monthly/me")
    public ResponseEntity<?> getRoutineMvpMyRank() {
        long memberId = 1;
        return ResponseEntity.ok(todoService.getgetRoutineMvpMyRank(memberId));
    }

    @PostMapping("/todos/exercise")
    public ResponseEntity<Integer> registExerciseRoutine(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody RoutineInfo routineInfo
    ){
        System.out.println(routineInfo);
        todoService.registExerciseRoutine(userDetails.getMemberId(), routineInfo);
        return ResponseEntity.noContent().build();
    }
}
