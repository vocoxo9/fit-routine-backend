package kr.co.khedu.fitroutine.board.model.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
public final class Image {
    private final long imageId;
    private final String originName;
    private final String changeName;
    private final long boardId;

    @Builder
    private Image(long imageId, String originName, String changeName, long boardId) {
        this.imageId = imageId;
        this.originName = originName;
        this.changeName = changeName;
        this.boardId = boardId;
    }
}
