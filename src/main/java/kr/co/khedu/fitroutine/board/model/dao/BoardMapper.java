package kr.co.khedu.fitroutine.board.model.dao;

import kr.co.khedu.fitroutine.board.model.dto.PopularBoard;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<? extends PopularBoard> getPopularBoardTop3();
}
