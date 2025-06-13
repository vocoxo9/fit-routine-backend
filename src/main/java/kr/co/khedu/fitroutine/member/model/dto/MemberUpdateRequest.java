package kr.co.khedu.fitroutine.member.model.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public final class MemberUpdateRequest {
    private final String nickname;
    private final String newPassword;
    private final String phone;
    private final Integer height;
    private final Integer weight;

    @Builder
    private MemberUpdateRequest(
            String nickname,
            String newPassword,
            String phone,
            Integer height,
            Integer weight
    ) {
        this.nickname = nickname;
        this.newPassword = newPassword;
        this.phone = phone;
        this.height = height;
        this.weight = weight;
    }
}
