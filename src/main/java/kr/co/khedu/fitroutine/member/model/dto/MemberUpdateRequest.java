package kr.co.khedu.fitroutine.member.model.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.lang.Nullable;

@Getter
public final class MemberUpdateRequest {
    private final @Nullable String nickname;
    private final @Nullable String newPassword;
    private final @Nullable String phone;
    private final @Nullable Integer height;
    private final @Nullable Integer weight;

    @Builder
    private MemberUpdateRequest(
            @Nullable String nickname,
            @Nullable String newPassword,
            @Nullable String phone,
            @Nullable Integer height,
            @Nullable Integer weight
    ) {
        this.nickname = nickname;
        this.newPassword = newPassword;
        this.phone = phone;
        this.height = height;
        this.weight = weight;
    }
}
