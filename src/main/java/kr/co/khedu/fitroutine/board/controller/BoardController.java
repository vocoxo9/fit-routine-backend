package kr.co.khedu.fitroutine.board.controller;

import kr.co.khedu.fitroutine.board.model.dto.PopularBoardDTO;
import kr.co.khedu.fitroutine.board.service.BoardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/boards")
public final class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/popular")
    public List<PopularBoardDTO> getPopularBoardTop3() {
        return boardService.getPopularBoardTop3();
    }
}
