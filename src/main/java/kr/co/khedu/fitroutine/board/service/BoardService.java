package kr.co.khedu.fitroutine.board.service;

import kr.co.khedu.fitroutine.board.model.dto.PopularBoardDTO;

import java.util.List;

public interface BoardService {
    List<? extends PopularBoardDTO> getPopularBoardTop3();
}
