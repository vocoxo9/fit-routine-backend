package kr.co.khedu.fitroutine.blog.model.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public final class FollowStatusResponse {
    private final boolean followed;

    @Builder
    private FollowStatusResponse(boolean followed) {
        this.followed = followed;
    }
}
