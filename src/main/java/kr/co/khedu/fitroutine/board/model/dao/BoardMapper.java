package kr.co.khedu.fitroutine.board.model.dao;

import kr.co.khedu.fitroutine.board.model.dto.PopularBoardDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<PopularBoardDTO> getPopularBoardTop3();
}
