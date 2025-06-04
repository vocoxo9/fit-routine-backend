package kr.co.khedu.fitroutine.board.service;

import kr.co.khedu.fitroutine.board.model.dao.BoardMapper;
import kr.co.khedu.fitroutine.board.model.dto.PopularBoardDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceimpl implements BoardService {
    private BoardMapper boardMapper;
    public BoardServiceimpl (BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    @Override
    public List<PopularBoardDTO> getPopularBoardTop3() {
        List<PopularBoardDTO> boardList = boardMapper.getPopularBoardTop3();
        return boardList;
    }
}
