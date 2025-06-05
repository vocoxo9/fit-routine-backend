package kr.co.khedu.fitroutine.board.model.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public final class PopularBoard {
    private final int boardId;
    private final String title;
    private final String nickname;

    @Builder
    private PopularBoard(
            int boardId,
            String title,
            String nickname
    ) {
        this.boardId = boardId;
        this.title = title;
        this.nickname = nickname;
    }
}
