package kr.co.khedu.fitroutine.member.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class BlogLikeList {
    private long memberId;
    private long blogId;
    private String nickname;
    private char gender;
    private int grade;

    @Builder
    public BlogLikeList(long memberId, long blogId, String nickname, char gender, int grade) {
        this.memberId = memberId;
        this.blogId = blogId;
        this.nickname = nickname;
        this.gender = gender;
        this.grade = grade;
    }
}
