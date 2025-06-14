package kr.co.khedu.fitroutine.todo.mapper;

import kr.co.khedu.fitroutine.todo.model.dto.RoutineInfo;
import kr.co.khedu.fitroutine.todo.model.dto.MyRank;
import kr.co.khedu.fitroutine.todo.model.dto.RoutineMvpTOP3;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.lang.Nullable;

import java.util.List;

@Mapper
public interface TodoMapper {
    List<? extends RoutineMvpTOP3> getRoutineMvpTOP3();

    @Nullable MyRank getRoutineMvpMyRank(long memberId);

    int insertRoutineInfo(long memberId, RoutineInfo routineInfo);
}
