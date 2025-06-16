package kr.co.khedu.fitroutine.todo.service;

import kr.co.khedu.fitroutine.exercise.model.dto.DailyExercises;
import kr.co.khedu.fitroutine.exercise.model.dto.ExerciseRoutineList;
import kr.co.khedu.fitroutine.todo.model.dto.RoutineInfo;
import kr.co.khedu.fitroutine.todo.model.dto.MyRank;
import kr.co.khedu.fitroutine.todo.model.dto.RoutineMvpTOP3;
import kr.co.khedu.fitroutine.todo.model.dto.*;
import kr.co.khedu.fitroutine.todo.mapper.TodoMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

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

    public Long createRoutineInfo(long memberId, RoutineInfo routineInfo){
        todoMapper.createRoutineInfo(memberId, routineInfo);
        return routineInfo.getTodoId();
    }


    public void createExerciseRoutine(long todoId, ExerciseRoutineList exerciseRoutineList) {
        List<List<Integer>> routineList = exerciseRoutineList.getExerciseList();

        for (int i = 0; i < routineList.size(); i++) {
            int dayNo = i + 1;

            // TB_DAILY_EXSERCISE 저장
            DailyExercises dailyExercise = DailyExercises.builder()
                    .todoId(todoId)
                    .dayNo(dayNo)
                    .build();
            todoMapper.insertDailyExercise(dailyExercise);

            // TB_EXERCISE_DETAIL 저장
            Long dailyExerciseId = dailyExercise.getDailyExerciseId();

            for (int exerciseId : routineList.get(i)) {
                todoMapper.insertExerciseDetail(dailyExerciseId, exerciseId);
            }
        }
    }

    private List<? extends Menu> getTodayMenuList(long memberId) {
        return todoMapper.getTodayMenuList(memberId);
    }

    private List<? extends Exercise> getTodayExerciseList(long memberId) {
        return todoMapper.getTodayExerciseList(memberId);
    }

    public MenuTodoListResponse getMenuTodoList(long memberId) {
        MenuTodoListResponse menuTodoListResponse = todoMapper.getMenuTodoList(memberId);
        if (menuTodoListResponse != null) {
            menuTodoListResponse.setTodos(getTodayMenuList(memberId));
        }
        return menuTodoListResponse;
    }

    public ExerciseTodoListResponse getExerciseTodoList(long memberId) {
        ExerciseTodoListResponse exerciseTodoListResponse = todoMapper.getExerciseTodoList(memberId);
        if (exerciseTodoListResponse != null) {
            exerciseTodoListResponse.setTodos(getTodayExerciseList(memberId));
        }
        return exerciseTodoListResponse;
    }

    public void deleteTodo(long memberId, Long todoId) {
        if(todoMapper.deleteTodo(memberId, todoId) != 1) {
            throw new NoSuchElementException("해당하는 TODO가 존재하지 않습니다. todoId=" + todoId);
        }
    }
}
