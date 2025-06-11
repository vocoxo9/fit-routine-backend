package kr.co.khedu.fitroutine.blog.model.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public final class FollowResponse {
    private final long blogId;
    private final int grade;
    private final String nickname;
    private final char gender;

    @Builder
    private FollowResponse(
            long blogId,
            int grade,
            String nickname,
            char gender
    ) {
        this.blogId = blogId;
        this.grade = grade;
        this.nickname = nickname;
        this.gender = gender;
    }
}
