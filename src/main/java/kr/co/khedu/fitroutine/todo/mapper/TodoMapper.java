package kr.co.khedu.fitroutine.todo.mapper;

import kr.co.khedu.fitroutine.todo.dto.RoutineMvpTOP3;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TodoMapper {
    List<? extends RoutineMvpTOP3> getRoutineMvpTOP3();
}
