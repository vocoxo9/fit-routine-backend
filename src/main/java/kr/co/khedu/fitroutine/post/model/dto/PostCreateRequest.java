package kr.co.khedu.fitroutine.post.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

@Getter
@Setter
public final class PostCreateRequest {
    @Null
    private @Nullable Long postId;

    @NotBlank
    @Size(max = 150)
    private final String title;

    @NotNull
    @Size(max = 1500)
    private final String content;

    @NotNull
    private final PostCategory category;

    @Builder
    private PostCreateRequest(
            String title,
            String content,
            PostCategory category
    ) {
        this.title = title;
        this.content = content;
        this.category = category;
    }
}
