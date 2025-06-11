package kr.co.khedu.fitroutine.blog.model.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public final class BlogResponse {
    private final long blogId;
    private final String introduce;
    private final int grade;
    private final String nickname;
    private final char gender;

    @Builder
    private BlogResponse(
            long blogId,
            String introduce,
            int grade,
            String nickname,
            char gender
    ) {
        this.blogId = blogId;
        this.introduce = introduce;
        this.grade = grade;
        this.nickname = nickname;
        this.gender = gender;
    }
}
