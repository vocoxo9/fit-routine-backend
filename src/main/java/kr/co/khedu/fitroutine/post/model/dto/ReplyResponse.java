package kr.co.khedu.fitroutine.post.model.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;

@Getter
public final class ReplyResponse {
    private final long replyId;
    private final String content;
    private final LocalDateTime createdAt;
    private final @Nullable Long parentId;
    private final long postId;

    @Builder
    private ReplyResponse(
            long replyId,
            String content,
            LocalDateTime createdAt,
            @Nullable Long parentId,
            long postId
    ) {
        this.replyId = replyId;
        this.content = content;
        this.createdAt = createdAt;
        this.parentId = parentId;
        this.postId = postId;
    }
}
