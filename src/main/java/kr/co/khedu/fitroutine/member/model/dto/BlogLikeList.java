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
    private String nickname;
    private char gender;
    private int grade;

    @Builder
    public BlogLikeList(long memberId, String nickname, char gender, int grade) {
        this.memberId = memberId;
        this.nickname = nickname;
        this.gender = gender;
        this.grade = grade;
    }
}
