package kr.co.khedu.fitroutine.board.service;

import kr.co.khedu.fitroutine.board.model.dao.BoardMapper;
import kr.co.khedu.fitroutine.board.model.dto.PopularBoardDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class BoardService {
    private final BoardMapper boardMapper;

    public BoardService(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    public List<? extends PopularBoardDTO> getPopularBoardTop3() {
        return boardMapper.getPopularBoardTop3();
    }
}
