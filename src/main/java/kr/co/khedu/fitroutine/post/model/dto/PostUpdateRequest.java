package kr.co.khedu.fitroutine.post.model.dto;

import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import org.springframework.lang.Nullable;

@Getter
public final class PostUpdateRequest {
    @Size(max = 150)
    private final @Nullable String title;

    @Size(max = 1500)
    private final @Nullable String content;

    private final @Nullable PostCategory category;

    @Builder
    private PostUpdateRequest(
            @Nullable String title,
            @Nullable String content,
            @Nullable PostCategory category
    ) {
        this.title = title;
        this.content = content;
        this.category = category;
    }
}
