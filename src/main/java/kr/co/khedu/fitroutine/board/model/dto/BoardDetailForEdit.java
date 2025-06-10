package kr.co.khedu.fitroutine.board.model.dto;

import jakarta.annotation.Nullable;
import kr.co.khedu.fitroutine.board.model.vo.Image;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class BoardDetailForEdit {
    private final long boardId;
    private final String title;
    private final String content;
    private final String category;
    private @Nullable List<? extends Image> images;

    @Builder
    public BoardDetailForEdit(final long boardId, final String title, final String content, final String category) {
        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.category = category;
    }
}
