package kr.co.khedu.fitroutine.todo.mapper;

import kr.co.khedu.fitroutine.exercise.model.dto.DailyExercise;
import kr.co.khedu.fitroutine.exercise.model.dto.ExerciseDetail;
import kr.co.khedu.fitroutine.exercise.model.dto.ExerciseRoutineList;
import kr.co.khedu.fitroutine.todo.model.dto.RoutineInfo;
import kr.co.khedu.fitroutine.todo.model.dto.MyRank;
import kr.co.khedu.fitroutine.todo.model.dto.RoutineMvpTOP3;
import kr.co.khedu.fitroutine.todo.model.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.lang.Nullable;

import java.util.List;

@Mapper
public interface TodoMapper {
    List<? extends RoutineMvpTOP3> getRoutineMvpTOP3();

    @Nullable MyRank getRoutineMvpMyRank(long memberId);

    Long createRoutineInfo(long memberId, RoutineInfo routineInfo);

    @Nullable Long getTodoIdByMemberId(long memberId);

    int insertDailyExercise(DailyExercise dailyExercise);

    int insertExerciseDetail(long dailyExerciseId, int exerciseId);

    int deleteExerciseRoutine(long todoId);

    RoutineInfo getRoutineInfoByTodoId(long todoId);

    List<DailyExercise> getDailyExercisesByTodoId(long todoId);

    List<ExerciseDetail> getExerciseDetailByDailyIds(@Param("dailyIds") List<Long> dailyExerciseId);

    int createExerciseRoutine(long memberId, ExerciseRoutineList exerciseRoutineList);

    List<? extends Menu> getTodayMenuList(long memberId);

    List<? extends Exercise> getTodayExerciseList(long memberId);

    @Nullable MenuTodoListResponse getMenuTodoList(long memberId);

    @Nullable ExerciseTodoListResponse getExerciseTodoList(long memberId);

    int deleteTodo(long memberId, Long todoId);
}
