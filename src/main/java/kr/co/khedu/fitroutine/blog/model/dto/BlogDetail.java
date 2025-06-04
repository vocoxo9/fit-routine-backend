package kr.co.khedu.fitroutine.blog.model.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public final class BlogDetail {
    private final long memberId;
    private final int grade;
    private final String introduce;
    private final String gender;
    private final int likeCount;
    private final boolean liked;

    @Builder
    private BlogDetail(
            long memberId,
            int grade,
            String introduce,
            String gender,
            int likeCount,
            boolean liked
    ) {
        this.memberId = memberId;
        this.grade = grade;
        this.introduce = introduce;
        this.gender = gender;
        this.likeCount = likeCount;
        this.liked = liked;
    }
}
