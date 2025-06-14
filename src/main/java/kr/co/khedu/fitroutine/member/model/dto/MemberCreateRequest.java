package kr.co.khedu.fitroutine.member.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.springframework.lang.Nullable;

import java.time.LocalDate;

@Getter
@Setter
public final class MemberCreateRequest {
    @Null
    private @Nullable Long memberId;

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    private final String email;

    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)[A-Za-z\\d!@#$%^&*()_+=\\-{}\\[\\]|:;\"'<>,.?/~`]{8,}$")
    private final String password;

    @Pattern(regexp = "^[a-zA-Z0-9]{2,16}$")
    private final String nickname;

    @NotNull
    private final MemberGender gender;

    @NotNull
    private final LocalDate birthAt;

    @Pattern(regexp = "^0\\d{1,2}-\\d{3,4}-\\d{4}$")
    private final String phone;

    @Range(min = 1, max = 499)
    private final int height;

    @Range(min = 1, max = 499)
    private final int weight;

    @Builder
    private MemberCreateRequest(
            String email,
            String password,
            String nickname,
            MemberGender gender,
            LocalDate birthAt,
            String phone,
            int height,
            int weight
    ) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.gender = gender;
        this.birthAt = birthAt;
        this.phone = phone;
        this.height = height;
        this.weight = weight;
    }
}
