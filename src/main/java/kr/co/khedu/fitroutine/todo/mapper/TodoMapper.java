package kr.co.khedu.fitroutine.todo.mapper;

import kr.co.khedu.fitroutine.todo.model.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.lang.Nullable;

import java.util.List;

@Mapper
public interface TodoMapper {
    List<? extends RoutineMvpTOP3> getRoutineMvpTOP3();

    @Nullable MyRank getRoutineMvpMyRank(long memberId);

    List<? extends Menu> getTodayMenuList(long memberId);

    List<? extends Exercise> getTodayExerciseList(long memberId);

    MenuTodoListResponse getMenuTodoList(long memberId);

    ExerciseTodoListResponse getExerciseTodoList(long memberId);
}
