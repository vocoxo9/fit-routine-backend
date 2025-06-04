package kr.co.khedu.fitroutine.board.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.Date;

@Getter
public class PopularBoardDTO {
    private int boardId;
    private String title;
    private String nickname;

    @Builder
    private PopularBoardDTO (int boardId, String title, String nickname) {
        this.boardId = boardId;
        this.title = title;
        this.nickname = nickname;
    }
}
