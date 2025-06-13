package kr.co.khedu.fitroutine.post.model.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public final class ReplyLikesResponse {
    private final boolean liked;
    private final int count;

    @Builder
    private ReplyLikesResponse(
            boolean liked,
            int count
    ) {
        this.liked = liked;
        this.count = count;
    }
}
