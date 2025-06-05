package kr.co.khedu.fitroutine.member.model.vo;

import lombok.Builder;
import lombok.Getter;

import java.sql.Date;

@Getter
public final class Member {
    private final long memberId;
    private final String email;
    private final String password;
    private final String nickname;
    private final String gender;
    private final Date birthAt;
    private final String phone;
    private final Date createdAt;
    private final boolean isDeleted;

    @Builder
    private Member(
            long memberId,
            String email,
            String password,
            String nickname,
            String gender,
            Date birthAt,
            String phone,
            Date createdAt,
            boolean isDeleted
    ) {
        this.memberId = memberId;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.gender = gender;
        this.birthAt = birthAt;
        this.phone = phone;
        this.createdAt = createdAt;
        this.isDeleted = isDeleted;
    }
}
