package kr.co.khedu.fitroutine.todo.service;

import kr.co.khedu.fitroutine.todo.model.dto.*;
import kr.co.khedu.fitroutine.todo.mapper.TodoMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    private final TodoMapper todoMapper;
    public TodoService (TodoMapper todoMapper) {
        this.todoMapper = todoMapper;
    }

    public List<? extends RoutineMvpTOP3> getRoutineMvpTOP3() {
        return todoMapper.getRoutineMvpTOP3();
    }

    public MyRank getgetRoutineMvpMyRank(long memberId) {
        MyRank myRank = todoMapper.getRoutineMvpMyRank(memberId);
        if(myRank == null){
            throw new IllegalStateException("등록된 루틴을 찾을 수 없습니다. " + memberId);
        }
        return myRank;
    }

    private List<? extends Menu> getTodayMenuList(long memberId) {
        return todoMapper.getTodayMenuList(memberId);
    }

    private List<? extends Exercise> getTodayExerciseList(long memberId) {
        return todoMapper.getTodayExerciseList(memberId);
    }

    public MenuTodoListResponse getMenuTodoList(long memberId) {
        MenuTodoListResponse menuTodoListResponse = todoMapper.getMenuTodoList(memberId);
        menuTodoListResponse.setTodos(getTodayMenuList(memberId));
        return menuTodoListResponse;
    }

    public ExerciseTodoListResponse getExerciseTodoList(long memberId) {
        ExerciseTodoListResponse exerciseTodoListResponse = todoMapper.getExerciseTodoList(memberId);
        exerciseTodoListResponse.setTodos(getTodayExerciseList(memberId));
        return exerciseTodoListResponse;
    }
}
