package kr.co.khedu.fitroutine.post.model.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public final class SimplePost {
    private final long postId;
    private final String title;

    @Builder
    private SimplePost(long postId, String title) {
        this.postId = postId;
        this.title = title;
    }
}
