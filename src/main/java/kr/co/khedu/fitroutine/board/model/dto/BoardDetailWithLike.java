package kr.co.khedu.fitroutine.board.model.dto;

import kr.co.khedu.fitroutine.board.model.vo.Image;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import java.util.List;

@Getter
@Setter
public final class BoardDetailWithLike {
    private final String title;
    private final String content;
    private final String nickname;
    private final String createdAt;
    private final int likeCount;
    private final boolean liked;
    private @Nullable List<? extends Image> images;

    @Builder
    public BoardDetailWithLike(final String title, final String content, final String nickname,
                               final String createdAt, final int likeCount, final boolean liked) {
        this.title = title;
        this.content = content;
        this.nickname = nickname;
        this.createdAt = createdAt;
        this.likeCount = likeCount;
        this.liked = liked;
    }
}
