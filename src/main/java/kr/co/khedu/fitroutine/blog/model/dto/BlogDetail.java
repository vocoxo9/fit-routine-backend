package kr.co.khedu.fitroutine.blog.model.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public final class BlogDetail {
    private final long memberId;
    private final String nickname;
    private final int grade;
    private final String introduce;
    private final String gender;
    private final int likeCount;
    private final int liked;


    @Builder
    private BlogDetail(
            long memberId,
            String nickname,
            int grade,
            String introduce,
            String gender,
            int likeCount,
            int liked
    ) {
        this.memberId = memberId;
        this.nickname = nickname;
        this.grade = grade;
        this.introduce = introduce;
        this.gender = gender;
        this.likeCount = likeCount;
        this.liked = liked;
    }
}
