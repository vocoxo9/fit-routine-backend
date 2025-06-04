package kr.co.khedu.fitroutine.board.service;

import kr.co.khedu.fitroutine.board.model.dto.PopularBoardDTO;

import java.util.List;

public interface BoardService {
    /* 인기글 TOP3를 조회 */
    public List<PopularBoardDTO> getPopularBoardTop3();
}
