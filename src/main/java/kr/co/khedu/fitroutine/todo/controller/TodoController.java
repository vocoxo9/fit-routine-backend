package kr.co.khedu.fitroutine.todo.controller;

import kr.co.khedu.fitroutine.security.model.dto.UserDetailsImpl;
import kr.co.khedu.fitroutine.todo.model.dto.ExerciseTodoListResponse;
import kr.co.khedu.fitroutine.todo.model.dto.MenuTodoListResponse;
import kr.co.khedu.fitroutine.todo.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public ResponseEntity<?> getRoutineMvpMyRank(
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        return ResponseEntity.ok(todoService.getgetRoutineMvpMyRank(userDetails.getMemberId()));
    }

    @GetMapping("/todos/menu")
    public ResponseEntity<MenuTodoListResponse> getMenuTodoList(
            @AuthenticationPrincipal final UserDetailsImpl userDetails
    ) {
        return ResponseEntity.ok(todoService.getMenuTodoList(userDetails.getMemberId()));
    }

    @GetMapping("/todos/exercise")
    public ResponseEntity<ExerciseTodoListResponse> getExerciseTodoList(
            @AuthenticationPrincipal final UserDetailsImpl userDetails
    ) {
        return ResponseEntity.ok(todoService.getExerciseTodoList(userDetails.getMemberId()));
    }
}
