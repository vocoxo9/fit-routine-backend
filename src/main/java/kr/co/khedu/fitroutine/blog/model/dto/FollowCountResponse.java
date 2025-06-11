package kr.co.khedu.fitroutine.blog.model.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public final class FollowCountResponse {
    private final int count;

    @Builder
    private FollowCountResponse(int count) {
        this.count = count;
    }
}
