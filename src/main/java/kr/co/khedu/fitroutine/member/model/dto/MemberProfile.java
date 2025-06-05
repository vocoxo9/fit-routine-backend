package kr.co.khedu.fitroutine.member.model.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public final class MemberProfile {
    private final int memberId;
    private final String email;
    private final String nickname;
    private final String birth;
    private final String phone;
    private final char gender;
    private final int height;
    private final int weight;

    @Builder
    public MemberProfile(int memberId, String email, String nickname, String birth, String phone, char gender, int height, int weight) {
        this.memberId = memberId;
        this.email = email;
        this.nickname = nickname;
        this.birth = birth;
        this.phone = phone;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
    }
}
