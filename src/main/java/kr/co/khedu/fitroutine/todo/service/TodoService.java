package kr.co.khedu.fitroutine.todo.service;

import kr.co.khedu.fitroutine.exercise.model.dto.DailyExercise;
import kr.co.khedu.fitroutine.exercise.model.dto.ExerciseDetail;
import kr.co.khedu.fitroutine.exercise.model.dto.ExerciseRoutineList;
import kr.co.khedu.fitroutine.todo.model.dto.RoutineInfo;
import kr.co.khedu.fitroutine.todo.model.dto.MyRank;
import kr.co.khedu.fitroutine.todo.model.dto.RoutineMvpTOP3;
import kr.co.khedu.fitroutine.todo.model.dto.*;
import kr.co.khedu.fitroutine.todo.mapper.TodoMapper;
import kr.co.khedu.fitroutine.todo.model.dto.RoutineUpdateResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TodoService {
    private final TodoMapper todoMapper;
    public TodoService (TodoMapper todoMapper) {
        this.todoMapper = todoMapper;
    }

    public List<? extends TodayRoutine> getExerciseToday(long memberId) {
        List<? extends TodayRoutine> routines = todoMapper.getExerciseToday(memberId);
        for (TodayRoutine routine : routines) {
            String category = routine.getCategory();
            if ("EXERCISE".equals(category)) {
                routine.setCategory("운동");  // "EXERCISE" -> "운동"
            } else if ("MENU".equals(category)) {
                routine.setCategory("식단");  // "MENU" -> "식단"
            }
        }
        return routines;
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

    public Long getTodoIdByMemberId(long memberId){
        Long todoId = todoMapper.getTodoIdByMemberId(memberId);
        if (todoId == null){
            throw new IllegalStateException("등록된 루틴이 없습니다. " + memberId);
        }
        return todoMapper.getTodoIdByMemberId(memberId);
    }

    public void createExerciseRoutine(long todoId, ExerciseRoutineList exerciseRoutineList) {
        if (exerciseRoutineList == null || exerciseRoutineList.getExerciseList() == null
                || exerciseRoutineList.getExerciseList().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "운동 루틴이 존재하지 않습니다.");
        }

        if (!todoMapper.existsById(todoId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 Todo가 존재하지 않습니다.");
        }

        List<List<Integer>> routineList = exerciseRoutineList.getExerciseList();

        for (int i = 0; i < routineList.size(); i++) {
            int dayNo = i + 1;

            // TB_DAILY_EXSERCISE 저장
            DailyExercise dailyExercise = DailyExercise.builder()
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

    public RoutineUpdateResponse getExerciseTodoListById(long todoId){
        RoutineInfo routineInfo = todoMapper.getRoutineInfoByTodoId(todoId);
        ExerciseRoutineList exerciseRoutineList = getExerciseRoutineListByTodoId(todoId);

        return RoutineUpdateResponse.builder()
                .routineInfo(routineInfo)
                .exerciseRoutineList(exerciseRoutineList)
                .build();
    }

    public ExerciseRoutineList getExerciseRoutineListByTodoId(long todoId) {
        List<DailyExercise> dailyExercises = todoMapper.getDailyExercisesByTodoId(todoId);
        List<Long> dailyIds = dailyExercises.stream()
                .map(DailyExercise::getDailyExerciseId)
                .toList();

        if (dailyIds.isEmpty()) {
            return new ExerciseRoutineList(Collections.emptyList());
        }

        List<ExerciseDetail> details = todoMapper.getExerciseDetailByDailyIds(dailyIds);

        // dayIndex -> List<exerciseId>
        Map<Integer, List<Integer>> grouped = new TreeMap<>();

        for (DailyExercise daily : dailyExercises) {
            List<Integer> exerciseIds = details.stream()
                    .filter(d -> d.getDailyExerciseId() == daily.getDailyExerciseId())
                    .map(ExerciseDetail::getExerciseId)
                    .toList();

            grouped.put(daily.getDayNo(), exerciseIds);
        }

        return new ExerciseRoutineList(new ArrayList<>(grouped.values()));
    }

    public void updateExerciseRoutine(long todoId, ExerciseRoutineList exerciseRoutineList){
        if (!todoMapper.existsById(todoId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 Todo가 존재하지 않습니다.");
        }
        
        // 해당 todoId 자식 요소 삭제 후 재삽입
        todoMapper.deleteExerciseDetailByTodoId(todoId);
        todoMapper.deleteDailyExerciseByTodoId(todoId);

        List<List<Integer>> routineList = exerciseRoutineList.getExerciseList();

        for (int i = 0; i < routineList.size(); i++) {
            int dayNo = i + 1;

            // TB_DAILY_EXSERCISE 저장
            DailyExercise dailyExercise = DailyExercise.builder()
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

    public void deleteTodo(long memberId, Long todoId) {
        if (todoId == null) {
            throw new IllegalArgumentException("todoId는 null일 수 없습니다.");
        }

        if (todoMapper.deleteTodo(memberId, todoId) != 1) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "해당하는 TODO가 존재하지 않습니다. todoId=" + todoId);
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
}
