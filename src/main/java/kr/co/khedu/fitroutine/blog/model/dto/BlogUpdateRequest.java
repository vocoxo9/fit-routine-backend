package kr.co.khedu.fitroutine.blog.model.dto;

import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import org.springframework.lang.Nullable;

@Getter
public final class BlogUpdateRequest {
    @Size(min = 1, max = 255)
    private final @Nullable String introduce;

    @Builder
    private BlogUpdateRequest(@Nullable String introduce) {
        this.introduce = introduce;
    }
}
